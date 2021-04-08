package com.kixmc.backpacks.utils;

import com.kixmc.backpacks.core.SimpleBackpacks;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BackpackUtils {

    public static boolean isBackpack(ItemStack is) {

        NamespacedKey key = new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks");
        ItemMeta itemMeta = is.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        boolean foundKey = false;

        if (container.has(key, PersistentDataType.STRING)) {
            foundKey = container.get(key, PersistentDataType.STRING) != null;
        }

        if (foundKey) {
            return true;
        }

        return false;
    }

}
