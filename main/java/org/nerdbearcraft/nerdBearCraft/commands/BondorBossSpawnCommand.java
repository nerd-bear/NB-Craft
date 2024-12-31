package org.nerdbearcraft.nerdBearCraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Player;

public class BondorBossSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawnbondorboss")) {
            if (sender instanceof Player player) {
                PiglinBrute bordorEntity = (PiglinBrute) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIGLIN_BRUTE);

                bordorEntity.setCustomNameVisible(true);
                bordorEntity.setCustomName("Bondor");
                bordorEntity.setGlowing(true);
                bordorEntity.setCustomNameVisible(true);
                bordorEntity.setCustomName(ChatColor.LIGHT_PURPLE + "Bondor");
                bordorEntity.setAI(false);

                return true;
            }

            return false;
        }
        return false;
    }
}