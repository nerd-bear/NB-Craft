package org.nerdbearcraft.nerdBearCraft;

import org.bukkit.ChatColor;

public class CustomColor {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
