package dev._2lstudios.jelly.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BlockUtils {
    public static void destroyBlockGroup(final Block block, final boolean useParticles) {
        final Material target = block.getType();
        final Block up = block.getRelative(BlockFace.SOUTH);
        final Block down = block.getRelative(BlockFace.NORTH);
        final Block left = block.getRelative(BlockFace.EAST);
        final Block right = block.getRelative(BlockFace.WEST);

        if (useParticles) {
            block.breakNaturally();
        } else {
            block.setType(Material.AIR);
        }

        if (up.getType() == target) {
            destroyBlockGroup(up, useParticles);
        }

        if (down.getType() == target) {
            destroyBlockGroup(down, useParticles);
        }

        if (left.getType() == target) {
            destroyBlockGroup(left, useParticles);
        }

        if (right.getType() == target) {
            destroyBlockGroup(right, useParticles);
        }
    }

    public static void destroyBlockGroup(final Block block) {
        destroyBlockGroup(block, true);
    }
}
