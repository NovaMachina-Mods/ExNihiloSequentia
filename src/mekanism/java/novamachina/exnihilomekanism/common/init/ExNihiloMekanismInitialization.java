package novamachina.exnihilomekanism.common.init;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

@Mod.EventBusSubscriber(
    modid = ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM,
    bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ExNihiloMekanismInitialization {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private ExNihiloMekanismInitialization() {
  }

  @SubscribeEvent
  public static void onServerStart(ServerStartingEvent event) {
    logger.debug("Fired ServerStartingEvent");
    enableOres();
  }

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Initializing modded items");
    ExNihiloMekanismItems.init(modEventBus);
  }

  private static void enableOres() {
    ExNihiloItems.TIN.setEnabled(true);
    ExNihiloItems.COPPER.setEnabled(true);
    ExNihiloItems.URANIUM.setEnabled(true);
    ExNihiloItems.LEAD.setEnabled(true);
  }
}
