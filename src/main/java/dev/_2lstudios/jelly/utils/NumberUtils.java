package dev._2lstudios.jelly.utils;

import java.util.Random;

public class NumberUtils {

    final static private String[] numberNames = new String[] { "Zero", "First", "Second", "Third", "Fourth" };

    public static String formatNumber(final int number) {
        if (NumberUtils.numberNames.length < number) {
            return String.valueOf(number);
        } else {
            return numberNames[number];
        }
    }

    public static int randomNumber(final int min, final int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
