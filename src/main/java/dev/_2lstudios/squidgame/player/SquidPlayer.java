package dev._2lstudios.squidgame.player;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.player.PluginPlayer;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;

public class SquidPlayer extends PluginPlayer {

    private Arena arena = null;
    private PlayerWand wand = null;

    private boolean spectator = false;

    private final SquidGame plugin;

    public SquidPlayer(final SquidGame plugin, final Player player) {
        super(player);
        this.plugin = plugin;
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

    public void teleport(final Location loc) {
        this.getBukkitPlayer().teleport(loc);
    }

    public void teleportToLobby() {
        this.teleport(this.plugin.getMainConfig().getLocation("lobby"));
    }

    public void sendMessage(final String message) {
        this.getBukkitPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void sendTitle(final String title, final String subtitle, final int duration) {
        this.getBukkitPlayer().sendTitle(title, subtitle, 2, duration * 20, 2);
    }

    public void sendScoreboard(final String scoreboardKey) {
        this.plugin.getScoreboardHook().request(this.getBukkitPlayer(),
                this.plugin.getScoreboardConfig().getStringList(scoreboardKey));
    }

    public void playSound(final Sound sound) {
        this.getBukkitPlayer().playSound(this.getBukkitPlayer().getLocation(), sound, 1, 1);
    }
}
