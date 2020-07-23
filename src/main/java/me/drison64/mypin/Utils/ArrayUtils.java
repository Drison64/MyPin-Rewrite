package me.drison64.mypin.Utils;

public class ArrayUtils {

    public static boolean contains(Object[] objectarray, Object object) {
        for (Object ob : objectarray) {
            if (ob == object) {
                return true;
            }
        }
        return false;
    }

}
