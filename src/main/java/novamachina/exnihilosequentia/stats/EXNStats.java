package novamachina.exnihilosequentia.stats;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.novacore.core.registries.SimpleResourceLocationRegistry;

public class EXNStats {

  private static final SimpleResourceLocationRegistry STATS =
      new SimpleResourceLocationRegistry(ExNihiloSequentia.MOD_ID);

  public static List<ResourceLocation> getDefinitions() {
    return STATS.getRegistry();
  }

  @Nonnull public static final ResourceLocation SIEVED = STATS.register("sieved");

  private EXNStats() {}
}
