package com.kixmc.backpacks.commands;

import com.kixmc.backpacks.core.SimpleBackpacks;
import com.kixmc.backpacks.utils.BackpackItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackpackCommand implements CommandExecutor {

    public final String noPermission = ChatColor.RED + "You don't have permission to do that.";
    public final String usage = ChatColor.RED + "/backpacks <get|reload>";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || args.length > 1) {

            sender.sendMessage(usage);

            return true;
        }

        if (args.length == 1) {

            switch (args[0].toLowerCase()) {

                case "get":
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(ChatColor.RED + "You have to be a player to obtain a backpack!");
                        return true;
                    }

                    Player p = (Player) sender;

                    if (!p.hasPermission("backpacks.getcommand")) {
                        p.sendMessage(noPermission);
                        return true;
                    }

                    p.getInventory().addItem(BackpackItem.makeUnopened());

                    break;

                case "reload":
                    if (!sender.hasPermission("backpacks.reload")) {
                        sender.sendMessage(noPermission);
                        return true;
                    }

                    SimpleBackpacks.get().reloadConfig();

                    sender.sendMessage(ChatColor.GREEN + "KixsSimpleBackpacks has been reloaded!");

                    break;

                default:
                    sender.sendMessage(usage);
                    break;

            }

        }

        return false;
    }

}