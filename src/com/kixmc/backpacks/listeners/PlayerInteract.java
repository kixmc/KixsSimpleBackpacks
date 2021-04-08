package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.contents.ItemHandler;
import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

                is.setType(Material.valueOf(SimpleBackpacks.get().getConfig().getString("backpack.material")));

                ArrayList<ItemStack> contents = ItemHandler.get(is);

                Inventory backpack = Bukkit.createInventory(e.getPlayer(), (SimpleBackpacks.get().getConfig().getInt("backpack.rows") * 9), SimpleBackpacks.get().getConfig().getString("backpack.gui-title"));

                ArrayList<ItemStack> itemOverflow = new ArrayList<>();

                for (ItemStack itemStack : contents) {
                    if (backpack.addItem(itemStack).isEmpty()) continue;
                    itemOverflow.add(itemStack);
                }

                for (ItemStack itemStack : itemOverflow) {
                    e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(), itemStack);
                }

                e.getPlayer().openInventory(backpack);

            }

        }
    }

}
