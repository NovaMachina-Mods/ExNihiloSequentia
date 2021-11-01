package novamachina.exnihilosequentia.api.utility;

import org.apache.logging.log4j.Logger;

public record ExNihiloLogger(Logger logger) {

    private static final String LOGGING_PATTERN = "{} {}";
    private static final String PREFIX = "[Ex Nihilo: Sequentia]:";

    public void debug(String msg) {
        if (Config.enableDebugLogging()) {
            logger.info(LOGGING_PATTERN, PREFIX, msg);
        }
    }

    public void error(String msg) {
        logger.error(LOGGING_PATTERN, PREFIX, msg);
    }

    public void info(String msg) {
        logger.info(LOGGING_PATTERN, PREFIX, msg);
    }

    public void info(int num) {
        info(String.format("%d", num));
    }

    public void warn(String msg) {
        logger.warn(LOGGING_PATTERN, PREFIX, msg);
    }
}
