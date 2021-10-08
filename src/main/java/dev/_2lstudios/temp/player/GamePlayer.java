package dev._2lstudios.temp.player;

import org.bukkit.entity.Player;

import dev._2lstudios.temp.arena.Arena;

public class GamePlayer {

    private final Player player;

    private Arena arena = null;
    private PlayerWand wand = null;

    public GamePlayer(final Player player) {
        this.player = player;
    }

    public Arena getArena() {
        return this.arena;
    }

    public Player getBukkitPlayer() {
        return this.player;
    }

    public PlayerWand getWand() {
        if (this.wand == null) {
            this.wand = new PlayerWand();
        }

        return this.wand;
    }
}
