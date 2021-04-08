package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.contents.ItemHandler;
import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        if (e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()) == null) return;

        if (BackpackUtils.isBackpack(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()))) {

            ArrayList<ItemStack> contents = new ArrayList<>();

            Arrays.stream(e.getInventory().getContents())
                    .filter(Objects::nonNull)
                    .forEach(contents::add);

            ItemHandler.store(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()), contents);

        }
    }

}
