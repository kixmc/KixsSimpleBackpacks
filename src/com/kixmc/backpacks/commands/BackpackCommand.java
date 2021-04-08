package com.kixmc.backpacks.commands;

import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.contents.ItemHandler;
import com.kixmc.backpacks.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("get")) {

                    ItemStack is = new ItemStack(Material.valueOf(SimpleBackpacks.get().getConfig().getString("backpack.material")));
                    ItemHandler.store(is, new ArrayList<>());

                    NamespacedKey key = new NamespacedKey(SimpleBackpacks.get(), "kixs-backpacks");
                    ItemMeta itemMeta = is.getItemMeta();
                    itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "");

                    itemMeta.setDisplayName(ChatUtil.colorize(SimpleBackpacks.get().getConfig().getString("backpack.name.regular")));

                    ArrayList<String> lore = new ArrayList<>();

                    for(String loreLine : SimpleBackpacks.get().getConfig().getStringList("backpack.lore.new")) {
                        lore.add(ChatUtil.colorize(loreLine));
                    }

                    itemMeta.setLore(lore);

                    is.setItemMeta(itemMeta);

                    p.getInventory().addItem(is);
                    p.updateInventory();

                }
            }

        }
        return false;
    }

}
