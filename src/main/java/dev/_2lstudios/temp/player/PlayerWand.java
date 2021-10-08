package dev._2lstudios.temp.player;

import org.bukkit.Location;

import dev._2lstudios.jelly.math.Cuboid;
import dev._2lstudios.jelly.math.Vector3;

public class PlayerWand {

    private Vector3 firstPoint;
    private Vector3 secondPoint;

    public Vector3 getFirstPoint() {
        return this.firstPoint;
    }

    public Vector3 getSecondPoint() {
        return this.secondPoint;
    }

    public Cuboid getCuboid() {
        return new Cuboid(this.firstPoint, this.secondPoint);
    }

    public void setFirstPoint(final Vector3 vector) {
        this.firstPoint = vector;
    }

    public void setSecondPoint(final Vector3 vector) {
        this.secondPoint = vector;
    }

    public void setFirstPoint(final Location loc) {
        this.firstPoint = new Vector3(loc.getX(), loc.getY(), loc.getZ());
    }

    public void setSecondPoint(final Location loc) {
        this.secondPoint = new Vector3(loc.getX(), loc.getY(), loc.getZ());
    }
}
