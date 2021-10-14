package dev._2lstudios.squidgame.arena;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.player.SquidPlayer;

public class ArenaHandler {

    private final Arena arena;
    private final Configuration scoreboardConfig;

    public ArenaHandler(final Arena arena) {
        this.arena = arena;
        this.scoreboardConfig = SquidGame.getInstance().getScoreboardConfig();
    }

    public void handleArenaSwitchState() {
        final ArenaState state = this.arena.getState();
        switch (state) {
            case WAITING:
                arena.broadcastScoreboard(this.scoreboardConfig.getStringList("waiting"));
                break;
            case STARTING:
                arena.broadcastScoreboard(this.scoreboardConfig.getStringList("starting"));
                break;
            case INTERMISSION:
                arena.broadcastScoreboard(this.scoreboardConfig.getStringList("intermission"));
                break;
            case EXPLAIN_GAME:
                arena.broadcastScoreboard(this.scoreboardConfig.getStringList("explaining_game"));
                break;
            case IN_GAME:
                arena.broadcastScoreboard(this.scoreboardConfig.getStringList("in_game"));
                break;
            case FINISHING_GAME:
                arena.broadcastScoreboard(this.scoreboardConfig.getStringList("finishing_game"));
                break;
            case FINISHING_ARENA:
                arena.broadcastScoreboard(this.scoreboardConfig.getStringList("finishing"));
                break;
        }
    }

    public void handlePlayerJoin(final Player bukkitPlayer) {
        arena.broadcastMessage("§a" + bukkitPlayer.getName() + " §ehas joined the game §a(" + arena.getPlayers().size()
                + "/" + arena.getMaxPlayers() + ")");

        if (arena.getPlayers().size() >= arena.getMinPlayers() && arena.getState() == ArenaState.WAITING) {
            arena.setInternalTime(5);
            arena.setState(ArenaState.STARTING);
            arena.broadcastMessage("§aStarting the game in " + arena.getInternalTime() + " seconds.");
        }
    }

    public void handlePlayerLeave(final Player bukkitPlayer) {
        if (this.arena.getState() == ArenaState.FINISHING_ARENA) {
            return;
        }

        arena.broadcastMessage("§c" + bukkitPlayer.getName() + " §ehas leave the game §c(" + arena.getPlayers().size()
                + "/" + arena.getMaxPlayers() + ")");

        if (arena.getPlayers().size() < arena.getMinPlayers() && arena.getState() == ArenaState.STARTING) {
            arena.setState(ArenaState.WAITING);
            arena.broadcastMessage("§cNo enough players to start the game, required: " + arena.getMinPlayers());
            arena.setInternalTime(30);
        }
    }

    public void handleArenaStart() {
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

        else if (arena.getState() == ArenaState.INTERMISSION) {
            if (arena.getInternalTime() == 0) {
                arena.setState(ArenaState.EXPLAIN_GAME);
                arena.teleportAllPlayers(arena.getSpawnPosition());
                arena.setInternalTime(arena.getCurrentGame().getExplainTime());
                arena.getCurrentGame().onExplainStart();
            }
        }

        else if (arena.getState() == ArenaState.FINISHING_ARENA) {
            if (arena.getInternalTime() == 0) {
                arena.resetArena();
            }
        }
    }

    public void handleArenaFinish(final ArenaFinishReason reason) {
        this.arena.setInternalTime(5);

        switch (reason) {
            case ALL_PLAYERS_DEATH:
                this.arena.broadcastTitle("§6DRAW", "§cAll players die");
                return;
            case ONE_PLAYER_IN_ARENA:
                final SquidPlayer winner = this.arena.calculateWinner();
                this.arena.broadcastTitle("§d§lFINISHED",
                        "§b" + winner.getBukkitPlayer().getName() + " §ehas won the game.");
                break;
            case PLUGIN_STOP:
                break;
        }
    }
}