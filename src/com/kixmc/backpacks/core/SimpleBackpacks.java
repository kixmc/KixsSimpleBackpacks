package com.kixmc.backpacks.core;

import com.kixmc.backpacks.commands.BackpackCommand;
import com.kixmc.backpacks.listeners.BlockPlace;
import com.kixmc.backpacks.listeners.InventoryClick;
import com.kixmc.backpacks.listeners.InventoryClose;
import com.kixmc.backpacks.listeners.PlayerInteract;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SimpleBackpacks extends JavaPlugin {

    private static SimpleBackpacks main;

    public static SimpleBackpacks get() {
        return main;
    }

    public void onEnable() {

        main = this;

        getCommand("backpack").setExecutor(new BackpackCommand());

        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        loadConfig();

    }

    public void onDisable() {}

    public void loadConfig() {

        try {

            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            File config = new File(getDataFolder(), "config.yml");

            if (!config.exists()) {
                saveDefaultConfig();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
