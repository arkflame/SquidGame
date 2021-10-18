package dev._2lstudios.squidgame.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.arena.Arena;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class EntityDamageByEntityListener implements Listener {
    private final SquidGame plugin;

    public EntityDamageByEntityListener(final SquidGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        final Entity entity = e.getDamager();

        if (entity instanceof Player) {
            final Player bukkitPlayer = (Player) entity;
            final SquidPlayer squidPlayer = (SquidPlayer) this.plugin.getPlayerManager().getPlayer(bukkitPlayer);

            if (squidPlayer == null) {
                return;
            }

            final Arena arena = squidPlayer.getArena();

            if (arena == null) {
                return;
            }

            if (!arena.isPvPAllowed()) {
                e.setCancelled(true);
            } else if (bukkitPlayer.getHealth() - e.getDamage() <= 0) {
                squidPlayer.getArena().killPlayer(squidPlayer);
                e.setCancelled(true);
            }
        }
    }
}