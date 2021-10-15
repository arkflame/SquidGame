package dev._2lstudios.squidgame;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import dev._2lstudios.jelly.JellyPlugin;
import dev._2lstudios.jelly.config.Configuration;

import dev._2lstudios.squidgame.arena.ArenaManager;
import dev._2lstudios.squidgame.commands.SquidGameCommand;
import dev._2lstudios.squidgame.hooks.PlaceholderAPIHook;
import dev._2lstudios.squidgame.hooks.ScoreboardHook;
import dev._2lstudios.squidgame.listeners.EntityDamageListener;
import dev._2lstudios.squidgame.listeners.PlayerInteractListener;
import dev._2lstudios.squidgame.listeners.PlayerJoinListener;
import dev._2lstudios.squidgame.listeners.PlayerMoveListener;
import dev._2lstudios.squidgame.listeners.PlayerQuitListener;
import dev._2lstudios.squidgame.player.PlayerManager;
import dev._2lstudios.squidgame.tasks.ArenaTickTask;

public class SquidGame extends JellyPlugin {

    private ScoreboardHook scoreboardHook;
    private ArenaManager arenaManger;
    private PlayerManager playerManager;

    private boolean usePAPI;

    @Override
    public void onEnable() {
        final PluginManager pluginManager = getServer().getPluginManager();

        // Save current plugin instance as static instance
        SquidGame.instance = this;

        // Instantiate hooks
        this.scoreboardHook = new ScoreboardHook(pluginManager);

        if (pluginManager.isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderAPIHook(this).register();
            this.usePAPI = true;
        }

        // Instantiate managers
        this.arenaManger = new ArenaManager(this);
        this.playerManager = new PlayerManager(this);

        final ScoreboardHook scoreboardHook = new ScoreboardHook(pluginManager);

        // Register commands
        this.addCommand(new SquidGameCommand());

        // Register listeners
        this.addEventListener(new EntityDamageListener(this));
        this.addEventListener(new PlayerInteractListener(this));
        this.addEventListener(new PlayerJoinListener(this, scoreboardHook));
        this.addEventListener(new PlayerMoveListener(this));
        this.addEventListener(new PlayerQuitListener(this));

        // Register player manager
        this.setPluginPlayerManager(this.playerManager);

        // Register tasks
        Bukkit.getScheduler().runTaskTimer(this, new ArenaTickTask(this), 20L, 20L);

        // Enable inventory API
        this.useInventoryAPI();

        // Generate config files
        this.getMainConfig();
        this.getMessagesConfig();
        this.getScoreboardConfig();

        // Banner
        this.getLogger().log(Level.INFO, "§7§m==========================================================");
        this.getLogger().log(Level.INFO,
                "                §d§lSquid§f§lGame§r §a(v" + this.getDescription().getVersion() + ")");
        this.getLogger().log(Level.INFO, "§r");
        this.getLogger().log(Level.INFO, "§7- §dArena loaded: §7" + this.arenaManger.getArenas().size());
        this.getLogger().log(Level.INFO, "§7- §dPlaceholderAPI Hook: "
                + (this.usePAPI ? "§aYes" : "§cNo §7(Placeholders option will be disabled)"));
        this.getLogger().log(Level.INFO, "§7- §dScoreboard Hook: "
                + (this.scoreboardHook.canHook() ? "§aYes" : "§cNo §7(The scoreboards option will be disabled)"));
        this.getLogger().log(Level.INFO, "§r");
        this.getLogger().log(Level.INFO, "§7§m==========================================================");

    }

    /* Configuration */
    public Configuration getMainConfig() {
        return this.getConfig("config.yml");
    }

    public Configuration getMessagesConfig() {
        return this.getConfig("messages.yml");
    }

    public Configuration getScoreboardConfig() {
        return this.getConfig("scoreboard.yml");
    }

    /* Managers */
    public ArenaManager getArenaManager() {
        return this.arenaManger;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public ScoreboardHook getScoreboardHook() {
        return scoreboardHook;
    }

    /* Static instance */
    private static SquidGame instance;

    public static SquidGame getInstance() {
        return instance;
    }
}