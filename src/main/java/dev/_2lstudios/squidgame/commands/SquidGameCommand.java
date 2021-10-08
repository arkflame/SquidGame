package dev._2lstudios.squidgame.commands;

import org.bukkit.entity.Player;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;

@Command(name = "squidgame", target = CommandExecutionTarget.ONLY_PLAYER, permission = "squidgame.use", usage = "usage: /sg [player] [string] [number] [bool]", arguments = {
        Player.class, String.class, Integer.class, Boolean.class })
public class SquidGameCommand extends CommandListener {

    public SquidGameCommand() {
        this.addSubcommand(new SquidGameSubCommand());
    }

    @Override
    public void handle(CommandContext context) {
        final Player targetPlayer = context.getArguments().getPlayer(0);
        final String targetStr = context.getArguments().getString(1);
        final int targetInt = context.getArguments().getInt(2);
        final boolean targetBool = context.getArguments().getBoolean(3);

        context.getPlayer()
                .sendMessage("La uuid de " + targetPlayer.getName() + " es " + targetPlayer.getUniqueId().toString());
        context.getPlayer().sendMessage("Tu string es " + targetStr + " y en mayuscula es " + targetStr.toUpperCase());
        context.getPlayer().sendMessage("Tu numero es " + targetInt + " y la mitad es " + (targetInt / 2));
        context.getPlayer().sendMessage("Tu bool es " + targetBool + " y el opuesto es " + (!targetBool));
    }

}
