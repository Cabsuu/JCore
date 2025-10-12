package com.jerae.jcore.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatColorGUI {
    private static final Map<Integer, ChatColor> slotToColor = new HashMap<>();

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
    }

    public static ChatColor getColor(int slot) {
        return slotToColor.get(slot);
    }

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, "Chat Color");

        // Add vanilla color items
        addVanillaColor(gui, 0, Material.BLACK_WOOL, ChatColor.BLACK, "Black");
        addVanillaColor(gui, 1, Material.BLUE_WOOL, ChatColor.DARK_BLUE, "Dark Blue");
        addVanillaColor(gui, 2, Material.GREEN_WOOL, ChatColor.DARK_GREEN, "Dark Green");
        addVanillaColor(gui, 3, Material.CYAN_WOOL, ChatColor.DARK_AQUA, "Dark Aqua");
        addVanillaColor(gui, 4, Material.RED_WOOL, ChatColor.DARK_RED, "Dark Red");
        addVanillaColor(gui, 5, Material.PURPLE_WOOL, ChatColor.DARK_PURPLE, "Dark Purple");
        addVanillaColor(gui, 6, Material.ORANGE_WOOL, ChatColor.GOLD, "Gold");
        addVanillaColor(gui, 7, Material.LIGHT_GRAY_WOOL, ChatColor.GRAY, "Gray");
        addVanillaColor(gui, 8, Material.GRAY_WOOL, ChatColor.DARK_GRAY, "Dark Gray");
        addVanillaColor(gui, 9, Material.LIGHT_BLUE_WOOL, ChatColor.BLUE, "Blue");
        addVanillaColor(gui, 10, Material.LIME_WOOL, ChatColor.GREEN, "Green");
        addVanillaColor(gui, 11, Material.LIGHT_BLUE_WOOL, ChatColor.AQUA, "Aqua");
        addVanillaColor(gui, 12, Material.RED_WOOL, ChatColor.RED, "Red");
        addVanillaColor(gui, 13, Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE, "Light Purple");
        addVanillaColor(gui, 14, Material.YELLOW_WOOL, ChatColor.YELLOW, "Yellow");
        addVanillaColor(gui, 15, Material.WHITE_WOOL, ChatColor.WHITE, "White");

        // Add hex and gradient options
        addSpecialOption(gui, 48, Material.ANVIL, "Hex Color", "Enter a hex code");
        addSpecialOption(gui, 50, Material.BUCKET, "Gradient Color", "Select two colors");

        player.openInventory(gui);
    }

    private static void addVanillaColor(Inventory gui, int slot, Material material, ChatColor color, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        List<String> lore = new ArrayList<>();
        lore.add("Click to select this color");
        meta.setLore(lore);
        item.setItemMeta(meta);
        gui.setItem(slot, item);
    }

    private static void addSpecialOption(Inventory gui, int slot, Material material, String name, String description) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + name);
        List<String> lore = new ArrayList<>();
        lore.add(description);
        meta.setLore(lore);
        item.setItemMeta(meta);
        gui.setItem(slot, item);
    }
}