package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.contents.ItemHandler;
import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (!e.hasItem()) return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack is = e.getItem();

            if (BackpackUtils.isBackpack(is)) {

                ArrayList<ItemStack> contents = ItemHandler.get(is);

                Inventory backpack = Bukkit.createInventory(e.getPlayer(), 18, "Backpack");

                contents.stream()
                        .forEach(itemStack -> backpack.addItem(itemStack));

                e.getPlayer().openInventory(backpack);

            }

        }
    }

}
