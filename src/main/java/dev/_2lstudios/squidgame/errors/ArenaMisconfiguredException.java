package dev._2lstudios.squidgame.errors;

import dev._2lstudios.jelly.errors.I18nCommandException;

public class ArenaMisconfiguredException extends I18nCommandException {
    public ArenaMisconfiguredException() {
        super("arena.misconfigured-arena", "Arena is misconfigured, contact server administrator.");
    }
}
