package dev._2lstudios.squidgame.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class Arena {

    private final List<SquidPlayer> players;
    private final List<SquidPlayer> spectators;

    private final World world;
    private final String name;
    private final Configuration config;

    private ArenaState state = ArenaState.WAITING;
    private ArenaGame game = ArenaGame.WAITING_ROOM;

    public Arena(final World world, final String name, final Configuration config) {
        this.players = new ArrayList<>();
        this.spectators = new ArrayList<>();

        this.world = world;
        this.name = name;
        this.config = config;
    }

    public Configuration getConfig() {
        return this.config;
    }

    public Location getSpawnPosition() {
        String configSection = "games." + this.game.toString().toLowerCase() + ".spawn";

        final double x = this.config.getDouble(configSection + ".x");
        final double y = this.config.getDouble(configSection + ".y");
        final double z = this.config.getDouble(configSection + ".z");
        final float yaw = (float) this.config.getDouble(configSection + ".yaw");
        final float pitch = (float) this.config.getDouble(configSection + ".pitch");

        final Location loc = new Location(this.world, x, y, z, yaw, pitch);
        return loc;
    }

    public Arena addPlayer(final SquidPlayer player) {
        if (!this.players.contains(player) && !this.spectators.contains(player)) {
            this.players.add(player);
            player.getBukkitPlayer().teleport(this.getSpawnPosition());
            player.setArena(this);
        }

        return this;
    }

    public Arena addSpectator(final SquidPlayer player) {
        if (!this.spectators.contains(player)) {
            if (this.players.contains(player)) {
                this.players.remove(player);
                this.spectators.add(player);
                player.setSpectator(true);
                player.setArena(this);
            }
        }

        return this;
    }

    public void removePlayer(final SquidPlayer player) {
        if (this.players.contains(player)) {
            this.players.remove(player);
        } else if (this.spectators.contains(player)) {
            this.spectators.remove(player);
            player.setSpectator(false);
        } else {
            return;
        }

        player.getBukkitPlayer().teleport(SquidGame.getInstance().getMainConfig().getLocation("lobby"));
        player.setArena(null);
    }

    public ArenaGame getGame() {
        return this.game;
    }

    public List<SquidPlayer> getPlayers() {
        return this.players;
    }

    public ArenaState getState() {
        return this.state;
    }

    public World getWorld() {
        return this.world;
    }

    public String getName() {
        return this.name;
    }
}
