package com.vymorix.plugins;

import com.vymorix.plugins.config.PluginConfig;
import com.vymorix.plugins.config.TelegramConfig;
import com.vymorix.plugins.models.Metrics;
import com.vymorix.plugins.service.TelegramService;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TelegramMetricsNotifier extends JavaPlugin {

    private PluginConfig pluginConfig;
    private TelegramService telegramService;
    private Metrics metrics;
    private BukkitTask metricsTask;

    @Override
    public void onEnable() {
        this.pluginConfig = new PluginConfig(this);
        this.telegramService =
                new TelegramService(
                        new TelegramConfig(
                                pluginConfig.getTelegramToken(), pluginConfig.getTelegramChatId()));
        this.metrics = new Metrics(getServer());

        int intervalMinutes = pluginConfig.getSendIntervalMinutes();
        long intervalTicks = intervalMinutes * 60 * 20;

        this.metricsTask =
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        sendServerMetricsTask();
                    }
                }.runTaskTimer(this, intervalTicks, intervalTicks);

        getLogger().info("TelegramMetricsNotifier enabled!");
    }

    private void sendServerMetricsTask() {
        String serverMetrics = metrics.collectMetrics();
        System.out.println(serverMetrics);
        telegramService.sendServerStats(serverMetrics);
    }
}
