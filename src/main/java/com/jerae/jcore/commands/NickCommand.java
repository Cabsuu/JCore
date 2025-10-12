package com.jerae.jcore.commands;

import com.jerae.jcore.utils.ChatColorUtils;
import com.jerae.jcore.utils.PermissionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (!PermissionUtils.hasPermission(player, "nick", "&cYou do not have permission to use this command.")) {
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColorUtils.translate("&cUsage: /nick <nickname|_reset>"));
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("-reset")) {
            player.setDisplayName(player.getName());
            player.sendMessage(ChatColorUtils.translate("&aYour nickname has been reset."));
            return true;
        }

        String nick = String.join(" ", args);

        if (nick.matches(".*&[a-f0-9].*") && !PermissionUtils.hasPermission(player, "nick.color", "&cYou do not have permission to use this color.")) {
            return true;
        }

        if (nick.matches(".*&[k-o].*") && !PermissionUtils.hasPermission(player, "nick.format", "&cYou do not have permission to use this text style.")) {
            return true;
        }

        if (nick.contains("<gradient:") && !PermissionUtils.hasPermission(player, "nick.gradient", "&cYou do not have permission to use this feature.")) {
            return true;
        }

        if (nick.contains("#") && !PermissionUtils.hasPermission(player, "nick.hex", "&cYou do not have permission to use this feature.")) {
            return true;
        }

        player.setDisplayName(ChatColorUtils.translate(nick));
        player.sendMessage(ChatColorUtils.translate("&aYour nickname has been set to: " + nick));

        return true;
    }
}