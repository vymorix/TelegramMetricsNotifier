package com.vymorix.plugins.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginConfig {

    private final JavaPlugin plugin;
    private FileConfiguration config;

    public PluginConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.config.options().copyDefaults(true);

        config.addDefault("telegram.token", "");
        config.addDefault("telegram.chatId", "");
        config.addDefault("stats.send_interval_minutes", 1);

        plugin.saveConfig();
    }

    public String getTelegramToken() {
        return config.getString("telegram.token");
    }

    public String getTelegramChatId() {
        return config.getString("telegram.chatId");
    }

    public int getSendIntervalMinutes() {
        return config.getInt("stats.send_interval_minutes");
    }

    public void setStatsSendIntervalMinutes(int minutes) {
        config.set("stats.send_interval_minutes", minutes);
        plugin.saveConfig();
    }
}
