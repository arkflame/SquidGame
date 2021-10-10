package dev._2lstudios.squidgame.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.gui.InventoryGUI;
import dev._2lstudios.squidgame.arena.Arena;

public class EditArenaGUI extends InventoryGUI {

    private final Arena arena;

    public EditArenaGUI(final Arena arena) {
        super("§d§lEdit Arena §f" + arena.getName(), 5 * 9);
        this.arena = arena;
    }

    @Override
    public void init() {
        this.addItem(0,
                this.createItem("§bWaiting lobby", Material.COMPASS, "§r\n§7Where players spawn after each game.\n§r"),
                2, 2);
        this.addItem(1,
                this.createItem("§eFirst game", Material.ENDER_EYE, "§r\n§aGreen Light§7, §cRed Light §7game.\n§r"), 3,
                2);

        this.addItem(3, this.createItem("§cComing soon", Material.GRAY_DYE), 4, 2);
        this.addItem(4, this.createItem("§cComing soon", Material.GRAY_DYE), 5, 2);
        this.addItem(5, this.createItem("§cComing soon", Material.GRAY_DYE), 6, 2);
        this.addItem(6, this.createItem("§cComing soon", Material.GRAY_DYE), 7, 2);
        this.addItem(7, this.createItem("§cComing soon", Material.GRAY_DYE), 8, 2);

        this.addItem(99, this.createItem("§cExit", Material.BARRIER), 5, 4);
    }

    @Override
    public void handle(int id, Player player) {
        switch (id) {
            case 0:
                new EditArenaWaitingLobbyGUI(this.arena, this).open(player);
                break;
            case 1:
                new EditArenaGame1GUI(this.arena, this).open(player);
                break;
            default:
                this.close(player);
                break;
        }
    }
}
