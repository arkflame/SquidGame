package dev._2lstudios.squidgame.arena.games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.math.Cuboid;
import dev._2lstudios.jelly.math.Vector3;
import dev._2lstudios.jelly.utils.NumberUtils;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class G1RedGreenLightGame extends ArenaGameBase {

    private final Cuboid barrier;
    private final Cuboid killZone;
    private final Cuboid goalZone;

    private boolean canWalk = true;
    private boolean playing = false;

    public G1RedGreenLightGame(final Arena arena) {
        super("§aGreen Light §7| §cRed Light", "first", 15, 60, 5, arena);

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

    private void singDoll() {
        if (!this.playing) {
            return;
        }

        final int time = NumberUtils.randomNumber(2, 5);
        this.getArena().broadcastTitle("games.first.green-light.title", "games.first.green-light.subtitle");
        this.canWalk = true;

        Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
            this.getArena().broadcastTitle("games.first.red-light.title", "games.first.red-light.subtitle");

            Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
                this.canWalk = false;
                final int waitTime = NumberUtils.randomNumber(2, 5);
                Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
                    singDoll();
                }, waitTime * 20);
            }, 20);
        }, time * 20);
    }

    @Override
    public void onStart() {
        this.playing = true;
        this.singDoll();
        this.getArena().setPvPAllowed(true);
    }

    @Override
    public void onStop() {
        this.playing = false;
    }

    @Override
    public void onTimeUp() {
        this.getArena().setPvPAllowed(false);
        this.canWalk = false;
        this.playing = false;

        this.getArena().broadcastTitle("events.game-timeout.title", "events.game-timeout.subtitle");

        final List<SquidPlayer> death = new ArrayList<>();
        final List<SquidPlayer> alive = new ArrayList<>();

        for (final SquidPlayer squidPlayer : this.getArena().getPlayers()) {
            final Player player = squidPlayer.getBukkitPlayer();
            final Location location = player.getLocation();
            final Vector3 position = new Vector3(location.getX(), location.getY(), location.getZ());

            if (this.getGoalZone().isBetween(position)) {
                alive.add(squidPlayer);
            } else {
                death.add(squidPlayer);
            }
        }

        Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
            for (final SquidPlayer player : death) {
                player.sendTitle("events.game-timeout-died.title", "events.game-timeout-died.subtitle", 3);
                player.playSound(Sound.ENTITY_CAT_HURT);
            }

            for (final SquidPlayer player : alive) {
                player.sendTitle("events.game-pass.title", "events.game-pass.subtitle", 3);
                player.playSound(Sound.ENTITY_PLAYER_LEVELUP);
            }
        }, 40L);

        Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
            for (final SquidPlayer squidPlayer : death) {
                this.getArena().killPlayer(squidPlayer);
            }
        }, 80L);
    }

    public boolean isCanWalk() {
        return this.canWalk;
    }
}
