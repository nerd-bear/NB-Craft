package org.nerdbearcraft.nerdBearCraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuGuiEvent implements Listener {
    @EventHandler
    public void onMenuGuiClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Menu")) {
            event.setCancelled(true);
        }
    }
}
