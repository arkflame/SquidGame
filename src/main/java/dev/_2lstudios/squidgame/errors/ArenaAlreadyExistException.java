package dev._2lstudios.squidgame.errors;

import dev._2lstudios.jelly.errors.CommandException;

public class ArenaAlreadyExistException extends CommandException {
    public ArenaAlreadyExistException(final String name) {
        super("Arena " + name + " already exist.");
    }
}
