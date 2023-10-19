package com.hackathon.mind_mentor.models;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class Bot {
    public static String getGPTResponse(String prompt) {
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
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
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

    public static String getPrompt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("How can I help you today?");
        System.out.println(getGPTResponse(getPrompt()));

        while (true) {
            String userResponse = getPrompt(); // Prompt for user input
            if (userResponse.equalsIgnoreCase("end")) {
                break; // Exit the loop if the user says "end"
            }

            String botResponse = getGPTResponse(userResponse);
            System.out.println(botResponse);

        }
    }
}
