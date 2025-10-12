package com.jerae.jcore.utils;

import org.bukkit.command.CommandSender;

public class PermissionUtils {

    public static boolean hasPermission(CommandSender sender, String permission) {
        if (sender.hasPermission("jcore." + permission)) {
            return true;
        } else {
            sender.sendMessage(ChatColorUtils.translate("&cYou do not have permission to use this feature."));
            return false;
        }
    }
}