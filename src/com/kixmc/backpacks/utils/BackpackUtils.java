package com.kixmc.backpacks.utils;

import com.kixmc.backpacks.core.SimpleBackpacks;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BackpackUtils {

    public static boolean isBackpack(ItemStack is) {

        if (is == null) return false;
        if (is.getType() == Material.AIR) return false;

        return hasKey(is, "kixs-backpacks", PersistentDataType.STRING);

    }

    public static boolean isUnopenedBackpack(ItemStack is) {

        if (is == null) return false;
        if (is.getType() == Material.AIR) return false;

        return hasKey(is, "kixs-backpacks-new", PersistentDataType.STRING);
    }

    public static boolean hasKey(ItemStack is, String targetKey, PersistentDataType type) {
        NamespacedKey key = new NamespacedKey(SimpleBackpacks.get(), targetKey);
        ItemMeta itemMeta = is.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        boolean foundKey = false;

        if (container.has(key, type)) {
            foundKey = container.get(key, type) != null;
        }

        return foundKey;
    }

}
