package com.hackathon.mind_mentor.models;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bot {
    private static List<Message> conversationHistory = new ArrayList<>();

    private static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String toJson() {
            return "{\"role\": \"" + role + "\", \"content\": \"" + content + "\"}";
        }
    }

    public static String getGPTResponse(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKeyPath = "src/main/java/com/hackathon/mind_mentor/key/api_key.txt";
        String apiKey = readApiKeyFromFile(apiKeyPath);
        String model = "gpt-3.5-turbo";

        // Add the user's message to the conversation history
        conversationHistory.add(new Message("user", prompt));

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Include the entire conversation history in the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": " + messagesToJson(conversationHistory) + "}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Extract and return the latest message from the model's response
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

    public static String getPrompt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("How can I help you today?");
        String userResponse = getPrompt();

        while (true) {
            if (userResponse.equalsIgnoreCase("end")) {
                break;
            }

            String botResponse = getGPTResponse(userResponse);
            System.out.println(botResponse);

            // Get the next user response
            userResponse = getPrompt();
        }
    }

    private static String messagesToJson(List<Message> messages) {
        StringBuilder json = new StringBuilder("[");
        for (Message message : messages) {
            if (json.length() > 1) {
                json.append(", ");
            }
            json.append(message.toJson());
        }
        json.append("]");
        return json.toString();
>>>>>>> develop
    }
}
