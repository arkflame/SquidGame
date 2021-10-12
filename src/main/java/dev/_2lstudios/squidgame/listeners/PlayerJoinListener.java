package dev._2lstudios.squidgame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.hooks.ScoreboardHook;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class PlayerJoinListener implements Listener {
    private final SquidGame plugin;
    private final ScoreboardHook scoreboardHook;

    public PlayerJoinListener(final SquidGame plugin, final ScoreboardHook scoreboardHook) {
        this.plugin = plugin;
        this.scoreboardHook = scoreboardHook;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final SquidPlayer squidPlayer = (SquidPlayer) this.plugin.getPlayerManager().getPlayer(e.getPlayer());
        final Configuration config = this.plugin.getMainConfig();

        scoreboardHook.request(squidPlayer, config.getStringList("scoreboard.lobby"));
    }
}