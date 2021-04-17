package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackUtils;
import com.kixmc.backpacks.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClick implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(InventoryClickEvent e) {

        if(e.getClick() == ClickType.NUMBER_KEY && e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString(ChatUtil.colorize("backpack.gui-title")))) {
            e.setCancelled(true);
            return;
        }

        if (BackpackUtils.isBackpack(e.getCurrentItem()) && e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString(ChatUtil.colorize("backpack.gui-title")))) {
            e.setCancelled(true);
            return;
        }

        if (!(e.getWhoClicked() instanceof Player)) return;

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getInventory().getType() != InventoryType.ANVIL) return;

        if (e.getSlotType() == InventoryType.SlotType.RESULT && BackpackUtils.isUnopenedBackpack(e.getCurrentItem())) e.setCancelled(true);

    }

}