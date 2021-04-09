package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent e) {

        if (e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()) == null) return;

        if (BackpackUtils.isBackpack(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()))) e.setCancelled(true);

    }

}