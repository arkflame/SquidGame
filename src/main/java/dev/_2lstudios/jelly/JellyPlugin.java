package dev._2lstudios.jelly;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.jelly.commands.CommandHandler;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.jelly.config.ConfigManager;
import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.jelly.listeners.PlayerJoinListener;
import dev._2lstudios.jelly.listeners.PlayerQuitListener;
import dev._2lstudios.jelly.player.IPluginPlayerManager;
import dev._2lstudios.jelly.utils.ReflectionUtils;

public class JellyPlugin extends JavaPlugin {

    private CommandHandler commandHandler = new CommandHandler(this);
    private ConfigManager configManager = new ConfigManager(this);
    private ReflectionUtils reflection = new ReflectionUtils();

    private IPluginPlayerManager pluginPlayerManager;

    public void addCommand(CommandListener cmd) {
        this.commandHandler.addCommand(cmd);
    }

    public Configuration getConfig(final String name) {
        return this.configManager.getConfig(name);
    }

    public IPluginPlayerManager getPluginPlayerManager() {
        return this.pluginPlayerManager;
    }

    public ReflectionUtils getReflection() {
        return this.reflection;
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
