package novamachina.exnihilosequentia.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import novamachina.exnihilosequentia.stats.EXNStats;

public class InitStats {
  public static void init() {
    for (ResourceLocation statId : EXNStats.getDefinitions()) {
      Registry.register(Registry.CUSTOM_STAT, statId, statId);
      Stats.CUSTOM.get(statId, StatFormatter.DEFAULT);
    }
  }
}
