package dev._2lstudios.squidgame.errors;

import dev._2lstudios.jelly.errors.I18nCommandException;

public class ArenaAlreadyExistException extends I18nCommandException {
    public ArenaAlreadyExistException(final String name) {
        super("setup.arena-already-exist", "Arena " + name + " already exist.");
    }
}
