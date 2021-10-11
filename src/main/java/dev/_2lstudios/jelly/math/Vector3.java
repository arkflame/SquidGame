package dev._2lstudios.jelly.math;

public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Vector3 clone() {
        return new Vector3(this.getX() + 0, this.getX() + 0, this.getZ() + 0);
    }

    public Vector3 minus(Vector3 vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
        return this;
    }

    public Vector3 plus(Vector3 vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        return this;
    }

    public String toString() {
        return this.x + ", " + this.y + ", " + this.z;
    }
}
