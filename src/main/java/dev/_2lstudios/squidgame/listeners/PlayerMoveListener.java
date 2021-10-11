package dev._2lstudios.squidgame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import dev._2lstudios.jelly.math.Vector3;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.arena.ArenaState;
import dev._2lstudios.squidgame.arena.games.G1RedGreenLightGame;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class PlayerMoveListener implements Listener {

    private final SquidGame plugin;

    public PlayerMoveListener(final SquidGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
        final SquidPlayer player = (SquidPlayer) this.plugin.getPlayerManager().getPlayer(e.getPlayer());
        final Arena arena = player.getArena();

        if (arena == null || player.isSpectator()) {
            return;
        }

        if (arena.getCurrentGame() instanceof G1RedGreenLightGame) {
            final G1RedGreenLightGame game = (G1RedGreenLightGame) arena.getCurrentGame();

            if (arena.getState() == ArenaState.EXPLAIN_GAME) {
                final Vector3 playerPosition = new Vector3(e.getTo().getX(), e.getTo().getY(), e.getTo().getZ());
                if (game.getBarrier().isBetween(playerPosition)) {
                    e.setCancelled(true);
                    e.setTo(e.getFrom());
                }
            }
        }
    }
}
