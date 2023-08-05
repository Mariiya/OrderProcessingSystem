package com.mako.utils;

import java.util.UUID;

public final class EventHelper {

    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

}
