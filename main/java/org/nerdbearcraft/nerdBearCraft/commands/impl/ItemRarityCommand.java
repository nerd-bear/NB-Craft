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

public class ItemRarityCommand extends AbstractCommand {
    public ItemRarityCommand() {
        super("itemrarity", "Can fetch and get the rarity of items", List.of());
    }

    @Override
    protected LiteralCommandNode<CommandSourceStack> node() {
        return Commands.literal(name)
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    FormatedMessage.playerMSG("&cUsage: (/itemrarity set <rarity> | /itemrarity get)", player);

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

                    FormatedMessage.playerMSG("&cUsage: /itemrarity set <rarity>", player);

                    return 0;
                }).then(setRarity());
    }

    private RequiredArgumentBuilder<CommandSourceStack, String> setRarity() {
        return Commands.argument("rarity", StringArgumentType.word())
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

                    String item_rarity = StringArgumentType.getString(context, "rarity");

                    CustomItemData.setItemRarity(item, item_rarity);

                    FormatedMessage.playerMSG("Set item rarity to &a" + item_rarity, player);

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

                    String item_rarity = CustomItemData.getItemRarity(item);

                    FormatedMessage.playerMSG("Item rarity of this is &a" + item_rarity, player);

                    return 0;
                });
    }
}