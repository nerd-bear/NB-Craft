package org.nerdbearcraft.nerdBearCraft.commands.impl;

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

public class ItemDataCommand extends AbstractCommand {
    public ItemDataCommand() {
        super("itemdata", "Can fetch the data of items", List.of());
    }

    @Override
    protected LiteralCommandNode<CommandSourceStack> node() {
        return Commands.literal(name)
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

                    String itemType = CustomItemData.getItemType(item);

                    if (itemType == null) {
                        FormatedMessage.playerMSG("&cItem has no type and can not retrieve data", player);
                    } else if (itemType.equals("weapon")) {
                        FormatedMessage.playerMSG("Type: &a" + itemType + " &fRarity: &a" + CustomItemData.getItemRarity(item) + " &fDamage: &a" + CustomItemData.getItemDamage(item), player);
                    } else if (itemType.equals("armor")) {
                        FormatedMessage.playerMSG("Type: &a" + itemType + " &fRarity: &a" + CustomItemData.getItemRarity(item) + " &fDefense: &a" + CustomItemData.getItemDefense(item), player);
                    } else {
                        FormatedMessage.playerMSG("&cType: UNKNOWN", player);
                    }

                    return 0;
                })
                .build();
    }
}