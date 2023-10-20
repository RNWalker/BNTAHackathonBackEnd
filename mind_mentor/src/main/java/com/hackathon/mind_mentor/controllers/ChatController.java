package com.hackathon.mind_mentor.controllers;

import com.hackathon.mind_mentor.models.Bot;
import com.hackathon.mind_mentor.models.BotResponse;
import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.models.MessageRequest;
import com.hackathon.mind_mentor.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/chats")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping(value = "/all")
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
        Chat chat = chatService.createChat(userId, LocalDate.now());
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Chat> deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BotResponse> postMessage(
            @RequestParam(name = "chatId") Long chatId,
            @RequestBody MessageRequest messageRequest
    ) {
        // Fetch the chat by chatId
        Optional<Chat> chat = chatService.findChatById(chatId);

        if (chat != null) {
            // Assuming messageRequest has a "content" field with the user's message.
            String userMessage = messageRequest.getContent();

            // Add user message to the chat's conversation history.
            chatService.addMessageToChat(chat.get(), false, userMessage);

            // Get a response from your GPT-based chatbot.
            String botResponse = Bot.getGPTResponse(userMessage);

            // Add bot response to the chat's conversation history.
            chatService.addMessageToChat(chat.get(), true, botResponse);

            // Save the updated chat.
            chatService.saveChat(chat.get());

            // Return the bot's response as an object.
            BotResponse response = new BotResponse(true, botResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
