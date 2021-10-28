package dev._2lstudios.squidgame.arena.games;

import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class G3BattleGame extends ArenaGameBase {

    private final int durationTime;

    public G3BattleGame(final Arena arena, final int durationTime) {
        super("§cBattle", "third", durationTime, arena);
        this.durationTime = durationTime;
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

        if (this.getArena().getMainConfig().getBoolean("game-settings.give-blindness-in-game-3", true)) {
            this.getArena()
                    .broadcastPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.durationTime * 20, 1));
        }
    }

    @Override
    public void onStop() {
        if (this.getArena().getMainConfig().getBoolean("game-settings.give-blindness-in-game-3", true)) {
            this.getArena().broadcastRemovePotionEffect(PotionEffectType.BLINDNESS);
        }
    }

    @Override
    public void onTimeUp() {
        this.getArena().setPvPAllowed(false);

        for (final SquidPlayer alivePlayer : this.getArena().getPlayers()) {
            alivePlayer.sendTitle("events.game-pass.title", "events.game-pass.subtitle", 2);
        }
    }
}