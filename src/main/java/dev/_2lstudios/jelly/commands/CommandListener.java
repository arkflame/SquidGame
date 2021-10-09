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

    public String mapCommandListToString(final String format, final String separator) {
        String output = null;

        for (final CommandListener cmd : this.subcommands) {
            final Command cmdInfo = cmd.getClass().getAnnotation(Command.class);
            final String entry = format.replace("{name}", cmdInfo.name()).replace("{permission}", cmdInfo.permission())
                    .replace("{usage}", cmdInfo.usage()).replace("{description}", cmdInfo.description());

            if (output == null) {
                output = entry;
            } else {
                output += separator + entry;
            }
        }

        return output;
    }

    public abstract void handle(final CommandContext context) throws Exception;
}
