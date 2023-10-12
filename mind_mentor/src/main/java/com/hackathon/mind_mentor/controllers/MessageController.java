package com.hackathon.mind_mentor.controllers;

import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.models.Message;
import com.hackathon.mind_mentor.services.ChatService;
import com.hackathon.mind_mentor.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping (value = "/messages")
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    ChatService chatService;

    @PostMapping
    public ResponseEntity<Message> createMessage (@RequestParam Long chatId){
        Optional<Chat> chat = chatService.findChatById(chatId);

        if (chat.isPresent()) {
            Message message = messageService.createMessage(chat.get(), LocalDateTime.now(), false);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}