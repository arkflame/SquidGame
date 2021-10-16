package dev._2lstudios.jelly.utils;

import org.bukkit.Bukkit;

public class ServerUtils {
    public static boolean isLegacy() {
        return Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.7");
    }
}
