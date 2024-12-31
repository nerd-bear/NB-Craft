package org.nerdbearcraft.nerdBearCraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class VanillaMobSpawnEvent implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Mob) {
            Mob mob = (Mob) entity;

            String health = String.valueOf(((int) mob.getHealth()));

            String mobName = "&f%mob_name% &7[&f%mob_hp%&7]".replace("%mob_name%", mob.getName()).replace("%mob_hp%", health);
            setCustomNameVisible(mob, mobName);
        }
    }

    private void setCustomNameVisible(Mob mob, String name) {
        mob.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        mob.setCustomNameVisible(true);
    }
}