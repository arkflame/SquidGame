package dev._2lstudios.squidgame.commands.admin;

import java.io.IOException;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.jelly.errors.CommandException;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.gui.EditArenaGUI;

@Command(name = "editarena", usage = "/squid editarena [arena]", description = "Edit an arena", permission = "squidgame.admin", target = CommandExecutionTarget.ONLY_PLAYER, arguments = {
        String.class })
public class SquidEditArenaCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) throws IOException, CommandException {
        final String arenaName = context.getArguments().getString(0);
        final Arena arena = ((SquidGame) context.getPlugin()).getArenaManager().getArena(arenaName);

        if (arena == null) {
            context.getPlayer().sendMessage("§cArena " + arenaName + " doesn't exist.");
        } else {
            EditArenaGUI gui = new EditArenaGUI(arena);
            gui.open(context.getPlayer());
        }
    }
}
