package dev._2lstudios.jelly.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandListener {

    private List<CommandListener> subcommands;

    public CommandListener() {
        this.subcommands = new ArrayList<>();
    }

    public void addSubcommand(final CommandListener obj) {
        this.subcommands.add(obj);
    }

    public abstract void handle(final CommandContext context);
}
