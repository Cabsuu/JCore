package com.jerae.jcore.managers;

import com.jerae.jcore.JCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerColorManager {

    private final JCore plugin;
    private final FileConfiguration config;

    public PlayerColorManager(JCore plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        plugin.saveDefaultConfig();
    }

    public String getPlayerColor(Player player) {
        return config.getString("players." + player.getUniqueId() + ".color", "§f");
    }

    public void setPlayerColor(Player player, String color) {
        String path = "players." + player.getUniqueId();
        config.set(path + ".color", color);
        config.set(path + ".gradient", null); // Clear gradient settings
        plugin.saveConfig();
    }

    public void setPlayerGradient(Player player, String startColor, String endColor) {
        String path = "players." + player.getUniqueId();
        config.set(path + ".gradient.start", startColor);
        config.set(path + ".gradient.end", endColor);
        config.set(path + ".color", null); // Clear single color setting
        plugin.saveConfig();
    }

    public String getPlayerGradientStart(Player player) {
        return config.getString("players." + player.getUniqueId() + ".gradient.start");
    }

    public String getPlayerGradientEnd(Player player) {
        return config.getString("players." + player.getUniqueId() + ".gradient.end");
    }
}