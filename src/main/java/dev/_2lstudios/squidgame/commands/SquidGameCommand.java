package dev._2lstudios.squidgame.commands;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.squidgame.commands.game.SquidJoinCommand;

@Command(name = "squidgame")
public class SquidGameCommand extends CommandListener {
    public SquidGameCommand() {
        this.addSubcommand(new SquidJoinCommand());
    }

    @Override
    public void handle(CommandContext context) {
        context.getSender().sendMessage("\n§d§lSquid§f§lGame§r");
        context.getSender().sendMessage(this.mapCommandListToString("§d/squid {name} §8- §f{description}", "\n"));
    }
}
