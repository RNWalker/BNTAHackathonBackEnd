package com.hackathon.mind_mentor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    @JsonIgnoreProperties({"messages"})
    private Chat chat;

    @Column
    private LocalDateTime dateTime;

    @Column
    private boolean isBot;

    @Column
    private String message;

    public Message(Chat chat, LocalDateTime dateTime, boolean isBot, String message){
        this.chat = chat;
        this.dateTime = dateTime;
        this.isBot = isBot;
        this.message = message;
    }

    public Message(){
    }

    public
    Long getId(){
        return id;
    }

    public
    void setId(Long id){
        this.id = id;
    }

    public
    Chat getChat(){
        return chat;
    }

    public
    void setChat(Chat chat){
        this.chat = chat;
    }

    public
    boolean isBot(){
        return isBot;
    }

    public
    void setBot(boolean bot){
        isBot = bot;
    }

    public
    LocalDateTime getDateTime(){
        return dateTime;
    }

    public
    void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public
    String getMessage(){
        return message;
    }

    public
    void setMessage(String message){
        this.message = message;
    }
}
