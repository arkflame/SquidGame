package dev._2lstudios.temp.arena;

import dev._2lstudios.jelly.math.Cuboid;

public class ArenaSettings {
    public int minPlayers;
    public int maxPlayers;

    public Cuboid game1BarrierZone;
    public Cuboid game1KillZone;
    public Cuboid game1GoalZone;

    public boolean isValid() {
        return this.minPlayers != Integer.MIN_VALUE && this.maxPlayers != Integer.MAX_VALUE && game1BarrierZone != null
                && game1KillZone != null && game1GoalZone != null;
    }
}
