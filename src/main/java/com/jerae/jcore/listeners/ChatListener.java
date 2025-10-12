package com.jerae.jcore.listeners;

import com.jerae.jcore.JCore;
import com.jerae.jcore.utils.ChatColorUtils;
import com.jerae.jcore.utils.GradientUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final JCore plugin;

    public ChatListener(JCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (plugin.getConversationManager().isinConversation(player)) {
            plugin.getConversationManager().handleResponse(player, event.getMessage());
            event.setCancelled(true);
            return;
        }

        String message = event.getMessage();
        String color = plugin.getPlayerColorManager().getPlayerColor(player);
        String startGradient = plugin.getPlayerColorManager().getPlayerGradientStart(player);
        String endGradient = plugin.getPlayerColorManager().getPlayerGradientEnd(player);

        // Prevent color codes from being used if the player doesn't have permission
        // (This part will be added later when permissions are fully implemented)

        // Apply the selected color
        if (startGradient != null && endGradient != null) {
            event.setMessage(GradientUtils.applyGradient(message, startGradient, endGradient));
        } else if (color != null && !color.equalsIgnoreCase("white")) {
            event.setMessage(ChatColorUtils.translate(color + message));
        }
    }
}