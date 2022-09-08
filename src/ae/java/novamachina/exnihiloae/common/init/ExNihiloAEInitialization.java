package novamachina.exnihiloae.common.init;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class ExNihiloAEInitialization {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private ExNihiloAEInitialization() {
  }

  public static void init(IEventBus modEventBus) {
    logger.debug("Initializing modded items");
    ExNihiloAEBlocks.init(modEventBus);
    ExNihiloAEItems.init(modEventBus);
  }
}
