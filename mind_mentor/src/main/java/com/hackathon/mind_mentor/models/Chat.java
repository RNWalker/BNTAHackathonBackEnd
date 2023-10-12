package com.hackathon.mind_mentor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"chats"})
    private User user;

    @OneToMany (mappedBy = "chat")
    @JsonIgnoreProperties({"chat"})
    private List<Message> messages;

    public Chat(LocalDate date, User user, List<Message> messages){
        this.date = date;
        this.user = user;
        this.messages = new ArrayList<>();
    }

    public Chat (){

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
    LocalDate getDate(){
        return date;
    }

    public
    void setDate(LocalDate date){
        this.date = date;
    }

    public
    User getUser(){
        return user;
    }

    public
    void setUser(User user){
        this.user = user;
    }

    public
    List<Message> getMessages(){
        return messages;
    }

    public
    void setMessages(List<Message> messages){
        this.messages = messages;
    }
}
