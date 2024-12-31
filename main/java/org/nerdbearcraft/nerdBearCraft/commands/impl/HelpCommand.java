package org.nerdbearcraft.nerdBearCraft.commands.impl;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nerdbearcraft.nerdBearCraft.FormatedMessage;
import org.nerdbearcraft.nerdBearCraft.commands.AbstractCommand;

import java.util.List;

public class HelpCommand extends AbstractCommand {
    public HelpCommand() {
        super("nerdbearcraft", "Displays useful information about NBC", List.of("nbc", "nbcraft"));
    }

    @Override
    protected LiteralCommandNode<CommandSourceStack> node() {
        return Commands.literal(name)
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    FormatedMessage.playerMSG("Using NBC version 0.0.9 &7(&cUnstable Alpha&7)", player);

                    return 1;
                }).build();
    }

}
