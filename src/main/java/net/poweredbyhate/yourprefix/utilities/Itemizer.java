package net.poweredbyhate.yourprefix.utilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Lax on 12/12/2016.
 */
public class Itemizer {

    public ItemStack createItem(Material mat, int amt, int shrt, String displayname, String lore) {
        ItemStack i = new ItemStack(mat, amt, (short) shrt);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));

        ArrayList<String> loreList = new ArrayList<String>();
        String[] lores = lore.split("~");
        for (String s: lores) {
            loreList.add(ChatColor.translateAlternateColorCodes('&', s));
        }

        im.setLore(loreList);
        i.setItemMeta(im);
        return i;
    }

    public ItemStack createItem(Material material, int amount) {
        return new ItemStack(material, amount);
    }

    public ItemStack setItemName(ItemStack itemStack, String s) {
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
        itemStack.setItemMeta(im);
        return itemStack;
    }

    public ItemStack setItemLore(ItemStack itemStack, String lore) {
        ItemMeta im = itemStack.getItemMeta();
        ArrayList<String> loreList = new ArrayList<String>();
        String[] lores = lore.split("~");
        loreList.addAll(Arrays.asList(lores));
        itemStack.setItemMeta(im);
        return itemStack;
    }

    public ItemStack addGlow(ItemStack is) {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(Enchantment.OXYGEN, 1, true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(im);
        return is;
    }
}
