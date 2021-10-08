package dev._2lstudios.temp.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

import dev._2lstudios.temp.player.GamePlayer;

public class Arena {

    private final List<GamePlayer> players;
    private final ArenaSettings settings;
    private final World world;

    private ArenaState state = ArenaState.INVALID;
    private ArenaGame game = ArenaGame.LOBBY;

    public Arena(final String id, final ArenaSettings settings) {
        this.players = new ArrayList<>();
        this.settings = settings;
        this.world = Bukkit.getWorld("game-" + id);

        if (this.settings.isValid()) {
            this.state = ArenaState.WAITING;
        }
    }

    public ArenaGame getGame() {
        return this.game;
    }

    public List<GamePlayer> getPlayers() {
        return this.players;
    }

    public ArenaSettings getSettings() {
        return this.settings;
    }

    public ArenaState getState() {
        return this.state;
    }

    public World getWorld() {
        return this.world;
    }
}
