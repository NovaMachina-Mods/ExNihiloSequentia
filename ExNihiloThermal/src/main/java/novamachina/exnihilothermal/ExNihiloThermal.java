package novamachina.exnihilothermal;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilothermal.common.init.ExNihiloThermalInitialization;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;

@Mod(ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL)
public class ExNihiloThermal {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public ExNihiloThermal() {
    logger.debug("Starting Ex Nihilo: Sequentia - Thermal Addon");
    ExNihiloThermalInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
  }
}
