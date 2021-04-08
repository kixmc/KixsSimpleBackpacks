# KixsSimpleBackpacks
Barebones item backpack plugin

## Features
- No dupes (unless item is copied in creative mode)
- Very customizable; change backpack size, lore, display name, etc.
- No messy backend storage; backpack contents stored in the item itself (PersistentDataContainer)
- Anvil support for renaming backpacks
- Backpack items show a summary of its contents along with slot usage and capacity in item lore
- Dynamically updates existing items in accordance to config changes:
  - If backpack size is made smaller, when players open their backpacks any items that previously fit in the older, bigger size will be dropped at their feet
  - Backpack item material, any text/lores from the config, and other options are refreshed and applied to backpacks on backpack open

## Compile
Requires Java 8 & Maven
