package org.nerdbearcraft.nerdBearCraft.commands.impl;

import com.mojang.brigadier.arguments.IntegerArgumentType;
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

public class ItemDamageCommand extends AbstractCommand {
    public ItemDamageCommand() {
        super("itemdamage", "Can fetch and get the damage of items", List.of());
    }

    @Override
    protected LiteralCommandNode<CommandSourceStack> node() {
        return Commands.literal(name)
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();

                    if (!(sender instanceof Player player)) {
                        return 1;
                    }

                    FormatedMessage.playerMSG("&cUsage: (/itemdamage set <damage> | /itemdamage get)", player);

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

                    FormatedMessage.playerMSG("&cUsage: /itemdamage set <damage>", player);

                    return 0;
                }).then(setDamage());
    }

    private RequiredArgumentBuilder<CommandSourceStack, Integer> setDamage() {
        return Commands.argument("damage", IntegerArgumentType.integer())
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

                    int item_damage = IntegerArgumentType.getInteger(context, "damage");

                    CustomItemData.setItemDamage(item, item_damage);

                    FormatedMessage.playerMSG("Set item damage to &a" + item_damage, player);

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

                    Integer item_damage = CustomItemData.getItemDamage(item);

                    FormatedMessage.playerMSG("Item damage of this is &a" + item_damage, player);

                    return 0;
                });
    }
}