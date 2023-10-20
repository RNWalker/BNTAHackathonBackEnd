package com.hackathon.mind_mentor.services;

import com.hackathon.mind_mentor.models.Message;
import com.hackathon.mind_mentor.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BotService {

//    @Autowired
//    MessageService messageService;

//    public String generateMessageBody(){
//        List<Message> conversationMessages = messageService.getAllMessages(1);
//        String newBody = "";
//
//        for (int i = 0; i < conversationMessages.size() ; i++ ){
//            if(conversationMessages.get(i).isBot()){
//                String prompt = conversationMessages.get(i).getMessage();
//                newBody += "\"role\": \"assistant\", \"content\": \"" + prompt + "\"";
//            } else if(!conversationMessages.get(i).isBot()){
//                String prompt = conversationMessages.get(i).getMessage();
//                newBody += "\"role\": \"user\", \"content\": \"" + prompt + "\"";
//            }
//        }
//        return newBody;
//    }
//
//    public static String getGPTResponse(String prompt) {
//        String url = "https://api.openai.com/v1/chat/completions";
//        String apiKeyPath = "src/main/java/com/hackathon/mind_mentor/key/api_key.txt";
//        String apiKey = readApiKeyFromFile(apiKeyPath); // Read API key from the specified path
//        System.out.println(apiKey);
//        String model = "gpt-3.5-turbo";
//        try {
//            URL obj = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
//            connection.setRequestProperty("Content-Type", "application/json");
//            // The request body
////            String messageBody = generateMessageBody();
////            String body = "{\"model\": \"" + model + "\", \"messages\": [{" + messageBody + "}]}";
//            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
//            connection.setDoOutput(true);
//            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//            writer.write(body);
//            writer.flush();
//            writer.close();
//            // Response from ChatGPT
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            StringBuffer response = new StringBuffer();
//            while ((line = br.readLine()) != null) {
//                response.append(line);
//            }
//            br.close();
//            // Calls the method to extract the message.
//            return extractMessageFromJSONResponse(response.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static String extractMessageFromJSONResponse(String response) {
//        int start = response.indexOf("content") + 11;
//        int end = response.indexOf("\"", start);
//        return response.substring(start, end);
//    }
//    public static String readApiKeyFromFile(String filePath) {
//        try {
//            Scanner scanner = new Scanner(new File(filePath));
//            if (scanner.hasNextLine()) {
//                return scanner.nextLine().trim();
//            } else {
//                throw new RuntimeException("API key file is empty.");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error reading API key from file: " + e.getMessage());
//        }
//    }

}
