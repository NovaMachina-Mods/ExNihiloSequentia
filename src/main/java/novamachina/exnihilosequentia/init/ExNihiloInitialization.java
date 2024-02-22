package novamachina.exnihilosequentia.init;

import javax.annotation.Nonnull;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Mod.EventBusSubscriber(
//    modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
//    bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExNihiloInitialization {

  private static Logger log = LoggerFactory.getLogger(ExNihiloInitialization.class);

  private ExNihiloInitialization() {}

  // neoforged.neoforge.EVENT_BUS

  public static void init(@Nonnull final IEventBus modEventBus) {
    log.debug("Initializing modded items");
  }

  // neoforged.neoforge.EVENT_BUS

  // neoforged.neoforge.EVENT_BUS

  // neoforged.neoforge.EVENT_BUS

  // TODO: MOVE TO TOP ADDON
  // neoforged.neoforge.EVENT_BUS
  //  @SubscribeEvent
  //  public static void registerTOP(@Nonnull final InterModEnqueueEvent event) {
  //    log.debug("The One Probe detected: " +
  // ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP));
  //    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP)) {
  //      CompatTOP.register();
  //    }
  //  }

  // neoforged.neoforge.EVENT_BUS

}
