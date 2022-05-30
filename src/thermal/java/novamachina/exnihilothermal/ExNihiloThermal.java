package novamachina.exnihilothermal;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilothermal.common.init.ExNihiloThermalInitialization;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;
import org.apache.logging.log4j.LogManager;

@Mod(ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL)
public class ExNihiloThermal {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  public ExNihiloThermal() {
    logger.debug("Starting Ex Nihilo: Sequentia - Thermal Addon");
    ExNihiloThermalInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
  }
}
