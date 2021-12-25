package novamachina.exnihilosequentia.common.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExNihiloLogger {

    private static final String LOGGING_PATTERN = "{} {}";
    private static final String PREFIX = "[Ex Nihilo: Sequentia]:";
    private final Logger logger;

    public ExNihiloLogger(Class<?> className) {
        this.logger = LoggerFactory.getLogger(className);
    }

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
