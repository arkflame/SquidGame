package dev._2lstudios.squidgame.errors;

import dev._2lstudios.jelly.errors.CommandException;

public class NoAvailableArenaException extends CommandException {
    public NoAvailableArenaException() {
        super("No available arena.");
    }
}
