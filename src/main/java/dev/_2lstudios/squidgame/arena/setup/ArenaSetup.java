package dev._2lstudios.squidgame.arena.setup;

import org.bukkit.Location;

import dev._2lstudios.jelly.math.Cuboid;

public class ArenaSetup {
    private ArenaSetupStep step;

    private Location waitRoomSpawn;

    private Location game1Spawn;
    private Cuboid game1BarrierZone;
    private Cuboid game1GoalZone;
    private Cuboid game1KillZone;

    private boolean finished = false;

    public ArenaSetup() {
        this.step = ArenaSetupStep.WAIT_LOBBY;
    }

    public boolean validateStep() {
        if (this.step == ArenaSetupStep.WAIT_LOBBY) {
            return waitRoomSpawn != null;
        }

        else if (this.step == ArenaSetupStep.GAME_1) {
            return this.game1BarrierZone != null && this.game1GoalZone != null && this.game1KillZone != null
                    && this.game1Spawn != null;
        }

        return false;
    }

    public boolean nextStep() {
        if (!this.validateStep()) {
            return false;
        }

        if (this.step == ArenaSetupStep.WAIT_LOBBY) {
            this.step = ArenaSetupStep.GAME_1;
        }

        else if (this.step == ArenaSetupStep.GAME_1) {
            this.step = ArenaSetupStep.FINISHED;
        }

        if (this.step == ArenaSetupStep.FINISHED) {
            this.finished = true;
        }

        return true;
    }

    public boolean isFinished() {
        return this.finished;
    }
}
