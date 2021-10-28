package dev._2lstudios.jelly.player;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import dev._2lstudios.jelly.utils.ServerUtils;

public class PluginPlayer {
    private final Player player;

    public PluginPlayer(final Player player) {
        this.player = player;
    }

    public Player getBukkitPlayer() {
        return this.player;
    }

    public void playSound(final Sound sound) {
        if (sound != null) {
            this.getBukkitPlayer().playSound(this.getBukkitPlayer().getLocation(), sound, 1, 1);
        }
    }

    public void teleport(final Location loc) {
        this.getBukkitPlayer().teleport(loc);
    }

    public void sendTitle(final String title, final String subtitle, final int duration) {
        this.sendTitle(title, subtitle, 2, duration * 20, 2);
    }

    public void spawnFirework(final int amount, final int power, final Color color, final boolean flicker) {
        final Location loc = this.getBukkitPlayer().getLocation().clone().add(0, 1, 0);

        final Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        final FireworkMeta meta = firework.getFireworkMeta();
        meta.setPower(power);
        meta.addEffect(FireworkEffect.builder().withColor(color).flicker(flicker).withTrail().build());
        firework.setFireworkMeta(meta);

        for (int i = 1; i < amount; i++) {
            final Firework fireworkCopy = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fireworkCopy.setFireworkMeta(meta);
        }
    }

    @SuppressWarnings("deprecation")
    public void sendTitle(final String title, final String subtitle, final int fadeInTime, final int showTime,
            final int fadeOutTime) {
        if (ServerUtils.isLegacy()) {
            this.getBukkitPlayer().resetTitle();
            this.getBukkitPlayer().sendTitle(title, subtitle);
        } else {
            this.getBukkitPlayer().sendTitle(title, subtitle, fadeInTime, showTime, fadeOutTime);
        }
    }

}
