package com.hackathon.mind_mentor.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Chat chat;

    @Column
    private LocalDate dateTime;

    @Column
    private boolean isBot;

    public Message(Chat chat, LocalDate dateTime, boolean isBot){
        this.chat = chat;
        this.dateTime = dateTime;
        this.isBot = isBot;
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
    LocalDate getDateTime(){
        return dateTime;
    }

    public
    void setDateTime(LocalDate dateTime){
        this.dateTime = dateTime;
    }

    public
    boolean isBot(){
        return isBot;
    }

    public
    void setBot(boolean bot){
        isBot = bot;
    }
}
