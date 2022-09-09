package novamachina.exnihilothermal.common.init;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;

@Mod.EventBusSubscriber(
    modid = ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
    bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExNihiloThermalInitialization {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private ExNihiloThermalInitialization() {}

  @SubscribeEvent
  public static void onServerStart(ServerStartingEvent event) {
    logger.debug("Fired FMLServerStartingEvent");
    enableOres();
  }

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Initializing modded items");
    ExNihiloThermalItems.init(modEventBus);
  }

  private static void enableOres() {
    //    ExNihiloItems.COPPER.setEnabled(true);
    ExNihiloItems.SILVER.setEnabled(true);
    ExNihiloItems.TIN.setEnabled(true);
    ExNihiloItems.LEAD.setEnabled(true);
    ExNihiloItems.NICKEL.setEnabled(true);
  }
}
