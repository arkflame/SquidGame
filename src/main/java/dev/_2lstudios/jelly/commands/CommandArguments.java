package dev._2lstudios.jelly.commands;

import org.bukkit.entity.Player;

public class CommandArguments {

    private final Object[] args;

    public CommandArguments(final Object[] args) {
        this.args = args;
    }

    /* Generic getters */
    public Object get(final int index) {
        if (args.length < index) {
            return null;
        }

        return this.args[index];
    }

    public Object get(final int index, final Object defaultValue) {
        final Object result = this.get(index);
        return result == null ? defaultValue : result;
    }

    /* Primitive java getters */
    public boolean getBoolean(final int index, final boolean defaultValue) {
        return (boolean) this.get(index, defaultValue);
    }

    public boolean getBoolean(final int index) {
        return this.getBoolean(index, false);
    }

    public int getInt(final int index, final int defaultValue) {
        return (int) this.get(index, defaultValue);
    }

    public int getInt(final int index) {
        return this.getInt(index, Integer.MIN_VALUE);
    }

    public String getString(final int index) {
        return (String) this.get(index);
    }

    public String getString(final int index, final String defaultValue) {
        return (String) this.get(index, defaultValue);
    }

    /* Bukkit getters */
    public Player getPlayer(final int index) {
        return (Player) this.get(index);
    }

    public Player getPlayer(final int index, final Player defaultValue) {
        return (Player) this.get(index, defaultValue);
    }
}
