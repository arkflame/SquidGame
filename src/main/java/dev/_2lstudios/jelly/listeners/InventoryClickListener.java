package dev._2lstudios.jelly.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;

import dev._2lstudios.jelly.gui.InventoryGUI;
import dev._2lstudios.jelly.gui.InventoryManager;
import dev._2lstudios.jelly.utils.ServerUtils;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player && e.getSlotType().equals(SlotType.CONTAINER)) {
            final Player player = (Player) e.getWhoClicked();
            final InventoryGUI gui = InventoryManager.getOpenInventory(player);

            Inventory clickedInventory;
            if (ServerUtils.isLegacy()) {
                clickedInventory = e.getInventory();
            } else {
                clickedInventory = e.getClickedInventory();
            }

            final int slot = e.getSlot();
            if (gui != null && clickedInventory.equals(gui.getInventory()) && e.getInventory().getItem(slot) != null) {
                e.setCancelled(true);
                gui.handle(gui.getItemID(slot), player);
            }
        }
    }
}
