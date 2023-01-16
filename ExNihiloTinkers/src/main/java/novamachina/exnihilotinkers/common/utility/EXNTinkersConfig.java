package novamachina.exnihilotinkers.common.utility;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.nio.file.Path;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EXNTinkersConfig {

  public static final ForgeConfigSpec COMMON_CONFIG;
  private static final String CATEGORY_ORE = "ore";

  private static final Builder COMMON_BUILDER = new Builder();
  // Ore
  private static ForgeConfigSpec.BooleanValue enableCobalt;

  static {
    COMMON_BUILDER.comment("Compatibility Configs").push(CATEGORY_ORE);
    oreConfigs();
    COMMON_BUILDER.pop();

    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  private EXNTinkersConfig() {
  }

  public static boolean enableCobalt() {
    return enableCobalt.get();
  }

  private static void oreConfigs() {
    enableCobalt = COMMON_BUILDER.comment(
            "Enable cobalt ore pieces, chunks and ingots if they exist. 'enableOreOverride' in the main Ex Nihilo config must be true for this to work. (Default: true)")
        .define("enableCobalt", true);
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave()
        .writingMode(WritingMode.REPLACE).build();

    configData.load();
    spec.setConfig(configData);
  }
}
