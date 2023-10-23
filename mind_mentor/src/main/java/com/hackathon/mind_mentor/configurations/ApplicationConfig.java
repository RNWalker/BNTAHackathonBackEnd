package com.hackathon.mind_mentor.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class ApplicationConfig {
    @Bean
    public String encryptionKey() throws IOException {
        Path keyFilePath = Path.of("src/main/java/com/hackathon/mind_mentor/key/encryption_key.txt");
        String key = Files.readString(keyFilePath);
        return key;
    }

    @Bean
    public String initVectorKey() throws IOException {
        Path keyFilePath = Path.of("src/main/java/com/hackathon/mind_mentor/key/initVector_key.txt");
        String key = Files.readString(keyFilePath);
        return key;
    }

}
