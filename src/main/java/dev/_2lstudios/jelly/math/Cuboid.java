package dev._2lstudios.jelly.math;

public class Cuboid {
    private final Vector3 firstPoint;
    private final Vector3 secondPoint;

    public Cuboid(final Vector3 firstPoint, final Vector3 secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public boolean isBetween(final Vector3 target) {
        double x1 = this.firstPoint.getX();
        double z1 = this.firstPoint.getZ();

        double x2 = this.secondPoint.getX();
        double z2 = this.secondPoint.getZ();

        double xP = target.getX();
        double zP = target.getZ();
        
        return ((x1 < xP && xP < x2) || (x1 > xP && xP > x2)) && ((z1 < zP && zP < z2) || (z1 > zP && zP > z2));
    }

    public Vector3 getFirstPoint() {
        return this.firstPoint;
    }

    public Vector3 getSecondPoint() {
        return this.secondPoint;
    }
}
