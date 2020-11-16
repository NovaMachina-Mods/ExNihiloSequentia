package novamachina.exnihilosequentia.common.utility;

import org.apache.logging.log4j.Logger;

public class ExNihiloLogger {

    private final Logger LOGGER;

    public ExNihiloLogger(Logger logger) {
        LOGGER = logger;
    }

    public void info(String msg) {
        LOGGER.info(addPrefix(msg));
    }

    public void warn(String msg) {
        LOGGER.warn(addPrefix(msg));
    }

    public void debug(String msg) {
        if(Config.ENABLE_DEBUG_LOGGING.get()) {
            LOGGER.info(addPrefix(msg));
        }
    }

    public void error(String msg) {
        LOGGER.error(addPrefix(msg));
    }

    private String addPrefix(String msg) {
        return "[Ex Nihilo: Sequentia]: " + msg;
    }

    public void info(int num) {
        info(String.format("%d", num));
    }
}
