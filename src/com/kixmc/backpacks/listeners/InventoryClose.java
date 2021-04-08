package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.contents.ItemHandler;
import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        if (e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()) == null) return;

        if (BackpackUtils.isBackpack(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot())) && e.getView().getTitle().equals(SimpleBackpacks.get().getConfig().getString("backpack.gui-title"))) {

            Inventory dummyInventory = Bukkit.createInventory(e.getPlayer(), 54, "");
            Arrays.stream(e.getInventory().getContents()).filter(Objects::nonNull).forEach(dummyInventory::addItem);

            ArrayList<ItemStack> tidiedContents = new ArrayList<>();

            Arrays.stream(dummyInventory.getContents()).filter(Objects::nonNull).forEach(tidiedContents::add);

            ItemHandler.store(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()), tidiedContents);

        }
    }

}
