package dev._2lstudios.squidgame.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.games.ArenaGameBase;
import dev._2lstudios.squidgame.arena.games.G1RedGreenLightGame;
import dev._2lstudios.squidgame.arena.games.G7SquidGame;
import dev._2lstudios.squidgame.hooks.ScoreboardHook;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class Arena {
    private final List<SquidPlayer> players;
    private final List<SquidPlayer> spectators;
    private final List<ArenaGameBase> games;

    private final ScoreboardHook scoreboardHook;
    private final Configuration arenaConfig;
    private final ArenaHandler handler;
    private final World world;
    private final String name;

    private ArenaState state = ArenaState.WAITING;
    private ArenaGameBase currentGame;
    private int internalTime;

    public Arena(final World world, final String name, final ScoreboardHook scoreboardHook,
            final Configuration arenaConfig) {
        this.players = new ArrayList<>();
        this.spectators = new ArrayList<>();
        this.games = new ArrayList<>();

        this.scoreboardHook = scoreboardHook;
        this.arenaConfig = arenaConfig;
        this.handler = new ArenaHandler(this);
        this.world = world;
        this.name = name;

        this.resetArena();
    }

    public void resetArena() {
        for (final SquidPlayer player : this.getAllPlayers()) {
            this.removePlayer(player);
        }

        this.state = ArenaState.WAITING;
        this.currentGame = null;
        this.internalTime = -1;

        this.players.clear();
        this.spectators.clear();
        this.games.clear();

        this.games.add(new G7SquidGame(this));
        this.games.add(new G1RedGreenLightGame(this));
    }

    public void broadcastMessage(final String message) {
        for (final Player player : this.world.getPlayers()) {
            player.sendMessage(message);
        }
    }

    public void broadcastSound(final Sound sound) {
        for (final Player player : this.world.getPlayers()) {
            player.playSound(player.getLocation(), sound, 1, 1);
        }
    }

    public void broadcastTitle(final String title, final String subtitle) {
        for (final Player player : this.world.getPlayers()) {
            player.sendTitle(title, subtitle, 2, 40, 2);
        }
    }

    public void broadcastScoreboard(final List<String> lines) {
        for (final SquidPlayer player : this.players) {
            scoreboardHook.request(player, lines);
        }

        for (final SquidPlayer player : this.spectators) {
            scoreboardHook.request(player, lines);
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
        if (this.getState() == ArenaState.INTERMISSION || this.getState() == ArenaState.FINISHING_ARENA) {
            final Location loc = this.arenaConfig.getLocation("arena.waiting_room");
            loc.setWorld(this.world);
            return loc;
        } else if (this.getCurrentGame() != null) {
            return this.getCurrentGame().getSpawnPosition();
        } else {
            final Location loc = this.arenaConfig.getLocation("arena.prelobby");
            loc.setWorld(this.world);
            return loc;
        }
    }

    public void teleportAllPlayers(final Location location) {
        for (final SquidPlayer player : this.players) {
            player.getBukkitPlayer().teleport(location);
        }

        for (final SquidPlayer player : this.spectators) {
            player.getBukkitPlayer().teleport(location);
        }
    }

    public void finishArena(final ArenaFinishReason reason) {
        this.handler.handleArenaFinish(reason);
        this.setState(ArenaState.FINISHING_ARENA);
        this.teleportAllPlayers(this.getSpawnPosition());
    }

    public Arena addPlayer(final SquidPlayer player) {
        if (!this.players.contains(player) && !this.spectators.contains(player)) {
            this.players.add(player);
            player.getBukkitPlayer().teleport(this.getSpawnPosition());
            player.setArena(this);
            this.handler.handlePlayerJoin(player.getBukkitPlayer());
        }

        return this;
    }

    public void killPlayer(final SquidPlayer player) {
        this.addSpectator(player);
        this.broadcastSound(Sound.ENTITY_GENERIC_EXPLODE);
        this.broadcastMessage("§c" + player.getBukkitPlayer().getName() + " §eha sido eliminado.");

        if (this.isAllPlayersDeath()) {
            this.finishArena(ArenaFinishReason.ALL_PLAYERS_DEATH);
        }

        else if (this.calculateWinner() != null) {
            this.finishArena(ArenaFinishReason.ONE_PLAYER_IN_ARENA);
        }
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
            this.handler.handlePlayerLeave(player);
        } else if (this.spectators.contains(player)) {
            this.spectators.remove(player);
            player.setSpectator(false);
        } else {
            return;
        }

        player.getBukkitPlayer().teleport(SquidGame.getInstance().getMainConfig().getLocation("lobby"));
        scoreboardHook.request(player, SquidGame.getInstance().getScoreboardConfig().getStringList("lobby"));
        player.setArena(null);
    }

    public List<SquidPlayer> getAllPlayers() {
        final List<SquidPlayer> result = new ArrayList<>(this.getPlayers());
        result.addAll(this.getSpectators());
        return result;
    }

    public List<SquidPlayer> getPlayers() {
        return this.players;
    }

    public List<SquidPlayer> getSpectators() {
        return this.spectators;
    }

    public ArenaState getState() {
        return this.state;
    }

    public World getWorld() {
        return this.world;
    }

    public ArenaGameBase getCurrentGame() {
        return this.currentGame;
    }

    public String getName() {
        return this.name;
    }

    public int getInternalTime() {
        return this.internalTime;
    }

    public void setState(final ArenaState newState) {
        this.state = newState;
        this.handler.handleArenaSwitchState();
    }

    public void setInternalTime(final int time) {
        if (time >= 0) {
            this.internalTime = time;
        }
    }

    public SquidPlayer calculateWinner() {
        if (this.players.size() == 1) {
            return this.players.get(0);
        } else {
            return null;
        }
    }

    public boolean isAllPlayersDeath() {
        return this.players.isEmpty();
    }

    public void doArenaTick() {
        if (this.internalTime >= 0) {
            this.internalTime--;
            this.handler.handleArenaTick();
        }
    }

    public void nextGame() {
        if (this.calculateWinner() != null) {
            this.finishArena(ArenaFinishReason.ONE_PLAYER_IN_ARENA);
            return;
        }

        final ArenaGameBase nextGame = this.games.get(0);
        this.currentGame = nextGame;
        this.games.remove(nextGame);

        this.setState(ArenaState.INTERMISSION);
        this.teleportAllPlayers(this.getSpawnPosition());

        this.setInternalTime(5);
        this.broadcastTitle("Intermission", "Next game in 5 seconds");
    }
}
