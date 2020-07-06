package me.drison64.mypin.Utils;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

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
