package dev._2lstudios.jelly.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.JellyPlugin;

public class CommandContext {

    private final CommandArguments arguments;
    private final JellyPlugin plugin;
    private final CommandSender sender;

    private final boolean isPlayer;

    public CommandContext(final JellyPlugin plugin, final CommandSender sender, final CommandArguments arguments) {
        this.arguments = arguments;
        this.plugin = plugin;
        this.sender = sender;

        this.isPlayer = sender instanceof Player;
    }

    public CommandArguments getArguments() {
        return this.arguments;
    }

    public boolean isExecutedByPlayer() {
        return this.isPlayer;
    }

    public Player getPlayer() {
        if (this.isExecutedByPlayer()) {
            return (Player) this.sender;
        } else {
            return null;
        }
    }

    public JellyPlugin getPlugin() {
        return this.plugin;
    }

    public CommandSender getSender() {
        return this.sender;
    }
}
