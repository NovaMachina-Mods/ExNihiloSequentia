package novamachina.exnihilotinkers;

import java.io.File;
import java.nio.file.Path;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.client.ClientSetup;
import novamachina.exnihilotinkers.common.init.EXNTinkersInit;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConfig;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.apache.logging.log4j.LogManager;

@Mod(EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS)
public class EXNTinkers {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  Path path = FMLPaths.CONFIGDIR.get().resolve("exnihilo-addons");
  File pathString = new File(path.toString());

  public EXNTinkers() {
    logger.debug("Starting Ex Nihilo: Sequentia - Tinkers Addon");
    if (!pathString.exists()) {
      pathString.mkdir();
    }
    EXNTinkersConfig.loadConfig(EXNTinkersConfig.COMMON_CONFIG,
        path.resolve("exntinkers-common.toml"));
    EXNTinkersInit.init(FMLJavaModLoadingContext.get().getModEventBus());
    FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
  }
}
