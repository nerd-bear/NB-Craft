package org.nerdbearcraft.nerdBearCraft;

// Simple class to manage Plugin Formated Messages to send to players, console or global context

import org.bukkit.entity.Player;

public class FormatedMessage {
    public static void playerMSG(String message, Player player) {
        String msg_prefix = CustomColor.translate(NerdBearCraft.getInstance().getConfig().getString("messages.game-prefix"));
        player.sendMessage(msg_prefix + " " + CustomColor.translate(message));
    }

    public static void consoleMSG(String message) {
        String prefix = CustomColor.translate(NerdBearCraft.getInstance().getConfig().getString("messages.console-prefix"));
        NerdBearCraft.getInstance().getServer().getConsoleSender().sendMessage(prefix + " " + message);
    }
}
