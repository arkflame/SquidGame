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
        super("§cRed Light §7| §aGreen Light", "first", 15, 90, 5, arena);

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
        this.getArena().broadcastTitle("§2§lO§r", "§aGreen Light");
        this.canWalk = true;

        Bukkit.getScheduler().runTaskLater(SquidGame.getInstance(), () -> {
            this.getArena().broadcastTitle("§4§lO§r", "§cRed Light");

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
    public void onExplainStart() {
        this.broadcastTitleAfterSeconds(3, "§a1", "§ePara ganar debes llegar a la §bmeta");
        this.broadcastTitleAfterSeconds(6, "§e2", "§eSolo puedes moverte cuando la muñeca diga §aLuz Verde");
        this.broadcastTitleAfterSeconds(9, "§c3", "§eSi te mueves en §cluz roja§e, quedarás eliminado.");
        this.broadcastTitleAfterSeconds(12, "§44", "§eTendrán solo 90 segundos, suerte.");
    }

    @Override
    public void onStart() {
        this.getArena().broadcastTitle("§6¡Adelante!", "§aBuena suerte");
        this.playing = true;
        this.singDoll();
    }

    @Override
    public void onTimeUp() {
        this.canWalk = false;
        this.playing = false;

        this.getArena().broadcastTitle("§c¡Se acabó!", "§eEl tiempo se ha agotado");

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
            for (final SquidPlayer squidPlayer : death) {
                final Player player = squidPlayer.getBukkitPlayer();
                player.sendTitle("§4Has muerto", "§cNo has llegado a tiempo", 2, 60, 2);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
            }

            for (final SquidPlayer squidPlayer : alive) {
                final Player player = squidPlayer.getBukkitPlayer();
                player.sendTitle("§2¡Has pasado!", "§aAvanzas a la siguiente ronda", 2, 60, 2);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
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
