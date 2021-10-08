package dev._2lstudios.jelly;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.jelly.commands.CommandHandler;
import dev._2lstudios.jelly.commands.CommandListener;

public class JellyPlugin extends JavaPlugin {

    private CommandHandler commandHandler = new CommandHandler(this);

    public void addCommand(CommandListener cmd) {
        this.commandHandler.addCommand(cmd);
    }
}
