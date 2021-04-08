package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(BackpackUtils.isBackpack(e.getCurrentItem()) && e.getView().getTitle().equals("Backpack")) e.setCancelled(true);
    }

}