package novamachina.exnihilosequentia.common.utility;

import org.apache.logging.log4j.Logger;

public class ExNihiloLogger {

    private static final String LOGGING_PATTERN = "{} {}";
    private static final String PREFIX = "[Ex Nihilo: Sequentia]:";
    private final Logger logger;

    public ExNihiloLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(String msg) {
        logger.info(LOGGING_PATTERN, PREFIX, msg);
    }

    public void warn(String msg) {
        logger.warn(LOGGING_PATTERN, PREFIX, msg);
    }

    public void debug(String msg) {
        if(Config.getEnableDebugLogging()) {
            logger.info(LOGGING_PATTERN, PREFIX, msg);
        }
    }

    public void error(String msg) {
        logger.error(LOGGING_PATTERN, PREFIX, msg);
    }

    public void info(int num) {
        info(String.format("%d", num));
    }
}
