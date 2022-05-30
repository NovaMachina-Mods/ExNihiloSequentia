package novamachina.exnihiloae;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import novamachina.exnihiloae.common.init.ExNihiloAEInitialization;
import novamachina.exnihiloae.common.utility.ExNihiloAEConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod(ExNihiloAEConstants.ModIds.EX_NIHILO_AE)
public class ExNihiloAE {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  public ExNihiloAE() {
    logger.debug("Starting Ex Nihilo: Sequentia - AE2 Addon");
    ExNihiloAEInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
  }
}
