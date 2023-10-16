package novamachina.exnihilosequentia.core.registries;

import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.stats.EXNStats;
import novamachina.novacore.core.IRegistry;

public class InitStats {
  public static void init(IRegistry<ResourceLocation> registry) {
    for (ResourceLocation statId : EXNStats.getDefinitions()) {
      registry.register(statId);
    }
  }
}
