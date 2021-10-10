package dev._2lstudios.jelly.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev._2lstudios.jelly.gui.InventoryManager;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
        InventoryManager.closeInventory((Player) e.getPlayer());
    }
}