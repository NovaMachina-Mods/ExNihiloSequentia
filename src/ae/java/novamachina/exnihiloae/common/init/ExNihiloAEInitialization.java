package novamachina.exnihiloae.common.init;

import net.minecraftforge.eventbus.api.IEventBus;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloAEInitialization {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private ExNihiloAEInitialization() {
  }

  public static void init(IEventBus modEventBus) {
    logger.debug("Initializing modded items");
    ExNihiloAEBlocks.init(modEventBus);
    ExNihiloAEItems.init(modEventBus);
  }
}
