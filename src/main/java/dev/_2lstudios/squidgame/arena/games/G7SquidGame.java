package dev._2lstudios.squidgame.arena.games;

import dev._2lstudios.squidgame.arena.Arena;

public class G7SquidGame extends ArenaGameBase {
    public G7SquidGame(final Arena arena, final int durationTime) {
        super("§dSquid§fGame", "seventh", durationTime, arena);
    }

    @Override
    public void onStart() {
        this.getArena().setPvPAllowed(true);
    }

    @Override
    public void onTimeUp() {
        this.getArena().killAllPlayers();
    }
}