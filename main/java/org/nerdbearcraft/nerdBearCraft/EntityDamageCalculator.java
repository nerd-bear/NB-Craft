package org.nerdbearcraft.nerdBearCraft;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class EntityDamageCalculator {
    private static int calculateArmorDefense(EntityEquipment gear) {
        int defense = 0;
        for (ItemStack item : gear.getArmorContents()) {
            defense = defense + CustomItemData.getItemDefense(item);
        }

        return defense;
    }

    public static int calculateDamage(Player player, int damage) {
        int defense = calculateArmorDefense(player.getEquipment());
        return damage * (100/(defense+100));
    }

    public static boolean isEntityDead(int damage, int hp) {
        if (hp <= 0) {
            return true;
        } else if ((hp - damage) <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
