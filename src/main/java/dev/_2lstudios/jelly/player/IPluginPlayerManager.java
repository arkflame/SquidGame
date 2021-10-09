package dev._2lstudios.jelly.player;

import org.bukkit.entity.Player;

public interface IPluginPlayerManager {
    PluginPlayer addPlayer(final Player player);

    PluginPlayer removePlayer(final Player player);

    PluginPlayer getPlayer(final Player player);

    void clear();
}
