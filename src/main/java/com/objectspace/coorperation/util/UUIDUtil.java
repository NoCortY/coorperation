package com.objectspace.coorperation.util;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        String result = uuid.replace("-", "");
        return result;
    }
}
