package com.hackathon.mind_mentor.controllers;

import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/chats")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping
    public ResponseEntity<List<Chat>> getAllChats() {
        List<Chat> allChats = chatService.findAllChats();
        return new ResponseEntity<>(allChats, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable Long id) {
        Optional<Chat> chat = chatService.findChatById(id);
        if (chat.isPresent()) {
            return new ResponseEntity<>(chat.get(), HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Chat>> getAllChatsOfUser(@RequestParam (required = true, name = "userId") Long userId) {
        List<Chat> userChats = chatService.findAllChatsOfUser(userId);
        return new ResponseEntity<>(userChats, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestParam (required = true, name = "userId") Long userId) {
        Chat chat = chatService.createChat(userId);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Chat> deleteChat(@PathVariable Long id) {
        Chat chat = chatService.deleteChat(id);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
