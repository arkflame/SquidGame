package dev._2lstudios.squidgame.commands.admin;

import java.io.IOException;

import org.bukkit.entity.Player;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.jelly.errors.CommandException;
import dev._2lstudios.squidgame.SquidGame;

@Command(name = "createarena", usage = "/squid createarena [name]", description = "Create a new arena in your current world", permission = "squidgame.admin", target = CommandExecutionTarget.ONLY_PLAYER, arguments = {
        String.class })
public class SquidCreateArenaCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) throws IOException, CommandException {
        final SquidGame plugin = (SquidGame) context.getPlugin();
        final Player player = context.getPlayer();
        final String arenaName = context.getArguments().getString(0);
        plugin.getArenaManager().createArena(arenaName, player.getWorld());
        player.sendMessage("§aHas creado la arena §e" + arenaName
                + " §aen el mundo actual. Editala con §e/squid editarena " + arenaName);
    }
}
