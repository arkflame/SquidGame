package dev._2lstudios.squidgame.commands.game;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.errors.ArenaMisconfiguredException;
import dev._2lstudios.squidgame.errors.NoAvailableArenaException;
import dev._2lstudios.squidgame.player.SquidPlayer;

@Command(name = "leave", usage = "/squid leave", description = "Leave your current game", permission = "squidgame.leave", target = CommandExecutionTarget.ONLY_PLAYER)
public class SquidLeaveCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) throws NoAvailableArenaException, ArenaMisconfiguredException {
        final SquidPlayer player = (SquidPlayer) context.getPluginPlayer();

        final Arena arena = player.getArena();
        if (arena != null) {
            arena.removePlayer(player);
        } else {
            player.sendMessage("arena.not-in-game");
        }
    }
}
