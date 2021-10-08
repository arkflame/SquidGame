package dev._2lstudios.jelly.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.JellyPlugin;
import dev._2lstudios.jelly.annotations.Command;

public class CommandHandler implements CommandExecutor {

    private final Map<String, CommandListener> commands;
    private final JellyPlugin plugin;

    public CommandHandler(final JellyPlugin plugin) {
        this.commands = new HashMap<>();
        this.plugin = plugin;
    }

    public void addCommand(final CommandListener listener) {
        if (listener.getClass().isAnnotationPresent(Command.class)) {
            Command command = listener.getClass().getAnnotation(Command.class);
            this.commands.put(command.name(), listener);
            this.plugin.getCommand(command.name()).setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmdInfo, String label, String[] args) {
        final String name = cmdInfo.getName().toLowerCase();
        final CommandListener listener = this.commands.get(name);
        final Command command = listener.getClass().getAnnotation(Command.class);

        if (command.target() != null) {
            if (sender instanceof Player && command.target() == CommandExecutionTarget.ONLY_CONSOLE) {
                sender.sendMessage("§cThis command can run only in console.");
                return true;
            } else if (sender instanceof ConsoleCommandSender
                    && command.target() == CommandExecutionTarget.ONLY_PLAYER) {
                sender.sendMessage("§cThis command can run only by a player.");
                return true;
            }
        }

        else if (command.permission() != null && !sender.hasPermission(command.permission())) {
            sender.sendMessage("§cMissing permission " + command.permission());
            return true;
        }

        if (command.arguments() != null && args.length < command.arguments().length) {
            sender.sendMessage(command.usage());
            return true;
        }

        final Object[] argList = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            final Class<?> clazz = command.arguments()[i];
            try {
                final Object arg = CommandArgumentParser.parse(clazz, i + 1, args[i]);
                argList[i] = arg;
            } catch (Exception e) {
                sender.sendMessage(e.getMessage());
                return true;
            }
        }

        final CommandArguments arguments = new CommandArguments(argList);
        final CommandContext context = new CommandContext(this.plugin, sender, arguments);
        listener.handle(context);

        return true;
    }

}
