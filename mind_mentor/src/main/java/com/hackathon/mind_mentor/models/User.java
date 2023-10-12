package com.hackathon.mind_mentor.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private List<Chat> chats;

    public User(String firstName, String lastName, String email, String phoneNumber, List<Chat> chats){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.chats = new ArrayList<>();
    }

    public User(){

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
    String getFirstName(){
        return firstName;
    }

    public
    void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public
    String getLastName(){
        return lastName;
    }

    public
    void setLastName(String lastName){
        this.lastName = lastName;
    }

    public
    String getEmail(){
        return email;
    }

    public
    void setEmail(String email){
        this.email = email;
    }

    public
    String getPhoneNumber(){
        return phoneNumber;
    }

    public
    void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public
    List<Chat> getChats(){
        return chats;
    }

    public
    void setChats(List<Chat> chats){
        this.chats = chats;
    }
}
