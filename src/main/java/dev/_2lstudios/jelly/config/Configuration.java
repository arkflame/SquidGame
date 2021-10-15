package dev._2lstudios.jelly.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import dev._2lstudios.jelly.math.Cuboid;
import dev._2lstudios.jelly.math.Vector3;
import dev._2lstudios.jelly.utils.WorldUtils;

public class Configuration extends YamlConfiguration {

    private final File file;

    public Configuration(final File file) {
        this.file = file;
    }

    public void load() throws FileNotFoundException, IOException, InvalidConfigurationException {
        this.load(this.file);
    }

    public void save() throws IOException {
        this.save(this.file);
    }

    public Vector3 getVector3(final String key) {
        final double x = this.getDouble(key + ".x");
        final double y = this.getDouble(key + ".y");
        final double z = this.getDouble(key + ".z");

        return new Vector3(x, y, z);
    }

    public Cuboid getCuboid(final String key) {
        final Vector3 firstPoint = this.getVector3(key + ".first_point");
        final Vector3 secondPoint = this.getVector3(key + ".second_point");
        return new Cuboid(firstPoint, secondPoint);
    }

    public Location getLocation(final String key, final boolean getWorld) {
        final World world = getWorld ? WorldUtils.getWorldSafe(this.getString(key + ".world")) : null;
        final double x = this.getDouble(key + ".x");
        final double y = this.getDouble(key + ".y");
        final double z = this.getDouble(key + ".z");
        final float pitch = (float) this.getDouble(key + ".pitch");
        final float yaw = (float) this.getDouble(key + ".yaw");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public Location getLocation(final String key) {
        return this.getLocation(key, true);
    }

    public void setVector3(final String key, final Vector3 vector) {
        this.set(key + ".x", vector.getX());
        this.set(key + ".y", vector.getY());
        this.set(key + ".z", vector.getZ());
    }

    public void setCuboid(final String key, final Cuboid cuboid) {
        this.setVector3(key + ".first_point", cuboid.getFirstPoint());
        this.setVector3(key + ".second_point", cuboid.getSecondPoint());
    }

    public void setLocation(final String key, final Location location, final boolean includeWorld) {
        if (includeWorld) {
            this.set(key + ".world", location.getWorld().getName());
        }

        this.set(key + ".x", location.getX());
        this.set(key + ".y", location.getY());
        this.set(key + ".z", location.getZ());
        this.set(key + ".pitch", location.getPitch());
        this.set(key + ".yaw", location.getYaw());
    }

    public void setLocation(final String key, final Location location) {
        this.setLocation(key, location, true);
    }
}
