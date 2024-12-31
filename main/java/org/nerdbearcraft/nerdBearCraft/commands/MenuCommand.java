package org.nerdbearcraft.nerdBearCraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MenuCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Inventory inv = Bukkit.createInventory(player, 9, ChatColor.DARK_GRAY + "Menu");

            ItemStack StatsItem = new ItemStack(Material.WRITTEN_BOOK);
            ItemMeta StatsItemMeta = StatsItem.getItemMeta();
            StatsItemMeta.setDisplayName(ChatColor.GRAY + player.getDisplayName() + " Stats");
            StatsItemMeta.setLore(List.of(ChatColor.WHITE + "Level: " + ChatColor.GRAY + "0"));
            StatsItemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
            StatsItem.setItemMeta(StatsItemMeta);

            inv.setItem(4, StatsItem);

            player.openInventory(inv);
            return true;
        }

        return false;
    }
}
