package com.lnjecit.netty.util;

import java.util.UUID;

public class IdUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

}
