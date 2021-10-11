package dev._2lstudios.squidgame.arena;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ArenaHandler {

    private final Arena arena;

    public ArenaHandler(final Arena arena) {
        this.arena = arena;
    }

    public void handlePlayerJoin(final Player bukkitPlayer) {
        arena.broadcastMessage("§a" + bukkitPlayer.getName() + " §ehas joined the game §a(" + arena.getPlayers().size()
                + "/" + arena.getMaxPlayers() + ")");

        if (arena.getPlayers().size() >= arena.getMinPlayers() && arena.getState() == ArenaState.WAITING) {
            arena.setState(ArenaState.STARTING);
            arena.setInternalTime(10);
            arena.broadcastMessage("§aStarting the game in " + arena.getInternalTime() + " seconds.");
        }
    }

    public void handlePlayerLeave(final Player bukkitPlayer) {
        arena.broadcastMessage("§c" + bukkitPlayer.getName() + " §ehas leave the game §c(" + arena.getPlayers().size()
                + "/" + arena.getMaxPlayers() + ")");

        if (arena.getPlayers().size() < arena.getMinPlayers() && arena.getState() == ArenaState.STARTING) {
            arena.setState(ArenaState.WAITING);
            arena.broadcastMessage("§cNo enough players to start the game, required: " + arena.getMinPlayers());
        }
    }

    public void handleArenaStart() {
        arena.setState(ArenaState.EXPLAIN_GAME);
        arena.broadcastMessage("§aGame started, good luck!");
        arena.nextGame();
        arena.broadcastSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
    }

    public void handleArenaTick() {
        if (arena.getState() == ArenaState.STARTING) {
            if (arena.getInternalTime() == 10) {
                arena.broadcastMessage("§aStarting the game in §e" + arena.getInternalTime() + " §aseconds.");
                arena.broadcastSound(Sound.UI_BUTTON_CLICK);
            }

            else if (arena.getInternalTime() <= 5 && arena.getInternalTime() > 0) {
                arena.broadcastMessage("§aStarting the game in §6" + arena.getInternalTime() + " §aseconds.");
                arena.broadcastSound(Sound.BLOCK_NOTE_BLOCK_PLING);
            }

            else if (arena.getInternalTime() == 0) {
                this.handleArenaStart();
            }
        }

        else if (arena.getState() == ArenaState.EXPLAIN_GAME) {
            if (arena.getInternalTime() == 0) {
                this.arena.setState(ArenaState.IN_GAME);
                this.arena.setInternalTime(this.arena.getCurrentGame().getGameTime());
                this.arena.getCurrentGame().onStart();
            }
        }

        else if (arena.getState() == ArenaState.IN_GAME) {
            if (arena.getInternalTime() == 0) {
                this.arena.setState(ArenaState.FINISHING_GAME);
                this.arena.setInternalTime(this.arena.getCurrentGame().getFinishTime());
                this.arena.getCurrentGame().onTimeUp();
            }
        }

        else if (arena.getState() == ArenaState.FINISHING_GAME) {
            if (arena.getInternalTime() == 0) {
                this.arena.nextGame();
            }
        }
    }
}
