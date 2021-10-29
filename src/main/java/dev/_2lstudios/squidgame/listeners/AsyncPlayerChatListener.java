package dev._2lstudios.squidgame.listeners;

import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev._2lstudios.jelly.utils.ObjectUtils;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class AsyncPlayerChatListener implements Listener {

    private final SquidGame plugin;

    public AsyncPlayerChatListener(final SquidGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent e) {
        if (this.plugin.getMainConfig().getBoolean("game-settings.per-arena-chat", true)) {
            final SquidPlayer thisPlayer = (SquidPlayer) this.plugin.getPlayerManager().getPlayer(e.getPlayer());
            final Iterator<Player> players = e.getRecipients().iterator();

            if (thisPlayer == null) {
                return;
            }

            while (players.hasNext()) {
                final Player bukkitPlayer = players.next();
                final SquidPlayer recipient = (SquidPlayer) this.plugin.getPlayerManager().getPlayer(bukkitPlayer);

                if (!ObjectUtils.checkEquals(recipient.getArena(), thisPlayer.getArena())) {
                    players.remove();
                }
            }
        }
    }
}
