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

        if (!PermissionUtils.hasPermission(player, "rename")) {
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColorUtils.translate("&cUsage: /rename <name>"));
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.AIR) {
            sender.sendMessage(ChatColorUtils.translate("&cYou must be holding an item to rename it."));
            return true;
        }

        String name = String.join(" ", args);

        if (name.matches(".*&[a-f0-9].*") && !PermissionUtils.hasPermission(player, "rename.color")) {
            return true;
        }

        if (name.matches(".*&[k-o].*") && !PermissionUtils.hasPermission(player, "rename.format")) {
            return true;
        }

        if (name.contains("<gradient:") && !PermissionUtils.hasPermission(player, "rename.gradient")) {
            return true;
        }

        if (name.contains("#") && !PermissionUtils.hasPermission(player, "rename.hex")) {
            return true;
        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColorUtils.translate(name));
        item.setItemMeta(meta);

        player.sendMessage(ChatColorUtils.translate("&aItem renamed to: " + name));

        return true;
    }
}