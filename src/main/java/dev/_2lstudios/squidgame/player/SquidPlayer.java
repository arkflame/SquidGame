package dev._2lstudios.squidgame.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.player.PluginPlayer;
import dev._2lstudios.squidgame.arena.Arena;

public class SquidPlayer extends PluginPlayer {
    private Arena arena = null;
    private PlayerWand wand = null;

    private boolean spectator = false;

    public SquidPlayer(Player player) {
        super(player);
    }

    public PlayerWand getWand() {
        return this.wand;
    }

    public PlayerWand createWand(final PlayerWand wand) {
        this.wand = wand;
        return this.wand;
    }

    public Arena getArena() {
        return this.arena;
    }

    public void setArena(final Arena arena) {
        if (arena == null && this.arena != null) {
            this.arena.removePlayer(this);
            this.arena = null;
        } else if (arena != null && this.arena == null) {
            this.arena = arena;
            arena.addPlayer(this);
        }
    }

    public boolean isSpectator() {
        return this.spectator;
    }

    public void setSpectator(final boolean result) {
        this.spectator = result;
        if (result) {
            this.getBukkitPlayer().setGameMode(GameMode.SPECTATOR);
        } else {
            this.getBukkitPlayer().setGameMode(GameMode.ADVENTURE);
        }
    }
}
