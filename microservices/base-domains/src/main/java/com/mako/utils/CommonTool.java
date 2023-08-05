package com.mako.utils;

import java.util.Collection;

public class CommonTool {

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return "".equals(object);
        }
        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty();
        }
        return false;
    }
}
