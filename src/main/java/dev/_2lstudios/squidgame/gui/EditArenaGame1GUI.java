package dev._2lstudios.squidgame.gui;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.gui.InventoryGUI;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.player.PlayerManager;
import dev._2lstudios.squidgame.player.PlayerWand;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class EditArenaGame1GUI extends InventoryGUI {

    private final Arena arena;

    public EditArenaGame1GUI(final Arena arena, final InventoryGUI prevGui) {
        super("§d§lArena §f" + arena.getName(), 45, prevGui);
        this.arena = arena;
    }

    @Override
    public void init() {
        this.addItem(0, this.createItem("§eSpawn point", Material.COMPASS, "§r\n§7Set at your current location\n§r"), 2,
                2);
        this.addItem(1, this.createItem("§eBarrier", Material.BEDROCK, "§r\n§7Set with your location wand\n§r"), 4, 2);
        this.addItem(2, this.createItem("§eKill Zone", Material.ENDER_PEARL, "§r\n§7Set with your location wand\n§r"),
                6, 2);
        this.addItem(3, this.createItem("§eGoal", Material.ARMOR_STAND, "§r\n§7Set with your location wand\n§r"), 8, 2);

        this.addItem(99, this.createItem("§cBack", Material.BARRIER), 5, 4);
    }

    @Override
    public void handle(int id, Player player) {
        final PlayerManager pm = SquidGame.getInstance().getPlayerManager();
        final SquidPlayer squidPlayer = (SquidPlayer) pm.getPlayer(player);
        final PlayerWand wand = squidPlayer.getWand();

        if (id == 99) {
            this.back(player);
            return;
        } else if (id == 0) {
            this.arena.getConfig().setLocation("games.first.spawn", player.getLocation(), false);
            player.sendMessage("§eFirst game spawn§a set in your current location.");
        } else {
            String key = "games.first";

            switch (id) {
            case 1:
                key += ".barrier";
                break;
            case 2:
                key += ".killzone";
                break;
            case 3:
                key += ".goal";
                break;
            }

            if (wand == null) {
                player.sendMessage("§cYou don't have an region wand, use /squid wand to get it.");
            } else if (!wand.isComplete()) {
                player.sendMessage("§cYou need to set area with your region wand first.");
            } else {
                this.arena.getConfig().setCuboid(key, wand.getCuboid());
                player.sendMessage("§eFirst game " + key + "§a set with your location wand §7("
                        + wand.getFirstPoint().toString() + ") (" + wand.getSecondPoint().toString() + ")");
            }
        }

        try {
            this.arena.getConfig().save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.close(player);
    }
}
