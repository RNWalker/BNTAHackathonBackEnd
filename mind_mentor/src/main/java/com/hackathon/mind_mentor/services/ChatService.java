package com.hackathon.mind_mentor.services;

import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.models.Message;
import com.hackathon.mind_mentor.models.User;
import com.hackathon.mind_mentor.repositories.ChatRepository;
import com.hackathon.mind_mentor.repositories.MessageRepository;
import com.hackathon.mind_mentor.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public List<Chat> findAllChats() {
        return chatRepository.findAll();
    }


    public Optional<Chat> findChatById(Long id) {
        return chatRepository.findById(id);
    }


    public List<Chat> findAllChatsOfUser(Long userId) {
        return chatRepository.findByUserId(userId);
    }

    public Chat createChat(Long userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Chat chat = new Chat(date, user);
        return chatRepository.save(chat);
    }

    public void deleteChat(Long id) {
        messageRepository.deleteByChatId(id);
        chatRepository.deleteById(id);
    }

    public void addMessageToChat(Chat chat, boolean isBot, String message) {
        Message chatMessage = new Message(chat, LocalDateTime.now(), isBot, message);
        chat.addMessage(chatMessage);
        chatRepository.save(chat);
    }

    public void saveChat(Chat chat){
        chatRepository.save(chat);
    }






}
