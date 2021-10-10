package dev._2lstudios.squidgame.commands.admin;

import java.io.IOException;

import org.bukkit.entity.Player;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandExecutionTarget;
import dev._2lstudios.jelly.commands.CommandListener;
import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.jelly.errors.CommandException;
import dev._2lstudios.squidgame.SquidGame;

@Command(name = "setlobby", usage = "/squid setlobby", description = "Set lobby at your current location", permission = "squidgame.admin.setlobby", target = CommandExecutionTarget.ONLY_PLAYER)
public class SquidSetLobbyCommand extends CommandListener {
    @Override
    public void handle(CommandContext context) throws IOException, CommandException {
        final SquidGame plugin = (SquidGame) context.getPlugin();
        final Player player = context.getPlayer();
        final Configuration config = plugin.getMainConfig();

        config.setLocation("lobby", player.getLocation());
        player.sendMessage("§aSet lobby at your current location.");
        config.save();
    }
}
