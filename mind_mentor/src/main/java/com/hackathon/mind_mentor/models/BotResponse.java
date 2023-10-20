package com.hackathon.mind_mentor.models;

public class BotResponse {

    private boolean isBot;
    private String content;

    public BotResponse() {
    }

    public BotResponse(boolean isBot, String content) {
        this.isBot = isBot;
        this.content = content;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
