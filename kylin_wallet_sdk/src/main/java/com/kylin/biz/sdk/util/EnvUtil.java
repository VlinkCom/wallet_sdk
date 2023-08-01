package com.kylin.biz.sdk.util;

import org.springframework.util.StringUtils;

public class EnvUtil {
    public static final String ENV_DEV = "test";
    public static final String ENV_DEBUG = "debug";
    public static final String ENV_QA = "test_qa";
    public static final String ENV_BKK_STAGE = "bkk_stage";
    public static final String ENV_BKK_REAL = "bkk";

    public EnvUtil() {
    }

    public static String getEnv() {
        return System.getenv("env");
    }

    public static boolean isTestOrQa() {
        String env = System.getenv("env");
        if (StringUtils.isEmpty(env)) {
            return false;
        } else {
            return env.contains("test") || env.contains("debug");
        }
    }

    public static boolean isDebug() {
        String env = System.getenv("env");
        return StringUtils.isEmpty(env) ? false : env.contains("debug");
    }

    public static boolean isMain() {
        String name = System.getenv("PRODUCT_NAME");
        return StringUtils.isEmpty(name) || name.equalsIgnoreCase("FIEX");
    }
}
