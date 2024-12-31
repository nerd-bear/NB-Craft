package org.nerdbearcraft.nerdBearCraft.listeners;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.nerdbearcraft.nerdBearCraft.CustomItemData;
import org.nerdbearcraft.nerdBearCraft.EntityDamageCalculator;
import org.nerdbearcraft.nerdBearCraft.NerdBearCraft;

import java.util.Objects;


public class BondorWandEvent implements Listener {
    public void removeStand(ArmorStand stand) {
        new BukkitRunnable() {
            @Override
            public void run() {
                stand.remove();
                cancel();
            }

        }.runTaskLater(NerdBearCraft.getInstance(), 6);
    }

    public void startProjectileMovement(ArmorStand stand) {
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (i > 15) {
                    cancel();
                    stand.remove();
                    return;
                }

                Vector vec = stand.getLocation().getDirection();
                vec.normalize();
                vec.multiply(1);
                stand.setVelocity(vec);

                i++;
            }
        }.runTaskTimer(NerdBearCraft.getInstance(), 0L, 1L);
    }

    private void bondorWandAttackTwo(Player player) {
        Location eyeLocation = player.getEyeLocation();
        Vector directionVector = eyeLocation.getDirection();
        Location frontLocation = eyeLocation.add(directionVector);

        World world = player.getWorld();
        ArmorStand as = world.spawn(frontLocation, ArmorStand.class, armorStand -> {
            // armorStand.setGravity(true); Removed as gravity is enabled by default
            armorStand.setInvulnerable(false);
            armorStand.setSmall(true);
            // armorStand.setMarker(true); Removed as making an armor stand a Maker will break gravity and won't allow change in velocity
            armorStand.setVisible(false);
            Objects.requireNonNull(armorStand.getEquipment()).setHelmet(new ItemStack(Material.SEA_LANTERN));
            armorStand.setHeadPose(new EulerAngle(Math.random(), Math.random(), Math.random()));
        });

        startProjectileMovement(as);
    }

    private void bondorWandAttackOne(Player player, Location startLocation, Location endLocation) {
        Vector path = endLocation.clone().subtract(startLocation).toVector().normalize();
        double distance = startLocation.distance(endLocation);
        Integer damage = CustomItemData.getItemDamage(player.getEquipment().getItemInMainHand());

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (i > 10) {
                    cancel();
                    player.getWorld().spawnParticle(Particle.EXPLOSION, endLocation, 1);
                    return;
                }

                double progress = (i / 10.0) * distance;
                Location spawnLocation = startLocation.clone().add(path.clone().multiply(progress));

                Location headLocation = spawnLocation.clone().add(0, 1, 0);

                if (!headLocation.getWorld().getNearbyEntities(headLocation, 0.8f, 0.8f, 0.8f).isEmpty()) {
                    if (i <= 3) {
                        cont
                    }

                    player.getWorld().spawnParticle(Particle.EXPLOSION, headLocation, 1);
                    attackNearbyEntities(spawnLocation, player, damage);
                    cancel();
                    return;
                }

                if (headLocation.getBlock().getType().isSolid()) {
                    player.getWorld().spawnParticle(Particle.EXPLOSION, headLocation, 1);
                    attackNearbyEntities(spawnLocation, player, damage);
                    cancel();
                    return;
                }

                World world = player.getWorld();
                ArmorStand armorStand = world.spawn(spawnLocation, ArmorStand.class, as -> {
                    as.setGravity(true);
                    as.setInvulnerable(false);
                    as.setSmall(true);
                    as.setMarker(true);
                    as.setVisible(false);
                    as.getEquipment().setHelmet(new ItemStack(Material.SEA_LANTERN));
                    as.setHeadPose(new EulerAngle(Math.random(), Math.random(), Math.random()));
                });

                if (i == 10) {
                    attackNearbyEntities(spawnLocation, player, damage);
                }

                removeStand(armorStand);
                i++;
            }
        }.runTaskTimer(NerdBearCraft.getInstance(), 0L, 1L);
    }

    private void attackNearbyEntities(Location location, Player player, Integer damage) {
        for (Entity entity : location.getWorld().getNearbyEntities(location, 3f, 3f, 3f)) {
            if (entity instanceof ArmorStand || entity == player) {
                continue;
            }

            if (entity instanceof Damageable damageableEntity) {
                damageableEntity.damage(damage);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType().isAir()) {
            return;
        }

        if (item.getType() == Material.ALLIUM && Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Bondor Wand")) {
            event.setCancelled(true);

            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                Location eyeLocation = player.getEyeLocation();
                Vector direction = eyeLocation.getDirection().normalize();

                Location startLocation = player.getLocation().clone();
                startLocation.setY(startLocation.getY() - 0.3);

                Location explosionLocation = eyeLocation.clone().add(direction.multiply(10));
                player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 2.0f, 1.0f);
                bondorWandAttackOne(player, startLocation, explosionLocation);
            }
            else {
                player.playSound(player, Sound.BLOCK_END_PORTAL_FRAME_FILL, 2.0F, 1.0F);
                bondorWandAttackTwo(player);
            }
        }
    }

    @EventHandler
    public void onPlayerHitDamageableEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getType() == Material.ALLIUM && Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Bondor Wand")) {
                player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 2.0F, 1.0F);

                Integer damage = EntityDamageCalculator.calculateDamage(player, CustomItemData.getItemDamage(item));

                if (event.getEntity() instanceof Damageable damageableEntity) {
                    damageableEntity.damage(damage);
                    event.setCancelled(true);
                }
            }
        }
    }
}
