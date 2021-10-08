package dev._2lstudios.squidgame.commands;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;

@Command(name = "pepe", target = CommandExecutionTarget.ONLY_PLAYER, permission = "squidgame.command.test", usage = "usage: /sg test pepe [string]", arguments = {
        String.class })
public class SquidGameSubSubCommand extends CommandListener {

    @Override
    public void handle(CommandContext context) {
        final String targetStr = context.getArguments().getString(0);

        context.getPlayer().sendMessage("Test -> " + targetStr);
    }

}
