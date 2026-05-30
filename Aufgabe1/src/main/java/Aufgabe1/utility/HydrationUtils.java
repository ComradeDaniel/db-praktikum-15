package Aufgabe1.utility;

import java.util.Optional;

public class HydrationUtils {
    public static Optional<Integer> parseInt(String s, int radix) {
        if (s == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.valueOf(s,radix));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static boolean hasNonNullProperties(Object obj) {
        if (obj == null) return false;

        try {
            for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(obj);
                
                if (value != null) {
                    if (value instanceof java.util.Collection) {
                        if (!((java.util.Collection<?>) value).isEmpty()) {
                            return true;
                        }
                    } else if (value instanceof String) {
                        if (!((String) value).isBlank()) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            // Log or ignore depending on requirements, returning true to be safe
            return true;
        }

        return false;
    }
}