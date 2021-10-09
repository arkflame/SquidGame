package dev._2lstudios.jelly.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.jelly.JellyPlugin;

public class PlayerJoinListener implements Listener {
    private final JellyPlugin plugin;

    public PlayerJoinListener(final JellyPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        if (this.plugin.getPluginPlayerManager() != null) {
            this.plugin.getPluginPlayerManager().addPlayer(e.getPlayer());
        }
    }
}
