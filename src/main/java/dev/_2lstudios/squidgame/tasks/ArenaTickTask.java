package dev._2lstudios.squidgame.tasks;

import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;

public class ArenaTickTask implements Runnable {

    private final SquidGame plugin;

    public ArenaTickTask(final SquidGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (final Arena arena : this.plugin.getArenaManager().getArenas()) {
            arena.setInternalTime(arena.getInternalTime() - 1);
        }
    }
}
