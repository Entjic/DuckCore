package de.entjic.builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String itemStackDisplayName) {
        itemMeta.setDisplayName(itemStackDisplayName);
        return this;
    }

    public ItemBuilder setDisplayName(ChatColor itemStackNameColor, String itemStackDisplayName) {
        itemMeta.setDisplayName(itemStackNameColor + itemStackDisplayName);
        return this;
    }

    public ItemBuilder setAmount(int itemStackAmount) {
        itemStack.setAmount(itemStackAmount);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean isUnbreakable) {
        itemMeta.setUnbreakable(isUnbreakable);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment itemStackEnchantment, int enchantLevel, boolean ignoreEnchantMax) {
        itemMeta.addEnchant(itemStackEnchantment, enchantLevel, ignoreEnchantMax);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment itemStackEnchantment) {
        if (itemMeta.hasEnchant(itemStackEnchantment)) {
            itemMeta.removeEnchant(itemStackEnchantment);
        }
        return this;
    }

    public ItemBuilder setLore(List<String> itemStackLore) {
        itemMeta.setLore(itemStackLore);
        return this;
    }

    public ItemStack buildItemStack() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
