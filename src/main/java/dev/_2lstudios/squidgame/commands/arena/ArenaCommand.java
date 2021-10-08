package dev._2lstudios.squidgame.commands.arena;

import dev._2lstudios.jelly.annotations.Command;
import dev._2lstudios.jelly.commands.CommandContext;
import dev._2lstudios.jelly.commands.CommandListener;

@Command(name = "arena", usage = "/squid arena", description = "Arena admin related commands", permission = "squidgame.admin.arena")
public class ArenaCommand extends CommandListener {

    public ArenaCommand() {
        this.addSubcommand(new CreateArenaCommand());
    }

    @Override
    public void handle(CommandContext context) {
        context.getSender().sendMessage("\n§d§lSquid§f§lGame§r §7- §aArena Commands");
        context.getSender().sendMessage(this.mapCommandListToString("§d{usage} §8- §f{description}", "\n"));
    }
}
