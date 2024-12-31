package org.nerdbearcraft.nerdBearCraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + event.getPlayer().getDisplayName());

        player.teleport(event.getPlayer().getWorld().getSpawnLocation());

        if (!player.hasPlayedBefore()) {
            Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "-------------------------------------------");
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + player.getDisplayName() + " has joined for the first time! Welcome them!");
            Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "-------------------------------------------");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + event.getPlayer().getDisplayName());
    }
}
