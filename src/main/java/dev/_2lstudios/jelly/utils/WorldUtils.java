package dev._2lstudios.jelly.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;

public class WorldUtils {
    public static World getWorldSafe(final String name) {
        World world = Bukkit.getWorld(name);

        if (world == null) {
            world = new WorldCreator(name).generateStructures(false).environment(Environment.NORMAL)
                    .type(WorldType.FLAT).createWorld();
        }

        return world;
    }
}
