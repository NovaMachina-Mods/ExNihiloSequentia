package novamachina.exnihilotinkers.common.init;

import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.init.tconstruct.EXNTinkersTinkerItems;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConfig;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(
    modid = EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS,
    bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class EXNTinkersInit {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private EXNTinkersInit() {
  }

  public static void init(IEventBus eventBus) {
    EXNTinkersBlockEntites.init(eventBus);
    EXNTinkersBlocks.init(eventBus);
    EXNTinkersItems.init(eventBus);
    EXNTinkersTinkerItems.init(eventBus);
  }

  @SubscribeEvent
  public static void onServerStart(ServerStartingEvent event) {
    logger.debug("Fired FMLServerStartingEvent");
    overrideOres();
  }

  private static void overrideOres() {
    if (Config.enableOreOverride()) {
      EXNTinkersItems.COBALT.setEnabled(EXNTinkersConfig.enableCobalt());
    }
  }
}
