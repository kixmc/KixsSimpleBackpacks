package com.kixmc.backpacks.core;

import com.kixmc.backpacks.commands.BackpackCommand;
import com.kixmc.backpacks.listeners.BlockPlace;
import com.kixmc.backpacks.listeners.CloseBackpack;
import com.kixmc.backpacks.listeners.OpenBackpack;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleBackpacks extends JavaPlugin {

    private static SimpleBackpacks main;

    public static SimpleBackpacks get() {
        return main;
    }

    public void onEnable() {

        main = this;

        getCommand("backpack").setExecutor(new BackpackCommand());

        getServer().getPluginManager().registerEvents(new OpenBackpack(), this);
        getServer().getPluginManager().registerEvents(new CloseBackpack(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);

    }

    public void onDisable() {}

}
