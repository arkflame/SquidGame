package dev._2lstudios.squidgame.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlaceholderAPIHook extends PlaceholderExpansion {
    private final Plugin plugin;

    public PlaceholderAPIHook(final Plugin plugin) {
        this.plugin = plugin;
    }

    public String getPlugin() {
        return plugin.getDescription().getName();
    }

    public String getIdentifier() {
        return this.getPlugin().toLowerCase();
    }

    public String getAuthor() {
        return plugin.getDescription().getAuthors().get(0);
    }

    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("onlines")) {
            return String.valueOf(Bukkit.getOnlinePlayers().size());
        }

        if (player == null) {
            return "";
        }

        return "uwu";
    }
}