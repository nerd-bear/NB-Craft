package org.nerdbearcraft.nerdBearCraft;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CustomItemData {
    public static String getItemType(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_type");

        String itemType = pdc.get(key, PersistentDataType.STRING);

        return itemType;
    }

    public static void setItemType(ItemStack itemStack, String itemType) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_type");
        pdc.set(key, PersistentDataType.STRING, itemType);

        itemStack.setItemMeta(meta);
    }

    public static Integer getItemDamage(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_damage");

        Integer itemDamage = pdc.get(key, PersistentDataType.INTEGER);

        return itemDamage == null ? 1 : itemDamage;
    }

    public static void setItemDamage(ItemStack itemStack, int damage) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_damage");
        pdc.set(key, PersistentDataType.INTEGER, damage);

        itemStack.setItemMeta(meta);
    }

    public static String getItemRarity(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_rarity");

        String itemRarity = pdc.get(key, PersistentDataType.STRING);

        return itemRarity == null ? "common" : itemRarity;
    }

    public static void setItemRarity(ItemStack itemStack, String rarity) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_rarity");
        pdc.set(key, PersistentDataType.STRING, rarity);

        itemStack.setItemMeta(meta);
    }

    public static Integer getItemDefense(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_defense");

        Integer defense = pdc.get(key, PersistentDataType.INTEGER);

        return defense == null ? 0 : defense;
    }

    public static void setItemDefense(ItemStack itemStack, Integer defense) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), "item_defense");
        pdc.set(key, PersistentDataType.INTEGER, defense);

        itemStack.setItemMeta(meta);
    }

    public static String getCustomField(ItemStack itemStack, String field) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), field);

        String customFieldData = pdc.get(key, PersistentDataType.STRING);

        return customFieldData == null ? "none" : customFieldData;
    }

    public static void setCustomField(ItemStack itemStack, String field, String data) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(NerdBearCraft.getInstance(), field);
        pdc.set(key, PersistentDataType.STRING, data);

        itemStack.setItemMeta(meta);
    }
}