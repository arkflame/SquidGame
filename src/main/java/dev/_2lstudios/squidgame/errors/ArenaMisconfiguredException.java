package dev._2lstudios.squidgame.errors;

import dev._2lstudios.jelly.errors.CommandException;

public class ArenaMisconfiguredException extends CommandException {
    public ArenaMisconfiguredException() {
        super("Arena is misconfigured, contact server administrator.");
    }
}
