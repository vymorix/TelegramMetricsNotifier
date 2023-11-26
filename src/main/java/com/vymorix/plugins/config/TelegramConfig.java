package com.vymorix.plugins.config;

public class TelegramConfig {

    private final String token;
    private final String chatId;

    public TelegramConfig(String token, String chatId) {
        this.token = token;
        this.chatId = chatId;
    }

    public String getToken() {
        return token;
    }

    public String getChatId() {
        return chatId;
    }
}
