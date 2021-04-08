package com.kixmc.backpacks.contents;

import com.kixmc.backpacks.core.SimpleBackpacks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ItemHandler {

    public static void store(ItemStack backpack, List<ItemStack> contents) {

        if (!backpack.hasItemMeta()) return;

        ItemMeta itemMeta = backpack.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        if (!data.has(new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks"), PersistentDataType.STRING, "");
        }

        if (contents.size() == 0) {
            data.set(new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks"), PersistentDataType.STRING, "");

            ArrayList<String> lore = new ArrayList<>();

            lore.add(" ");
            lore.add(ChatColor.GOLD + "0/18 " + ChatColor.WHITE + "slots in use");
            lore.add(" ");

            itemMeta.setLore(lore);

            backpack.setItemMeta(itemMeta);
        } else {

            try {

                ByteArrayOutputStream io = new ByteArrayOutputStream();
                BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

                os.writeInt(contents.size());

                for (ItemStack item : contents) {
                    os.writeObject(item);
                }

                os.flush();

                byte[] rawData = io.toByteArray();

                String encodedData = Base64.getEncoder().encodeToString(rawData);

                data.set(new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks"), PersistentDataType.STRING, encodedData);

                ArrayList<String> lore = new ArrayList<>();

                lore.add(" ");
                lore.add(ChatColor.GOLD + "" + contents.size() + "/18 " + ChatColor.WHITE + "slots in use");
                lore.add(" ");

                int counter = 0;
                for (int i = 0; i <= 5; i++) {
                    try {
                        contents.get(i);
                    } catch (Exception ex) {
                        continue;
                    }
                    if(contents.get(i).getType() == Material.AIR) return;
                    counter++;
                    lore.add(ChatColor.WHITE + " - " + ChatColor.GRAY + "" + ChatColor.ITALIC + "" + contents.get(i).getAmount() + "x" + ChatColor.WHITE + " " + ChatColor.ITALIC + contents.get(i).getType().toString().replaceAll("_", " ").toLowerCase());
                }

                if (contents.size() > 5) {
                    lore.add(" ");
                    lore.add(ChatColor.WHITE + " " + ChatColor.WHITE + "" + ChatColor.ITALIC + "and " + ChatColor.GRAY + (contents.size() - counter) + ChatColor.WHITE + "" + ChatColor.ITALIC + "more... ");
                }

                lore.add(" ");

                itemMeta.setLore(lore);

                backpack.setItemMeta(itemMeta);

                os.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    public static ArrayList<ItemStack> get(ItemStack backpack) {

        if (!backpack.hasItemMeta()) new ArrayList<ItemStack>();

        ItemMeta itemMeta = backpack.getItemMeta();
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();

        ArrayList<ItemStack> items = new ArrayList<>();

        String encodedItems = data.get(new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks"), PersistentDataType.STRING);

        if (!encodedItems.isEmpty()) {

            byte[] rawData = Base64.getDecoder().decode(encodedItems);

            try {

                ByteArrayInputStream io = new ByteArrayInputStream(rawData);
                BukkitObjectInputStream in = new BukkitObjectInputStream(io);

                int itemsCount = in.readInt();

                for (int i = 0; i < itemsCount; i++) {
                    items.add((ItemStack) in.readObject());
                }

                in.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        return items;
    }

}