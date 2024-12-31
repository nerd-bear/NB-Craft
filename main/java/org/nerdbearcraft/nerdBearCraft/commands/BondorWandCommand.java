package org.nerdbearcraft.nerdBearCraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.nerdbearcraft.nerdBearCraft.CustomItemData;
import org.nerdbearcraft.nerdBearCraft.FormatedMessage;
import org.nerdbearcraft.nerdBearCraft.NerdBearCraft;

public class BondorWandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("bondorwand")) {
            if (sender instanceof Player player) {
                ItemStack wand = new ItemStack(Material.ALLIUM);
                ItemMeta meta = wand.getItemMeta();
                meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Bondor Wand");
//                CustomItemData.setItemType(wand, "weapon");
//                CustomItemData.setItemDamage(wand, 20);
//                CustomItemData.setItemRarity(wand, "mythic");
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_type");
                pdc.set(key, PersistentDataType.STRING, "weapon");
                wand.setItemMeta(meta);

                player.getInventory().addItem(wand);
                FormatedMessage.playerMSG("You have been given a " + wand.getItemMeta().displayName(), player);
                return true;
            }
        }
        return false;
    }
}
