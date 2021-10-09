package dev._2lstudios.squidgame.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import dev._2lstudios.jelly.player.IPluginPlayerManager;
import dev._2lstudios.jelly.player.PluginPlayer;

public class PlayerManager implements IPluginPlayerManager {

    private final Map<Player, SquidPlayer> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

    @Override
    public PluginPlayer addPlayer(Player player) {
        final SquidPlayer pluginPlayer = new SquidPlayer(player);
        this.players.put(player, pluginPlayer);
        return pluginPlayer;
    }

    @Override
    public PluginPlayer removePlayer(Player player) {
        return this.players.remove(player);
    }

    @Override
    public PluginPlayer getPlayer(Player player) {
        return this.players.get(player);
    }

    @Override
    public void clear() {
        this.players.clear();
    }

}
