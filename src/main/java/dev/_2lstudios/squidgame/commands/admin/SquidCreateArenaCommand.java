package dev._2lstudios.squidgame.commands.admin;

import java.io.IOException;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.jelly.errors.CommandException;
import dev._2lstudios.jelly.errors.I18nCommandException;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.player.SquidPlayer;

@Command(name = "createarena", usage = "/squid createarena [name]", description = "Create a new arena in your current world", permission = "squidgame.admin", target = CommandExecutionTarget.ONLY_PLAYER, arguments = {
        String.class })
public class SquidCreateArenaCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) throws IOException, CommandException, I18nCommandException {
        final SquidGame plugin = (SquidGame) context.getPlugin();
        final SquidPlayer player = (SquidPlayer) context.getPluginPlayer();
        final String arenaName = context.getArguments().getString(0);
        plugin.getArenaManager().createArena(arenaName, player.getBukkitPlayer().getWorld());
        player.sendMessage("setup.arena-created");
    }
}
