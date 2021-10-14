package dev._2lstudios.squidgame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class EntityDamageListener implements Listener {
    private final SquidGame plugin;

    public EntityDamageListener(final SquidGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player bukkitPlayer = (Player) e.getEntity();
            if (bukkitPlayer.getHealth() - e.getDamage() <= 0) {
                final SquidPlayer squidPlayer = (SquidPlayer) this.plugin.getPlayerManager().getPlayer(bukkitPlayer);
                if (squidPlayer.getArena() != null) {
                    squidPlayer.getArena().killPlayer(squidPlayer);
                    e.setCancelled(true);
                }
            }
        }
    }
}