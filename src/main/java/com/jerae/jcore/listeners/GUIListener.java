package com.jerae.jcore.listeners;

import com.jerae.jcore.JCore;
import com.jerae.jcore.guis.ChatColorGUI;
import com.jerae.jcore.utils.ChatColorUtils;
import com.jerae.jcore.utils.PermissionUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIListener implements Listener {

    private final JCore plugin;

    public GUIListener(JCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Chat Color")) {
            return;
        }

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (clickedItem.getType().name().endsWith("_WOOL")) {
            ChatColor color = ChatColorGUI.getColor(event.getSlot());
            if (color == null) return;

            String colorName = color.name().toLowerCase().replace("_", "");

            if (PermissionUtils.hasPermission(player, "chatcolor.color." + colorName)) {
                plugin.getPlayerColorManager().setPlayerColor(player, color.toString());
                player.sendMessage(ChatColorUtils.translate("&aYour chat color has been set to " + color + color.name()));
                player.closeInventory();
            }
        } else if (clickedItem.getType() == Material.ANVIL) {
            if (PermissionUtils.hasPermission(player, "chatcolor.hex")) {
                player.closeInventory();
                player.sendMessage(ChatColor.YELLOW + "Please enter a hex color code in chat (e.g., #FF5733).");
                plugin.getConversationManager().startConversation(player, (response) -> {
                    if (!response.matches("^#([a-fA-F0-9]{6})$")) {
                        player.sendMessage(ChatColor.RED + "Invalid hex code format.");
                        plugin.getConversationManager().endConversation(player);
                        return;
                    }
                    plugin.getPlayerColorManager().setPlayerColor(player, response);
                    player.sendMessage(ChatColor.GREEN + "Your chat color has been set to " + response);
                    plugin.getConversationManager().endConversation(player);
                });
            }
        } else if (clickedItem.getType() == Material.BUCKET) {
            if (PermissionUtils.hasPermission(player, "chatcolor.gradient")) {
                player.closeInventory();
                player.sendMessage(ChatColor.YELLOW + "Enter the first hex color for the gradient.");
                plugin.getConversationManager().startConversation(player, (startColor) -> {
                    if (!startColor.matches("^#([a-fA-F0-9]{6})$")) {
                        player.sendMessage(ChatColor.RED + "Invalid hex code format for start color.");
                        plugin.getConversationManager().endConversation(player);
                        return;
                    }
                    player.sendMessage(ChatColor.YELLOW + "Enter the second hex color for the gradient.");
                    plugin.getConversationManager().startConversation(player, (endColor) -> {
                        if (!endColor.matches("^#([a-fA-F0-9]{6})$")) {
                            player.sendMessage(ChatColor.RED + "Invalid hex code format for end color.");
                            plugin.getConversationManager().endConversation(player);
                            return;
                        }
                        plugin.getPlayerColorManager().setPlayerGradient(player, startColor, endColor);
                        player.sendMessage(ChatColor.GREEN + "Your chat color has been set to a gradient from " + startColor + " to " + endColor);
                        plugin.getConversationManager().endConversation(player);
                    });
                });
            }
        } else if (clickedItem.getType() == Material.OAK_DOOR) {
            player.closeInventory();
        }
    }
}