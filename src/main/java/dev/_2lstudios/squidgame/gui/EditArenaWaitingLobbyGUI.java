package dev._2lstudios.squidgame.gui;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.gui.InventoryGUI;
import dev._2lstudios.squidgame.arena.Arena;

public class EditArenaWaitingLobbyGUI extends InventoryGUI {

    private final Arena arena;

    public EditArenaWaitingLobbyGUI(final Arena arena, final InventoryGUI prevGui) {
        super("§d§lEdit Arena Waiting Lobby §f" + arena.getName(), 36, prevGui);
        this.arena = arena;
    }

    @Override
    public void init() {
        this.addItem(0, this.createItem("§eWaiting Lobby Spawn", Material.COMPASS), 4, 2);
        this.addItem(1, this.createItem("§eSet arena World", Material.GREEN_DYE), 6, 2);
        this.addItem(99, this.createItem("§cBack", Material.BARRIER), 5, 4);
    }

    @Override
    public void handle(int id, Player player) {
        switch (id) {
            case 0:
                this.arena.getConfig().setLocation("arena.waiting_room.spawn", player.getLocation(), false);
                player.sendMessage("§eWaiting room spawn §ahas set to your current position.");
                break;
            case 1:
                this.arena.getConfig().set("arena.world", player.getWorld().getName());
                player.sendMessage("§eArena map §aset in your current world.");
                break;
            case 99:
                this.back(player);
                return;
        }

        try {
            this.arena.getConfig().save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.close(player);
    }
}
