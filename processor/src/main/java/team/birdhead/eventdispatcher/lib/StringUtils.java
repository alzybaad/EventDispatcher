package team.birdhead.eventdispatcher.lib;

import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean equals(String string1, String string2) {
        return string1 != null ? string1.equals(string2) : string2 == null;
    }

    public static <T> String join(String separator, T[] objects) {
        return join(separator, Arrays.asList(objects));
    }

    public static <T> String join(String separator, Iterable<T> objects) {
        final StringBuilder builder = new StringBuilder();
        for (Object object : objects) {
            if (builder.length() != 0) {
                builder.append(separator);
            }
            builder.append(object);
        }
        return builder.toString();
    }
}
