package com.vymorix.plugins.service;

import com.vymorix.plugins.config.TelegramConfig;
import com.vymorix.plugins.utils.HttpRequestUtil;

import java.net.URL;
import java.util.logging.Logger;

public class TelegramService {

    private TelegramConfig config;
    private HttpRequestUtil httpRequestUtil;
    private static final Logger logger = Logger.getLogger("TelegramService");

    public TelegramService(TelegramConfig config) {
        this.config = config;
        this.httpRequestUtil = new HttpRequestUtil();
    }

    public void sendServerStats(String message) {
        if (config.getToken() == "" || config.getChatId() == "") {
            System.out.println("Telegram token or chat id is not set");
            return;
        }
        try {
            URL url =
                    new URL(
                            String.format(
                                    "https://api.telegram.org/bot%s/sendMessage",
                                    config.getToken()));
            String payload = String.format("chat_id=%s&text=%s", config.getChatId(), message);
            httpRequestUtil.sendPostRequest(url, payload.getBytes());
        } catch (Exception e) {
            System.out.println("Failed to send message to Telegram");
            e.printStackTrace();
        }
    }
}
