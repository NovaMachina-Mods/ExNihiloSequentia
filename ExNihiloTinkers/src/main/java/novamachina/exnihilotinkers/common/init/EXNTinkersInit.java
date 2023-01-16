package novamachina.exnihilotinkers.common.init;

import com.mojang.logging.LogUtils;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConfig;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;

@Mod.EventBusSubscriber(
    modid = EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS,
    bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EXNTinkersInit {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private EXNTinkersInit() {}

  public static void init(IEventBus eventBus) {
    EXNTinkersBlockEntites.init(eventBus);
    EXNTinkersBlocks.init(eventBus);
    EXNTinkersItems.init(eventBus);
    // TODO reanable when tinkers available for 1.18.2
    // EXNTinkersTinkerItems.init(eventBus);
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
