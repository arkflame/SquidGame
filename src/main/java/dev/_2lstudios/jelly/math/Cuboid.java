package dev._2lstudios.jelly.math;

public class Cuboid {
    private final Vector3 firstPoint;
    private final Vector3 secondPoint;

    public Cuboid(final Vector3 firstPoint, final Vector3 secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public boolean isBetween(final Vector3 target) {
        return target.isBetweenPoints(firstPoint, secondPoint);
    }

    public Vector3 getFirstPoint() {
        return this.firstPoint;
    }

    public Vector3 getSecondPoint() {
        return this.secondPoint;
    }
}
