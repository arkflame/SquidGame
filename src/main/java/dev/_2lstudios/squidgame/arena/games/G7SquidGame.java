package dev._2lstudios.squidgame.arena.games;

import dev._2lstudios.squidgame.arena.Arena;

public class G7SquidGame extends ArenaGameBase {
    public G7SquidGame(final Arena arena) {
        super("§dSquid§fGame", "seventh", 16, 600, 5, arena);
    }

    @Override
    public void onExplainStart() {
        this.broadcastTitleAfterSeconds(3, "§a1", "§eDebes empujar fuera del campo a los enemigos");
        this.broadcastTitleAfterSeconds(6, "§e2", "§aGanas si eres el único sobreviviente en pie");
        this.broadcastTitleAfterSeconds(9, "§c3", "§cPierdes si alguien te empuja fuera de la arena");
        this.broadcastTitleAfterSeconds(12, "§44", "§eSolo un jugador sobrevivirá. ¡Suerte!");
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onTimeUp() {

    }
}