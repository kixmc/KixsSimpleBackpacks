package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackUtils;
import com.kixmc.backpacks.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (BackpackUtils.isBackpack(e.getCurrentItem()) && e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString("backpack.gui-title"))) e.setCancelled(true);

        if (!(e.getWhoClicked() instanceof Player)) return;

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getInventory().getType() != InventoryType.ANVIL) return;

        if (e.getSlotType() == InventoryType.SlotType.RESULT) {

            if (!BackpackUtils.isBackpack(e.getCurrentItem())) return;

            ItemMeta im = e.getCurrentItem().getItemMeta();

            im.setDisplayName(ChatUtil.colorize(SimpleBackpacks.get().getConfig().getString("backpack.name.renamed").replace("{CUSTOM_NAME}", e.getCurrentItem().getItemMeta().getDisplayName())));

            e.getCurrentItem().setItemMeta(im);

        }

    }

}