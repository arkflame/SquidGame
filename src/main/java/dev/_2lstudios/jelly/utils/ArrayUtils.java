package dev._2lstudios.jelly.utils;

import java.util.Arrays;

public class ArrayUtils {
    public static boolean[] shift(boolean[] array, int index) {
        return Arrays.copyOfRange(array, 1, array.length);
    }

    public static String[] shift(String[] array, int index) {
        return Arrays.copyOfRange(array, 1, array.length);
    }

    public static int[] shift(int[] array, int index) {
        return Arrays.copyOfRange(array, 1, array.length);
    }

    public static String join(Object[] array, String joinCharacter) {
        String output = null;
        for (final Object str : array) {
            output = output == null ? str.toString() : output + joinCharacter + str.toString();
        }
        return output;
    }
}
