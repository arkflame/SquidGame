package dev._2lstudios.jelly.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.errors.ArgumentParserException;
import dev._2lstudios.jelly.errors.PlayerOfflineException;

public class CommandArgumentParser {

    public static Object parse(final Class<?> clazz, final int index, final String str)
            throws PlayerOfflineException, ArgumentParserException {
        Object result = null;

        if (clazz.equals(String.class)) {
            return str;
        }

        else if (clazz.equals(Integer.class)) {
            try {
                result = Integer.parseInt(str);
            } catch (Exception ignored) {
                throw new ArgumentParserException(index, "number");
            }
        }

        else if (clazz.equals(Boolean.class)) {
            try {
                result = Boolean.parseBoolean(str);
            } catch (Exception ignored) {
                throw new ArgumentParserException(index, "boolean");
            }
        }

        else if (clazz.equals(Player.class)) {
            Player player = Bukkit.getPlayer(str);
            if (player == null) {
                throw new PlayerOfflineException(str);
            } else {
                result = player;
            }
        }

        return result;
    }
}
