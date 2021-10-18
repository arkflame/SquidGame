package dev._2lstudios.squidgame.commands.game;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.errors.ArenaMisconfiguredException;
import dev._2lstudios.squidgame.errors.NoAvailableArenaException;
import dev._2lstudios.squidgame.player.SquidPlayer;

@Command(name = "start", usage = "/squid start", description = "Force game to start", permission = "squidgame.start", target = CommandExecutionTarget.ONLY_PLAYER)
public class SquidStartCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) throws NoAvailableArenaException, ArenaMisconfiguredException {
        final SquidPlayer player = (SquidPlayer) context.getPluginPlayer();
        final Arena arena = player.getArena();

        if (arena == null) {
            player.sendMessage("arena.not-in-game");
        } else {
            arena.forceStart();
        }
    }
}
