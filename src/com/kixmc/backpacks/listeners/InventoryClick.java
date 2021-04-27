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

        if(e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString(ChatUtil.colorize("backpack.gui-title")))) {
            if(e.getClick() == ClickType.NUMBER_KEY) e.setCancelled(true);
            if(e.getCurrentItem() == null) return;
            if(e.getCurrentItem().getType().toString().contains("SHULKER_BOX") && !SimpleBackpacks.get().getConfig().getBoolean("backpack.allow-shulker-boxes-in-backpacks")) e.setCancelled(true);
        }

        if (BackpackUtils.isBackpack(e.getCurrentItem())) {
            if(e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString(ChatUtil.colorize("backpack.gui-title")))) e.setCancelled(true);
            if(e.getInventory().getType() == InventoryType.SHULKER_BOX) {
                if(e.getClick() == ClickType.NUMBER_KEY) e.setCancelled(true);
                if(e.getClickedInventory().getType() == InventoryType.SHULKER_BOX) return; // allow taking backpacks out of shulker boxes in case of settings change
                if (!SimpleBackpacks.get().getConfig().getBoolean("backpack.allow-backpacks-in-shulker-boxes")) e.setCancelled(true);
            }
        }

        if (!(e.getWhoClicked() instanceof Player)) return;

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getInventory().getType() != InventoryType.ANVIL) return;

        if (e.getSlotType() == InventoryType.SlotType.RESULT && BackpackUtils.isUnopenedBackpack(e.getCurrentItem())) e.setCancelled(true);

    }

}