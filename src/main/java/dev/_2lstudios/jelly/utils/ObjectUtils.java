package dev._2lstudios.jelly.utils;

public class ObjectUtils {
    public static boolean checkEquals(final Object obj1, final Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }

        if (obj1 == null || obj2 == null) {
            return false;
        }

        return obj1.equals(obj2);
    }
}
