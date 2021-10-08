package dev._2lstudios.jelly.utils;

public class NumberUtils {

    final static private String[] numberNames = new String[] { "Zero", "First", "Second", "Third", "Fourth" };

    public static String formatNumber(final int number) {
        if (NumberUtils.numberNames.length < number) {
            return String.valueOf(number);
        } else {
            return numberNames[number];
        }
    }
}
