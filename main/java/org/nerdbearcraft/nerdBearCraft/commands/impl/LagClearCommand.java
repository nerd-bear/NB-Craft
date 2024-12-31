package org.nerdbearcraft.nerdBearCraft.commands.impl;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.nerdbearcraft.nerdBearCraft.FormatedMessage;
import org.nerdbearcraft.nerdBearCraft.commands.AbstractCommand;

import java.util.List;

public class LagClearCommand extends AbstractCommand {
    public LagClearCommand() {
        super("lagclear", "Does fancy stuff to get rid of lag", List.of());
    }

    @Override
    protected LiteralCommandNode<CommandSourceStack> node() {
        return Commands.literal(name)
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    FormatedMessage.playerMSG("Starting Lag Clear Process", ((Player) sender));

                    List<World> worlds = player.getServer().getWorlds();
                    Integer deleted_items = 0;

                    for (World world : worlds) {
                        List<Entity> entityList = world.getEntities();

                        for (Entity current : entityList) {
                            if (current instanceof Item) {
                                current.remove();
                                deleted_items++;
                            }
                        }
                    }

                    for (World world : worlds) {
                        Chunk[] worldChucks = world.getLoadedChunks();

                        for (Chunk chunk : worldChucks) {
                            Integer entityCount = chunk.getEntities().length;

                            if (entityCount > 500) {
                                FormatedMessage.playerMSG("&cWARNING! Found " + entityCount + " entities in a chunk &7(&fX: &a" + chunk.getX() * 16 + "&7, &fZ: &a" + chunk.getZ() * 16 + "&7)", player);
                            }
                        }
                    }

                    FormatedMessage.playerMSG("Deleted " + deleted_items + " items from " + worlds.size() + " world(s)", ((Player) sender));

                    return 0;
                }).build();
    }

}
