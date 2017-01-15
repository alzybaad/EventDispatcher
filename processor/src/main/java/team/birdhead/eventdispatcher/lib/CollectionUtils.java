package team.birdhead.eventdispatcher.lib;

import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean intersects(int[] array1, int[] array2) {
        for (int value : array1) {
            if (contains(array2, value)) {
                return true;
            }
        }

        return false;
    }

    public static boolean contains(int[] array, int target) {
        for (int value : array) {
            if (value == target) {
                return true;
            }
        }

        return false;
    }

    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        final Set<T> union = new LinkedHashSet<>();
        union.addAll(set1);
        union.addAll(set2);
        return union;
    }
}
