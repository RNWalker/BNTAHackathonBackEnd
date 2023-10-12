package com.hackathon.mind_mentor.services;

import com.hackathon.mind_mentor.models.Chat;
import com.hackathon.mind_mentor.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;

    public List<Chat> findAllChats() {
        return chatRepository.findAll();
    }


    public Optional<Chat> findChatById(Long id) {
        return chatRepository.findById(id);
    }


    public List<Chat> findAllChatsOfUser(Long userId) {
        return chatRepository.findByUserId(userId);
    }

    public Chat createChat(Long userId) {
        Chat chat = new Chat()
    }
}
