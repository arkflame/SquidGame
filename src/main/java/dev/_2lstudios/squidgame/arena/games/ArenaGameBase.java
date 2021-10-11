package dev._2lstudios.squidgame.arena.games;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;

public abstract class ArenaGameBase {

    private final String name;
    private final String configKey;

    private final Arena arena;

    private final int explainTime, gameTime, finishTime;

    public ArenaGameBase(final String name, final String configKey, final int explainTime, final int gameTime,
            final int finishTime, final Arena arena) {
        this.name = name;
        this.configKey = configKey;

        this.arena = arena;

        this.explainTime = explainTime;
        this.gameTime = gameTime;
        this.finishTime = finishTime;
    }

    public abstract void onExplainStart();

    public abstract void onStart();

    public abstract void onTimeUp();

    public Location getSpawnPosition() {
        final Configuration config = this.arena.getConfig();
        final Location location = config.getLocation("games." + this.configKey + ".spawn");
        location.setWorld(this.arena.getWorld());
        return location;
    }

    public void broadcastTitleAfterSeconds(int seconds, final String title, final String subtitle) {
        Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
            this.arena.broadcastTitle(title, subtitle);
        }, seconds * 20L);
    }

    public void broadcastMessageAfterSeconds(int seconds, final String message) {
        Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
            this.arena.broadcastMessage(message);
        }, seconds * 20L);
    }

    public int getExplainTime() {
        return this.explainTime;
    }

    public int getGameTime() {
        return this.gameTime;
    }

    public int getFinishTime() {
        return this.finishTime;
    }

    public Arena getArena() {
        return this.arena;
    }

    public String getName() {
        return this.name;
    }
}
