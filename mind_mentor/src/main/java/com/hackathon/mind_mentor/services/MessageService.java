package com.hackathon.mind_mentor.services;

import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.models.Message;
import com.hackathon.mind_mentor.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

//    @Autowired
//    BotService botService;

    private static List<MessageOutput> conversationHistory = new ArrayList<>();

    private static class MessageOutput {
        private String role;
        private String content;

        public MessageOutput(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String toJson() {
            return "{\"role\": \"" + role + "\", \"content\": \"" + content + "\"}";
        }
    }

    public Message createMessage(Chat chat, LocalDateTime now, boolean b, String text){
        Message message = new Message(chat,now,b,text);
        String modText = "In less than 200 characters, give me a response to :" + text;
        Message botMessage = new Message(chat,now,true, getGPTResponse(modText));
        messageRepository.save(message);
        messageRepository.save(botMessage);
        return message;
    }

    public List<Message> getAllMessages(int chatId){
        return messageRepository.findByChatId(chatId);
    }

    public void generateMessageBody(){
        List<Message> conversationMessages = getAllMessages(1);

        conversationHistory.add(new MessageOutput("system","You are a chatbot trained by a team of celebrated psychologists with over 30 years experience of supporting people with mental health difficulty, particularly in relation to work-place stressors. You are familiar with all the relevant UK guidelines, including NICE guidelines for anxiety disorders, depression, red flag psychiatric symptoms and many more. You are now going to have a chat with someone who feels like they need to talk to you about their problems. You should ask a mix of open and closed questions to get o the root of the matter and close the consultation appropriately when you have no more questions or suggestions. Everything you say to them should not include jargon and should be put simply for them. On mentioning of red flag symptoms, you should suggest a user calls 999 or contact a medical professional for support. The app includes: an article about managing workplace stress, an article about work life balance, an article about friendship and talking to others, a video of a 5 minute meditation, a phone number for Mind, a phone number for Samaritans, a phone number for CALM and a phone number for the National Suicide Prevention HelpLine UK. If appropriate, direct the user to these resources, found in the Resources section of the app. Now introduce yourself saying the following only: “Hi, I’m Mind Mentor! How can I help you today?"));

        for (int i = 0; i < conversationMessages.size() ; i++ ) {
            if (conversationMessages.get(i).isBot()) {
                String prompt = conversationMessages.get(i).getMessage();
                conversationHistory.add(new MessageOutput("assistant", prompt));
            } else if (!conversationMessages.get(i).isBot()) {
                String prompt = conversationMessages.get(i).getMessage();
                conversationHistory.add(new MessageOutput("user", prompt));
            }
        }
    }

    private static String messagesToJson(List<MessageOutput> messages) {
        StringBuilder json = new StringBuilder("[");
        for (MessageOutput message : messages) {
            if (json.length() > 1) {
                json.append(", ");
            }
            json.append(message.toJson());
        }
        json.append("]");
        return json.toString();
    }

    public String getGPTResponse(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKeyPath = "src/main/java/com/hackathon/mind_mentor/key/api_key.txt";
        String apiKey = readApiKeyFromFile(apiKeyPath); // Read API key from the specified path
        System.out.println(apiKey);
        String model = "gpt-3.5-turbo";
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            // The request body
            generateMessageBody();
            conversationHistory.add(new MessageOutput("user", prompt));
//            String body = "{\"model\": \"" + model + "\", \"messages\": [\""+ messageBody +"\"{\"role\": \"user\", \"content\": \"" + prompt + "\"]}}";
//            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}"; //without context
            String body = "{\"model\": \"" + model + "\", \"messages\": " + messagesToJson(conversationHistory) + "}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            // Calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }
    public static String readApiKeyFromFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            } else {
                throw new RuntimeException("API key file is empty.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading API key from file: " + e.getMessage());
        }
    }
}