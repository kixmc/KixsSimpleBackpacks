package com.kixmc.backpacks.listeners;

import com.kixmc.backpacks.core.SimpleBackpacks;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        NamespacedKey recipe = new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks");
        if(e.getPlayer().hasDiscoveredRecipe(recipe)) return;

        e.getPlayer().discoverRecipe(recipe);
    }

}