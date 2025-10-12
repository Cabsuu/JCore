package com.jerae.jcore.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class ConversationManager {

    private final Map<UUID, Consumer<String>> conversations = new HashMap<>();

    public void startConversation(Player player, Consumer<String> handler) {
        conversations.put(player.getUniqueId(), handler);
    }

    public void endConversation(Player player) {
        conversations.remove(player.getUniqueId());
    }

    public boolean isinConversation(Player player) {
        return conversations.containsKey(player.getUniqueId());
    }

    public void handleResponse(Player player, String message) {
        if (conversations.containsKey(player.getUniqueId())) {
            conversations.get(player.getUniqueId()).accept(message);
        }
    }
}