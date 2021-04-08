package com.kixmc.backpacks.contents;

import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.ChatUtil;
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

            for(String loreLine : SimpleBackpacks.get().getConfig().getStringList("backpack.lore.empty")) {
                lore.add(ChatUtil.colorize(loreLine.replace("{SLOTS_IN_USE}", "0")).replace("{MAX_SLOTS}", Integer.toString(SimpleBackpacks.get().getConfig().getInt("backpack.rows") * 9)));
            }

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

                ArrayList<String> contentsPreview = new ArrayList<>();

                int previewSize = SimpleBackpacks.get().getConfig().getInt("backpack.lore.preview-slots-size");
                int counter = 0;
                for (int i = 1; i <= previewSize; i++) {
                    try {
                        contents.get(i);
                    } catch (Exception ignored) {
                        continue;
                    }

                    if(contents.get(i).getType() == Material.AIR) return;

                    counter++;

                    contentsPreview.add(ChatUtil.colorize(SimpleBackpacks.get().getConfig().getString("backpack.lore.contents-preview").replace("{ITEM_AMOUNT}", Integer.toString(contents.get(i).getAmount())).replace("{ITEM_NAME}", contents.get(i).getType().toString().replaceAll("_", " ").toLowerCase())));

                }

                if (contents.size() > previewSize) {
                    for(String loreLine : SimpleBackpacks.get().getConfig().getStringList("backpack.lore.preview-overflow")) {
                        contentsPreview.add(ChatUtil.colorize(loreLine).replace("{REMAINING_CONTENTS_SLOT_COUNT}", Integer.toString((contents.size() - counter))));
                    }
                }

                ArrayList<String> lore = new ArrayList<>();

                int index = 0;
                for(String loreLine : SimpleBackpacks.get().getConfig().getStringList("backpack.lore.storing")) {
                    if(loreLine.contains("{CONTENTS_PREVIEW}")) {
                        lore.addAll(index, contentsPreview);
                        continue;
                    }
                    lore.add(ChatUtil.colorize(loreLine.replace("{SLOTS_IN_USE}", Integer.toString(contents.size())).replace("{MAX_SLOTS}", Integer.toString(SimpleBackpacks.get().getConfig().getInt("backpack.rows") * 9))));
                    index++;
                }

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