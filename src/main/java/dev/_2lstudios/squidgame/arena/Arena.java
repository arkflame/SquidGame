package dev._2lstudios.squidgame.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class Arena {

    private final List<SquidPlayer> players;
    private final List<SquidPlayer> spectators;

    private final World world;
    private final String name;

    private final Configuration arenaConfig;

    private ArenaState state = ArenaState.WAITING;
    private ArenaGame game = ArenaGame.WAITING_ROOM;

    private int internalTime;

    public Arena(final World world, final String name, final Configuration arenaConfig) {
        this.players = new ArrayList<>();
        this.spectators = new ArrayList<>();

        this.world = world;
        this.name = name;
        this.arenaConfig = arenaConfig;
    }

    /* Arena Handling */
    private void handlePlayerJoin(final Player bukkitPlayer) {
        this.broadcastMessage("§a" + bukkitPlayer.getName() + " §ehas joined the game §a(" + this.players.size() + "/"
                + this.getMaxPlayers() + ")");

        if (this.players.size() >= this.getMinPlayers() && this.state == ArenaState.WAITING) {
            this.state = ArenaState.STARTING;
            this.internalTime = 30;
            this.broadcastMessage("§aStarting the game in " + this.getInternalTime() + " seconds.");
        }
    }

    private void handlePlayerLeave(final Player bukkitPlayer) {
        this.broadcastMessage("§c" + bukkitPlayer.getName() + " §ehas leave the game §c(" + this.players.size() + "/"
                + this.getMaxPlayers() + ")");

        if (this.players.size() < this.getMinPlayers() && this.state == ArenaState.STARTING) {
            this.state = ArenaState.WAITING;
            this.broadcastMessage("§cNo enough players to start the game, required: " + this.getMinPlayers());
        }
    }

    private void handleArenaTick() {
        if (this.state == ArenaState.STARTING) {
            if (this.internalTime == 10) {
                this.broadcastMessage("§aStarting the game in §e" + this.getInternalTime() + " §aseconds.");
            }

            else if (this.internalTime <= 5 && this.internalTime > 0) {
                this.broadcastMessage("§aStarting the game in §6" + this.getInternalTime() + " §aseconds.");
            }

            else if (this.internalTime == 0) {
                this.state = ArenaState.IN_GAME;
                this.broadcastMessage("§aGame started, good luck!");
            }
        }
    }

    /* Arena utils */
    public void broadcastMessage(final String message) {
        for (final Player player : this.world.getPlayers()) {
            player.sendMessage(message);
        }
    }

    public int getMinPlayers() {
        return this.arenaConfig.getInt("arena.min-players");
    }

    public int getMaxPlayers() {
        return this.arenaConfig.getInt("arena.max-players");
    }

    public Configuration getConfig() {
        return this.arenaConfig;
    }

    public Location getSpawnPosition() {
        String configSection = "games." + this.game.toString().toLowerCase() + ".spawn";

        final double x = this.arenaConfig.getDouble(configSection + ".x");
        final double y = this.arenaConfig.getDouble(configSection + ".y");
        final double z = this.arenaConfig.getDouble(configSection + ".z");
        final float yaw = (float) this.arenaConfig.getDouble(configSection + ".yaw");
        final float pitch = (float) this.arenaConfig.getDouble(configSection + ".pitch");

        final Location loc = new Location(this.world, x, y, z, yaw, pitch);
        return loc;
    }

    public Arena addPlayer(final SquidPlayer player) {
        if (!this.players.contains(player) && !this.spectators.contains(player)) {
            this.players.add(player);
            player.getBukkitPlayer().teleport(this.getSpawnPosition());
            player.setArena(this);
            this.handlePlayerJoin(player.getBukkitPlayer());
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
            this.handlePlayerLeave(player.getBukkitPlayer());
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

    public int getInternalTime() {
        return this.internalTime;
    }

    public void setInternalTime(final int time) {
        if (time >= 0) {
            this.internalTime = time;
            this.handleArenaTick();
        }
    }
}
