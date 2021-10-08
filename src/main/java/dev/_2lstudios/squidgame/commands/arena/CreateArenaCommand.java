package dev._2lstudios.squidgame.commands.arena;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;

@Command(name = "create", usage = "/squid arena create [name] [minPlayers]", description = "Create an arena", permission = "squidgame.admin.arena", arguments = {
        String.class, Integer.class }, target = CommandExecutionTarget.ONLY_PLAYER)
public class CreateArenaCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) {
        context.getPlayer().sendMessage("Creating arena " + context.getArguments().getString(0) + " with min players "
                + context.getArguments().getInt(1));
    }
}
