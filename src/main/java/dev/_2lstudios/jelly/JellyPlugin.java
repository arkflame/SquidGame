package dev._2lstudios.jelly;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.jelly.commands.CommandHandler;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.jelly.config.ConfigManager;
import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.jelly.listeners.InventoryClickListener;
import dev._2lstudios.jelly.listeners.InventoryCloseListener;
import dev._2lstudios.jelly.listeners.PlayerJoinListener;
import dev._2lstudios.jelly.listeners.PlayerQuitListener;
import dev._2lstudios.jelly.player.IPluginPlayerManager;

public class JellyPlugin extends JavaPlugin {

    private CommandHandler commandHandler = new CommandHandler(this);
    private ConfigManager configManager = new ConfigManager(this);
    private IPluginPlayerManager pluginPlayerManager;

    public void useInventoryAPI() {
        this.addEventListener(new InventoryClickListener());
        this.addEventListener(new InventoryCloseListener());
    }

    public void addCommand(CommandListener cmd) {
        this.commandHandler.addCommand(cmd);
    }

    public void addEventListener(final Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    public Configuration getConfig(final String name) {
        return this.configManager.getConfig(name);
    }

    public IPluginPlayerManager getPluginPlayerManager() {
        return this.pluginPlayerManager;
    }

    public void setPluginPlayerManager(final IPluginPlayerManager manager) {
        if (this.pluginPlayerManager != null) {
            this.pluginPlayerManager.clear();
        }

        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        this.pluginPlayerManager = manager;

        for (final Player player : this.getServer().getOnlinePlayers()) {
            this.pluginPlayerManager.addPlayer(player);
        }
    }
}
