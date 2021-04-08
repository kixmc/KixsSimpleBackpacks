package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackUtils;
import com.kixmc.backpacks.utils.ChatUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PrepareAnvil implements Listener {

    @EventHandler
    public void onPrepare(PrepareAnvilEvent e) {

        if(BackpackUtils.isBackpack(e.getResult())) {

            ItemMeta im = e.getResult().getItemMeta();

            im.setDisplayName(ChatUtil.colorize(SimpleBackpacks.get().getConfig().getString("backpack.name.renamed").replace("{CUSTOM_NAME}", e.getInventory().getRenameText())));
            im.getPersistentDataContainer().set(new NamespacedKey(SimpleBackpacks.get(),"kixs-backpacks-custom-name"), PersistentDataType.STRING, e.getInventory().getRenameText());

            ItemStack result = e.getResult();

            result.setItemMeta(im);
            e.setResult(result);

        }

    }

}
