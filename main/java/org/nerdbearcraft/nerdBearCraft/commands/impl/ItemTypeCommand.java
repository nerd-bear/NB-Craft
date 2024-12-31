package org.nerdbearcraft.nerdBearCraft.commands.impl;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.nerdbearcraft.nerdBearCraft.CustomItemData;
import org.nerdbearcraft.nerdBearCraft.FormatedMessage;
import org.nerdbearcraft.nerdBearCraft.commands.AbstractCommand;

import java.util.List;

public class ItemTypeCommand extends AbstractCommand {
    public ItemTypeCommand() {
        super("itemtype", "Can fetch and get the type of items", List.of());
    }

    @Override
    protected LiteralCommandNode<CommandSourceStack> node() {
        return Commands.literal(name)
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    FormatedMessage.playerMSG("&cUsage: (/itemtype set <type> | /itemtype get)", player);

                    return 0;
                })
                .then(set())
                .then(get())
                .build();
    }

    private LiteralArgumentBuilder<CommandSourceStack> set() {
        return Commands.literal("set")
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    FormatedMessage.playerMSG("&cUsage: /itemtype set <type>", player);

                    return 0;
                }).then(setType());
    }

    private RequiredArgumentBuilder<CommandSourceStack, String> setType() {
        return Commands.argument("type", StringArgumentType.word())
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    ItemStack item = player.getEquipment().getItemInMainHand();

                    if (item.getType().isAir()) {
                        FormatedMessage.playerMSG("&cYou must be holding an item", player);

                        return 1;
                    }

                    String item_type = StringArgumentType.getString(context, "type");

                    CustomItemData.setItemType(item, item_type);

                    FormatedMessage.playerMSG("Set item type to &a" + item_type, player);

                    return 0;
                });
    }

    private LiteralArgumentBuilder<CommandSourceStack> get() {
        return Commands.literal("get")
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    ItemStack item = player.getEquipment().getItemInMainHand();

                    if (item.getType().isAir()) {
                        FormatedMessage.playerMSG("&cYou must be holding an item", player);

                        return 1;
                    }

                    String item_type = CustomItemData.getItemType(item);

                    FormatedMessage.playerMSG("Item type of this is &a" + (item_type == null ? "&cnull" : item_type), player);

                    return 0;
                });
    }
}