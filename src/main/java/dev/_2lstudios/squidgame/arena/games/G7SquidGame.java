package dev._2lstudios.squidgame.arena.games;

import dev._2lstudios.squidgame.arena.Arena;

public class G7SquidGame extends ArenaGameBase {
    public G7SquidGame(final Arena arena) {
        super("§dSquid§fGame", "seventh", 12, 600, 5, arena);
    }

    @Override
    public void onStart() {
        this.getArena().setPvPAllowed(true);
    }
}