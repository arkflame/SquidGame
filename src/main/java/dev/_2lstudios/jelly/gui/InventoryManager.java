package dev._2lstudios.jelly.gui;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class InventoryManager {
    private static Map<Player, InventoryGUI> inventories = new HashMap<>();

    public static void openInventory(final Player player, final InventoryGUI gui) {
        inventories.put(player, gui);
    }

    public static void closeInventory(final Player player) {
        inventories.remove(player);
    }

    public static InventoryGUI getOpenInventory(final Player player) {
        return inventories.get(player);
    }

}
