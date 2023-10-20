package com.hackathon.mind_mentor.services;

import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.models.Message;
import com.hackathon.mind_mentor.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    public Message createMessage(Chat chat, LocalDateTime dateTime, boolean isBot, String text){
        Message message = new Message(chat,dateTime, isBot, text);
        return messageRepository.save(message);
    }
}