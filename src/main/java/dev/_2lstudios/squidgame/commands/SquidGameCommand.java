package dev._2lstudios.squidgame.commands;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandListener;

import dev._2lstudios.squidgame.commands.admin.SquidCreateArenaCommand;
import dev._2lstudios.squidgame.commands.admin.SquidSetLobbyCommand;
import dev._2lstudios.squidgame.commands.admin.SquidEditArenaCommand;
import dev._2lstudios.squidgame.commands.admin.SquidWandCommand;
import dev._2lstudios.squidgame.commands.game.SquidJoinCommand;
import dev._2lstudios.squidgame.commands.game.SquidLeaveCommand;
import dev._2lstudios.squidgame.commands.game.SquidStartCommand;

@Command(name = "squidgame")
public class SquidGameCommand extends CommandListener {
    public SquidGameCommand() {
        // Admin commands
        this.addSubcommand(new SquidCreateArenaCommand());
        this.addSubcommand(new SquidSetLobbyCommand());
        this.addSubcommand(new SquidEditArenaCommand());
        this.addSubcommand(new SquidWandCommand());

        // Game commands
        this.addSubcommand(new SquidLeaveCommand());
        this.addSubcommand(new SquidJoinCommand());
        this.addSubcommand(new SquidStartCommand());
    }

    @Override
    public void handle(CommandContext context) {
        context.getSender().sendMessage("\n§d§lSquid§f§lGame§r");
        context.getSender().sendMessage(this.mapCommandListToString("§d/squid {name} §8- §f{description}", "\n"));
    }
}
