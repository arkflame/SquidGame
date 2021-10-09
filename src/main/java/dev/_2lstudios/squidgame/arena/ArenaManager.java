package dev._2lstudios.squidgame.arena;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.WorldCreator;

import dev._2lstudios.jelly.utils.FileUtils;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.errors.ArenaMisconfiguredException;
import dev._2lstudios.squidgame.errors.NoAvailableArenaException;

public class ArenaManager {

    private final List<Arena> arenas;

    private final File serverPath;
    private final File templatePath;

    private final SquidGame plugin;
    private boolean readyToPlay;

    public ArenaManager(final SquidGame plugin) {
        this.arenas = new ArrayList<>();
        this.serverPath = plugin.getServer().getWorldContainer();
        this.templatePath = new File(plugin.getDataFolder(), "map");
        this.plugin = plugin;

        this.initArenas();
    }

    public void clear() {
        this.arenas.clear();
    }

    public void initArenas() {
        if (!this.templatePath.exists()) {
            this.readyToPlay = false;
            return;
        }

        for (int i = 0; i < plugin.getMainConfig().getInt("concurrent-max-games"); i++) {
            final String id = "game-" + i;
            FileUtils.copyFolder(this.templatePath, new File(this.serverPath, id));
            final World world = new WorldCreator(id).createWorld();
            this.arenas.add(new Arena(world, this.plugin.getArenaConfig()));
        }

        this.readyToPlay = true;
    }

    public Arena getFirstAvailableArena() throws NoAvailableArenaException, ArenaMisconfiguredException {
        if (!this.readyToPlay) {
            throw new ArenaMisconfiguredException();
        }

        for (final Arena arena : arenas) {
            if (arena.getState() == ArenaState.WAITING || arena.getState() == ArenaState.STARTING) {
                return arena;
            }
        }

        throw new NoAvailableArenaException();
    }

    public List<Arena> getArenas() {
        return this.arenas;
    }
}
