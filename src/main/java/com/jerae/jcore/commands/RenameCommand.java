package com.jerae.jcore.commands;

import com.jerae.jcore.utils.ChatColorUtils;
import com.jerae.jcore.utils.PermissionUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (!PermissionUtils.hasPermission(player, "rename", "&cYou do not have permission to use this command.")) {
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColorUtils.translate("&cUsage: /rename <name|-reset>"));
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        if (args.length == 1 && args[0].equalsIgnoreCase("-reset")) {
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(null);
            item.setItemMeta(meta);
            player.sendMessage(ChatColorUtils.translate("&aItem name has been reset."));
            return true;
        }

        if (item.getType() == Material.AIR) {
            sender.sendMessage(ChatColorUtils.translate("&cYou must be holding an item to rename it."));
            return true;
        }

        String name = String.join(" ", args);

        if (name.matches(".*&[a-f0-9].*") && !PermissionUtils.hasPermission(player, "rename.color", "&cYou do not have permission to use this color.")) {
            return true;
        }

        if (name.matches(".*&[k-o].*") && !PermissionUtils.hasPermission(player, "rename.format", "&cYou do not have permission to use this text style.")) {
            return true;
        }

        if (name.matches("<#([A-Fa-f0-9]{6}):#([A-Fa-f0-9]{6})>.*") && !PermissionUtils.hasPermission(player, "rename.gradient", "&cYou do not have permission to use this feature.")) {
            return true;
        }

        if (name.contains("#") && !PermissionUtils.hasPermission(player, "rename.hex", "&cYou do not have permission to use this feature.")) {
            return true;
        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColorUtils.translate(name));
        item.setItemMeta(meta);

        player.sendMessage(ChatColorUtils.translate("&aItem renamed to: " + name));

        return true;
    }
}