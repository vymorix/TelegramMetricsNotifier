package com.vymorix.plugins.models;

import org.bukkit.Server;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class Metrics {

    private Server server;

    public Metrics(Server server) {
        this.server = server;
    }

    public String collectMetrics() {
        StringBuilder metrics = new StringBuilder();

        metrics.append("----- Server Metrics -----\n");
        metrics.append("Online Players: ").append(server.getOnlinePlayers().size()).append("\n");
        metrics.append("Max Players: ").append(server.getMaxPlayers()).append("\n");
        metrics.append("Loaded Chunks: ")
                .append(
                        server.getWorlds().stream()
                                .mapToInt(world -> world.getLoadedChunks().length)
                                .sum())
                .append("\n");

        // Add memory statistics
        MemoryUsage heapMemoryUsage = getHeapMemoryUsage();
        metrics.append("Heap Memory: Used ")
                .append(formatMemorySize(heapMemoryUsage.getUsed()))
                .append(", Max ")
                .append(formatMemorySize(heapMemoryUsage.getMax()))
                .append("\n");

        return metrics.toString();
    }

    private String formatMemorySize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return bytes / 1024 + " KB";
        } else if (bytes < 1024 * 1024 * 1024) {
            return bytes / (1024 * 1024) + " MB";
        } else {
            return bytes / (1024 * 1024 * 1024) + " GB";
        }
    }

    private MemoryUsage getHeapMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        return memoryMXBean.getHeapMemoryUsage();
    }
}
