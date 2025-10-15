package com.jerae.jcore.commands;

import com.jerae.jcore.JCore;
import com.jerae.jcore.utils.ChatColorUtils;
import com.jerae.jcore.utils.PermissionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JcCommand implements CommandExecutor {

    private final JCore plugin;

    public JcCommand(JCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!PermissionUtils.hasPermission(player, "jc.reload", "&cYou do not have permission to use this command.")) {
                    return true;
                }
            }

            plugin.reloadConfig();
            sender.sendMessage(ChatColorUtils.translate("&aJCore configuration has been reloaded."));
            return true;
        }

        sender.sendMessage(ChatColorUtils.translate("&cUsage: /jc reload"));
        return true;
    }
}