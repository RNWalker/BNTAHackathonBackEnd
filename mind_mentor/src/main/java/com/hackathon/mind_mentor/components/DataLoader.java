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
        
        Message message1 = new Message(chat1, LocalDateTime.now(), true, "Hi, I'm Mind Mentor. How can I help you today?");
        messageRepository.save(message1);
//
//        Message message2 = new Message(chat1, LocalDateTime.now(), true, "Hello, John! I'm here to help. How are you feeling?");
//        messageRepository.save(message2);
//
//        Message message3 = new Message(chat1, LocalDateTime.now(), false, "I've been feeling really stressed and anxious lately.");
//        messageRepository.save(message3);
//
//        Message message4 = new Message(chat1, LocalDateTime.now(), true, "I'm sorry to hear that. Let's talk about what's been bothering you.");
//        messageRepository.save(message4);
//
//        Message message5 = new Message(chat1, LocalDateTime.now(), false, "It's work-related stress and personal issues.");
//        messageRepository.save(message5);
//
//        Message message6 = new Message(chat1, LocalDateTime.now(), true, "I understand. It's normal to feel this way sometimes. Have you considered talking to a professional?");
//        messageRepository.save(message6);
//
//        Message message7 = new Message(chat1, LocalDateTime.now(), false, "I haven't thought about that. Do you have any recommendations for professionals?");
//        messageRepository.save(message7);
//
//        Message message8 = new Message(chat1, LocalDateTime.now(), true, "I can suggest a few mental health professionals in your area. Please share your location, and I'll find some options for you.");
//        messageRepository.save(message8);
//
//        Message message9 = new Message(chat1, LocalDateTime.now(), false, "I'm located in London.");
//        messageRepository.save(message9);
//
//        Message message10 = new Message(chat1, LocalDateTime.now(), true, "Great! I'll find some professionals in London for you. Please hold on for a moment.");
//        messageRepository.save(message10);
//
//        Message message11 = new Message(chat1, LocalDateTime.now(), true, "Here are a few mental health professionals in London:\n1. Dr. Sarah Smith\n2. Dr. John Davis\n3. Dr. Emily Wilson\n\nYou can contact any of them to schedule an appointment.");
//        messageRepository.save(message11);
//
//        Message message12 = new Message(chat1, LocalDateTime.now(), false, "Thank you for your help. I'll contact one of them for sure.");
//        messageRepository.save(message12);
//
//        Message message13 = new Message(chat1, LocalDateTime.now(), false, "You're welcome, John. I'm here whenever you need someone to talk to. Take care.");
//        messageRepository.save(message13);









    }

}
