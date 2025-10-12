package com.jerae.jcore.guis;

import com.jerae.jcore.JCore;
import com.jerae.jcore.managers.PlayerColorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatColorGUI {
    private static final Map<Integer, ChatColor> slotToColor = new HashMap<>();
    private static final Map<ChatColor, Integer> colorToSlot = new HashMap<>();

    static {
        slotToColor.put(0, ChatColor.BLACK);
        slotToColor.put(1, ChatColor.DARK_BLUE);
        slotToColor.put(2, ChatColor.DARK_GREEN);
        slotToColor.put(3, ChatColor.DARK_AQUA);
        slotToColor.put(4, ChatColor.DARK_RED);
        slotToColor.put(5, ChatColor.DARK_PURPLE);
        slotToColor.put(6, ChatColor.GOLD);
        slotToColor.put(7, ChatColor.GRAY);
        slotToColor.put(8, ChatColor.DARK_GRAY);
        slotToColor.put(9, ChatColor.BLUE);
        slotToColor.put(10, ChatColor.GREEN);
        slotToColor.put(11, ChatColor.AQUA);
        slotToColor.put(12, ChatColor.RED);
        slotToColor.put(13, ChatColor.LIGHT_PURPLE);
        slotToColor.put(14, ChatColor.YELLOW);
        slotToColor.put(15, ChatColor.WHITE);

        for (Map.Entry<Integer, ChatColor> entry : slotToColor.entrySet()) {
            colorToSlot.put(entry.getValue(), entry.getKey());
        }
    }

    public static ChatColor getColor(int slot) {
        return slotToColor.get(slot);
    }

    public static void open(Player player, JCore plugin) {
        Inventory gui = Bukkit.createInventory(null, 27, "Chat Color");
        PlayerColorManager colorManager = plugin.getPlayerColorManager();
        String selectedColor = colorManager.getPlayerColor(player);
        String startGradient = colorManager.getPlayerGradientStart(player);

        // Add vanilla color items
        for (Map.Entry<Integer, ChatColor> entry : slotToColor.entrySet()) {
            addVanillaColor(gui, entry.getKey(), getWoolMaterial(entry.getValue()), entry.getValue(), entry.getValue().name(), selectedColor, startGradient != null);
        }

        // Add special options
        addSpecialOption(gui, 21, Material.ANVIL, "Hex Color", "Enter a hex code", selectedColor != null && selectedColor.startsWith("#"), false);
        addSpecialOption(gui, 22, Material.BUCKET, "Gradient Color", "Select two colors", startGradient != null, false);

        // Add control buttons
        addControlButton(gui, 26, Material.OAK_DOOR, "Close", "Close the menu");

        player.openInventory(gui);
    }

    private static void addVanillaColor(Inventory gui, int slot, Material material, ChatColor color, String name, String selectedColor, boolean isGradient) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        List<String> lore = new ArrayList<>();
        lore.add("Click to select this color");
        meta.setLore(lore);

        if (!isGradient && selectedColor != null && selectedColor.equals(color.toString())) {
            meta.addEnchant(Enchantment.UNBREAKING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(meta);
        gui.setItem(slot, item);
    }

    private static void addSpecialOption(Inventory gui, int slot, Material material, String name, String description, boolean isSelected, boolean isGradient) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + name);
        List<String> lore = new ArrayList<>();
        lore.add(description);
        meta.setLore(lore);

        if (isSelected) {
            meta.addEnchant(Enchantment.UNBREAKING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(meta);
        gui.setItem(slot, item);
    }

    private static void addControlButton(Inventory gui, int slot, Material material, String name, String description) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + name);
        List<String> lore = new ArrayList<>();
        lore.add(description);
        meta.setLore(lore);
        item.setItemMeta(meta);
        gui.setItem(slot, item);
    }

    private static Material getWoolMaterial(ChatColor color) {
        switch (color) {
            case BLACK: return Material.BLACK_WOOL;
            case DARK_BLUE: return Material.BLUE_WOOL;
            case DARK_GREEN: return Material.GREEN_WOOL;
            case DARK_AQUA: return Material.CYAN_WOOL;
            case DARK_RED: return Material.RED_WOOL;
            case DARK_PURPLE: return Material.PURPLE_WOOL;
            case GOLD: return Material.ORANGE_WOOL;
            case GRAY: return Material.LIGHT_GRAY_WOOL;
            case DARK_GRAY: return Material.GRAY_WOOL;
            case BLUE: return Material.LIGHT_BLUE_WOOL;
            case GREEN: return Material.LIME_WOOL;
            case AQUA: return Material.LIGHT_BLUE_WOOL;
            case RED: return Material.RED_WOOL;
            case LIGHT_PURPLE: return Material.MAGENTA_WOOL;
            case YELLOW: return Material.YELLOW_WOOL;
            case WHITE: return Material.WHITE_WOOL;
            default: return Material.WHITE_WOOL;
        }
    }
}