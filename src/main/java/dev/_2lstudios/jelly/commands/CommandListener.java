package dev._2lstudios.jelly.commands;

import java.util.ArrayList;
import java.util.List;

import dev._2lstudios.jelly.annotations.Command;

public abstract class CommandListener {

    private List<CommandListener> subcommands = new ArrayList<>();

    public void addSubcommand(final CommandListener obj) {
        this.subcommands.add(obj);
    }

    public CommandListener getSubcommand(final String name) {
        for (final CommandListener subcommand : this.subcommands) {
            Command cmd = subcommand.getClass().getAnnotation(Command.class);
            if (cmd.name().equalsIgnoreCase(name)) {
                return subcommand;
            }
        }

        return null;
    }

    public abstract void handle(final CommandContext context);
}
