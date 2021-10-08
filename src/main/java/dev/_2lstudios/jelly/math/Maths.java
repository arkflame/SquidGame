package dev._2lstudios.jelly.math;

public class Maths {
    public static boolean inRange(double number, double point1, double point2) {
        double min = Math.min(point1, point2);
        double max = Math.max(point1, point1);

        return min <= number && number <= max;
    }
}