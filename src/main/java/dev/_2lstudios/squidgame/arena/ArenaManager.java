package dev._2lstudios.squidgame.arena;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.squidgame.SquidGame;
import dev._2lstudios.squidgame.errors.ArenaAlreadyExistException;
import dev._2lstudios.squidgame.errors.ArenaMisconfiguredException;
import dev._2lstudios.squidgame.errors.NoAvailableArenaException;

public class ArenaManager {

    private final List<Arena> arenas;
    private final File arenasPath;

    public ArenaManager(final SquidGame plugin) {
        this.arenas = new ArrayList<>();
        this.arenasPath = new File(plugin.getDataFolder(), "arenas");

        if (!this.arenasPath.exists()) {
            this.arenasPath.mkdirs();
        }

        try {
            this.scanForArenas();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanForArenas() throws IOException {
        for (final File fileEntry : this.arenasPath.listFiles()) {
            if (!fileEntry.isDirectory() && fileEntry.getName().endsWith(".yml")) {
                final Configuration arenaConfig = new Configuration(fileEntry);

                try {
                    arenaConfig.load();
                } catch (Exception e) {
                }

                final String name = fileEntry.getName().split("[.]")[0];

                World world = Bukkit.getWorld(arenaConfig.getString("arena.world"));
                if (world == null) {
                    world = new WorldCreator(arenaConfig.getString("arena.world")).createWorld();
                }

                final Arena arena = new Arena(world, name, arenaConfig);
                this.arenas.add(arena);
            }
        }
    }

    public Arena getArena(final String name) {
        for (final Arena arena : this.arenas) {
            if (arena.getName().equalsIgnoreCase(name)) {
                return arena;
            }
        }

        return null;
    }

    public Arena createArena(final String name, final World world) throws ArenaAlreadyExistException, IOException {
        if (this.getArena(name) != null) {
            throw new ArenaAlreadyExistException(name);
        }

        final Configuration arenaConfig = new Configuration(new File(this.arenasPath, name + ".yml"));
        final Arena arena = new Arena(world, name, arenaConfig);
        arenaConfig.set("arena.world", world.getName());
        arenaConfig.save();
        this.arenas.add(arena);
        return arena;
    }

    public Arena getFirstAvailableArena() throws NoAvailableArenaException, ArenaMisconfiguredException {
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
