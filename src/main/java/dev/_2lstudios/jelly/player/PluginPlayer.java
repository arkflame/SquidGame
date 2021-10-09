package dev._2lstudios.jelly.player;

import org.bukkit.entity.Player;

public class PluginPlayer {
    private final Player player;

    public PluginPlayer(final Player player) {
        this.player = player;
    }

    public Player getBukkitPlayer() {
        return this.player;
    }
}
