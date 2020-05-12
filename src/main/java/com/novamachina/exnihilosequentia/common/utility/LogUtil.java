package com.novamachina.exnihilosequentia.common.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void info(String msg) {
        LOGGER.info(addPrefix(msg));
    }

    public static void warn(String msg) {
        LOGGER.warn(addPrefix(msg));
    }

    public static void debug(String msg) {
        LOGGER.debug(addPrefix(msg));
    }

    public static void error(String msg) {
        LOGGER.error(addPrefix(msg));
    }

    private static String addPrefix(String msg) {
        return "[Ex Nihilo: Sequentia]: " + msg;
    }
}
