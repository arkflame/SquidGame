package dev._2lstudios.squidgame.gui;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.gui.InventoryGUI;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.player.PlayerWand;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class EditArenaGame6GUI extends InventoryGUI {

    private final Arena arena;

    public EditArenaGame6GUI(final Arena arena, final InventoryGUI prevGui) {
        super("§d§lEdit Arena Sixth game §f" + arena.getName(), 45, prevGui);
        this.arena = arena;
    }

    @Override
    public void init() {
        this.addItem(0, this.createItem("§eSpawn point", Material.COMPASS, "§r\n§7Set at your current location\n§r"), 3,
                2);
        this.addItem(1, this.createItem("§eGlass Zone", Material.GLASS, "§r\n§7Set with your location wand\n§r"), 5, 2);
        this.addItem(2, this.createItem("§eGoal Zone", Material.ARMOR_STAND, "§r\n§7Set with your location wand\n§r"),
                7, 2);

        this.addItem(99, this.createItem("§cBack", Material.BARRIER), 5, 4);
    }

    @Override
    public void handle(int id, Player player) {
        if (id == 99) {
            this.back(player);
            return;
        } else if (id == 0) {
            this.arena.getConfig().setLocation("games.sixth.spawn", player.getLocation(), false);
            player.sendMessage("§eSixth game spawn §aset in your current location.");
        }

        else if (id == 1 || id == 2) {
            final SquidPlayer squidPlayer = (SquidPlayer) SquidGame.getInstance().getPlayerManager().getPlayer(player);
            final PlayerWand wand = squidPlayer.getWand();

            if (wand == null) {
                player.sendMessage("§cYou don't have an region wand, use /squid wand to get it.");
            } else if (!wand.isComplete()) {
                player.sendMessage("§cYou need to set area with your region wand first.");
            } else {

                if (id == 1) {
                    this.arena.getConfig().setCuboid("games.sixth.glass", wand.getCuboid());
                    player.sendMessage("§eSixth game glass zone§a set with your location wand §7("
                            + wand.getFirstPoint().toString() + ") (" + wand.getSecondPoint().toString() + ")");
                }

                else if (id == 2) {
                    this.arena.getConfig().setCuboid("games.sixth.goal", wand.getCuboid());
                    player.sendMessage("§eSixth game goal zone§a set with your location wand §7("
                            + wand.getFirstPoint().toString() + ") (" + wand.getSecondPoint().toString() + ")");
                }
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
