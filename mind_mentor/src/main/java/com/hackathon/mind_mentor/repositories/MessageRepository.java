package com.hackathon.mind_mentor.repositories;

import com.hackathon.mind_mentor.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteByChatId(Long id);
}
