package dev._2lstudios.squidgame;

import dev._2lstudios.jelly.JellyPlugin;
import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.arena.ArenaManager;
import dev._2lstudios.squidgame.commands.SquidGameCommand;
import dev._2lstudios.squidgame.player.PlayerManager;

public class SquidGame extends JellyPlugin {

    private ArenaManager arenaManger;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        // Save current plugin instance as static instance
        SquidGame.instance = this;

        // Instantiate managers
        this.arenaManger = new ArenaManager(this);
        this.playerManager = new PlayerManager();

        // Register commands
        this.addCommand(new SquidGameCommand());

        // Register player manager
        this.setPluginPlayerManager(this.playerManager);

        // Generate config files
        this.getArenaConfig();
        this.getMainConfig();
    }

    /* Configuration */
    public Configuration getArenaConfig() {
        return this.getConfig("arena.yml");
    }

    public Configuration getMainConfig() {
        return this.getConfig("config.yml");
    }

    /* Managers */
    public ArenaManager getArenaManager() {
        return this.arenaManger;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    /* Static instance */
    private static SquidGame instance;

    public static SquidGame getInstance() {
        return instance;
    }
}