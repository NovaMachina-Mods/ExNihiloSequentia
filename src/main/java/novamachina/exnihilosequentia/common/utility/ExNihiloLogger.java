package novamachina.exnihilosequentia.common.utility;

import javax.annotation.Nonnull;
import org.apache.logging.log4j.Logger;

public class ExNihiloLogger {

  @Nonnull
  private static final String LOGGING_PATTERN = "{} {}";
  @Nonnull
  private static final String PREFIX = "[Ex Nihilo: Sequentia]:";
  @Nonnull
  private final Logger logger;

  public ExNihiloLogger(@Nonnull final Logger logger) {
    this.logger = logger;
  }

  public void debug(@Nonnull final String msg) {
    if (Config.enableDebugLogging()) {
      logger.info(LOGGING_PATTERN, PREFIX, msg);
    }
  }

  public void error(@Nonnull final String msg) {
    logger.error(LOGGING_PATTERN, PREFIX, msg);
  }

  public void info(@Nonnull final String msg) {
    logger.info(LOGGING_PATTERN, PREFIX, msg);
  }

  public void info(final int num) {
    info(String.format("%d", num));
  }

  public void warn(@Nonnull final String msg) {
    logger.warn(LOGGING_PATTERN, PREFIX, msg);
  }
}
