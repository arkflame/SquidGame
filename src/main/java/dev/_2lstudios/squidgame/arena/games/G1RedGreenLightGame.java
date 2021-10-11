package dev._2lstudios.squidgame.arena.games;

import dev._2lstudios.jelly.math.Cuboid;
import dev._2lstudios.squidgame.arena.Arena;

public class G1RedGreenLightGame extends ArenaGameBase {

    private final Cuboid barrier;
    private final Cuboid killZone;
    private final Cuboid goalZone;

    public G1RedGreenLightGame(final Arena arena) {
        super("§cRed Light §7| §aGreen Light", "first", 15, 5, 5, arena);

        this.barrier = arena.getConfig().getCuboid("games.first.barrier");
        this.killZone = arena.getConfig().getCuboid("games.first.killzone");
        this.goalZone = arena.getConfig().getCuboid("games.first.goal");
    }

    public Cuboid getBarrier() {
        return this.barrier;
    }

    public Cuboid getKillZone() {
        return this.killZone;
    }

    public Cuboid getGoalZone() {
        return this.goalZone;
    }

    @Override
    public void onExplainStart() {
        this.broadcastTitleAfterSeconds(3, "§a1", "§ePara ganar debes llegar a la §bmeta");
        this.broadcastTitleAfterSeconds(6, "§e2", "§eSolo puedes moverte cuando la muñeca diga §aLuz Verde");
        this.broadcastTitleAfterSeconds(9, "§c3", "§eSi te mueves en §cluz roja§e, quedarás eliminado.");
        this.broadcastTitleAfterSeconds(12, "§44", "§eTendrán solo 3 minutos, suerte.");
    }

    @Override
    public void onStart() {
        this.getArena().broadcastMessage("gb barrier");
    }

    @Override
    public void onTimeUp() {
        this.getArena().broadcastTitle("§c¡Se acabó!", "§eEl tiempo se ha agotado");
    }

}
