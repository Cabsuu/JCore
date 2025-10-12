package com.jerae.jcore.utils;

import net.md_5.bungee.api.ChatColor;
import java.awt.Color;

public class GradientUtils {

    public static String applyGradient(String text, String startHex, String endHex) {
        Color start = Color.decode(startHex);
        Color end = Color.decode(endHex);
        StringBuilder sb = new StringBuilder();

        int length = text.length();
        for (int i = 0; i < length; i++) {
            double ratio = (double) i / (length - 1);
            if (length == 1) {
                ratio = 0.5;
            }

            int r = (int) (start.getRed() * (1 - ratio) + end.getRed() * ratio);
            int g = (int) (start.getGreen() * (1 - ratio) + end.getGreen() * ratio);
            int b = (int) (start.getBlue() * (1 - ratio) + end.getBlue() * ratio);

            sb.append(ChatColor.of(new Color(r, g, b)));
            sb.append(text.charAt(i));
        }

        return sb.toString();
    }
}