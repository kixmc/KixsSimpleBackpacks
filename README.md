# Kix's Simple Backpacks
Simple & customizable survival-friendly item backpack plugin - a happy medium between bundles & shulker boxes

## Compatibility
* Spigot, Paper, & CraftBukkit on versions **1.13.x - 1.16.x** (other server types may work but are not directly supported)
* Java 8 or newer

![preview images](https://i.imgur.com/vVl5ojB.png)

## Features
* **Supports any items**; all item attributes including nbt data is processed
* **No dupes** unless the item is copied in creative mode; unique identifier assigned per backpack
* **Very customizable**; change backpack size, lore, display name, etc. (see [config.yml](src/config.yml))
* **No messy backend storage**; backpack contents stored in the item itself (PersistentDataContainer)
* **Anvil support** for renaming backpacks with respect to a configurable format
* **Rich meta**; backpack items show a summary of its contents along with slot usage and capacity in item lore
* **Dynamic config updates**; refreshes existing items in accordance to config changes:
  * If backpack size is made smaller, when players open their backpacks any items that previously fit in the older, bigger size will be dropped at their feet
  * Backpack item material, any text/lores from the config, and other options are refreshed and applied to backpacks when opened

## Commands & Permissions
- **/backpacks get**: get a backpack without having to craft it [permission: *backpacks.getcommand*]
- **/backpacks reload**: reload & update changes to the config file [permission: *backpacks.reload*]

## Download
https://www.spigotmc.org/resources/91098/

## Compile
Requires Java 8 & Maven
```
1. git clone https://github.com/kixmc/KixsSimpleBackpacks
2. mvn package
```
Compiled jar will be in the /target directory

## Support
If you have any questions, suggestions, or just want to say hi you can join my Discord server here: https://discord.com/invite/HKnDTRj
