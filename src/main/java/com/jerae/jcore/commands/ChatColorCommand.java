package com.jerae.jcore.commands;

import com.jerae.jcore.JCore;
import com.jerae.jcore.guis.ChatColorGUI;
import com.jerae.jcore.utils.PermissionUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatColorCommand implements CommandExecutor {

    private final JCore plugin;

    public ChatColorCommand(JCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (PermissionUtils.hasPermission(player, "chatcolor.gui", "&cYou do not have permission to use this command.")) {
            ChatColorGUI.open(player, plugin);
        }

        return true;
    }
}