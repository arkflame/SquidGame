package dev._2lstudios.jelly.errors;

public class PlayerOfflineException extends Exception {
    public PlayerOfflineException(final String player) {
        super("Player " + player + " isn't online.");
    }
}
