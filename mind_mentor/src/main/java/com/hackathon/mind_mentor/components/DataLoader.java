package com.hackathon.mind_mentor.components;

import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.models.Message;
import com.hackathon.mind_mentor.models.User;
import com.hackathon.mind_mentor.repositories.ChatRepository;
import com.hackathon.mind_mentor.repositories.MessageRepository;
import com.hackathon.mind_mentor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        User user1 = new User ("John","Doe","john@doe.com","07985987254");
        userRepository.save(user1);
        Chat chat1 = new Chat(LocalDate.now(),user1);
        chatRepository.save(chat1);
        Message message1 = new Message(chat1, LocalDateTime.now(), true, "Test");
        messageRepository.save(message1);
    }

}
