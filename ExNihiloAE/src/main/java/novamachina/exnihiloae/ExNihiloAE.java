package novamachina.exnihiloae;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import novamachina.exnihiloae.common.init.ExNihiloAEInitialization;
import novamachina.exnihiloae.common.utility.ExNihiloAEConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

@Mod(ExNihiloAEConstants.ModIds.EX_NIHILO_AE)
public class ExNihiloAE {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public ExNihiloAE() {
    logger.debug("Starting Ex Nihilo: Sequentia - AE2 Addon");
    ExNihiloAEInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
  }
}
