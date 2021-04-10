package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent e) {

        if (BackpackUtils.isBackpack(e.getPlayer().getInventory().getItemInMainHand())) e.setCancelled(true);
        if (BackpackUtils.isBackpack(e.getPlayer().getInventory().getItemInOffHand())) e.setCancelled(true);

    }

}