package dev._2lstudios.squidgame.arena.games;

import org.bukkit.Location;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class G3BattleGame extends ArenaGameBase {
    public G3BattleGame(final Arena arena, final int durationTime) {
        super("§cBattle", "third", durationTime, arena);
    }

    @Override
    public Location getSpawnPosition() {
        final Configuration config = this.getArena().getConfig();
        final Location location = config.getLocation("arena.waiting_room", false);
        location.setWorld(this.getArena().getWorld());
        return location;
    }

    @Override
    public void onStart() {
        this.getArena().setPvPAllowed(true);
    }

    @Override
    public void onTimeUp() {
        for (final SquidPlayer alivePlayer : this.getArena().getPlayers()) {
            alivePlayer.sendTitle("events.game-pass.title", "events.game-pass.subtitle", 2);
        }
    }
}