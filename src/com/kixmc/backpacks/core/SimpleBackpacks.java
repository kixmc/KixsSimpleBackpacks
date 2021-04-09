package com.kixmc.backpacks.core;

import com.kixmc.backpacks.commands.BackpackCommand;
import com.kixmc.backpacks.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class SimpleBackpacks extends JavaPlugin {

    private static SimpleBackpacks main;

    public static SimpleBackpacks get() {
        return main;
    }

    public void onEnable() {

        main = this;

        getCommand("backpacks").setExecutor(new BackpackCommand());

        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PrepareAnvil(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        loadConfig();
        createRecipe();

    }

    public void onDisable() {
    }

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

    public void createRecipe() {

        NamespacedKey key = new NamespacedKey(this, "kixs-backpacks");
        ShapedRecipe recipe = new ShapedRecipe(key, BackpackItem.makeUnopened());

        recipe.shape(getConfig().getString("backpack.recipe.shape.top"), getConfig().getString("backpack.recipe.shape.mid"), getConfig().getString("backpack.recipe.shape.btm"));

        for (String ingredientKey : getConfig().getConfigurationSection("backpack.recipe.key").getKeys(false)) {
            ArrayList<Material> choices = new ArrayList<>();
            for (String choice : getConfig().getStringList("backpack.recipe.key." + ingredientKey)) {
                choices.add(Material.valueOf(choice));
            }
            recipe.setIngredient(ingredientKey.charAt(0), new RecipeChoice.MaterialChoice(choices));
        }

        Bukkit.addRecipe(recipe);

    }

}
