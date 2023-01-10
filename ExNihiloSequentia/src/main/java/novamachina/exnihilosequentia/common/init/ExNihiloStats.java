package novamachina.exnihilosequentia.common.init;

import javax.annotation.Nonnull;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloStats {

  @Nonnull
  public static final ResourceLocation SIEVED = new ResourceLocation(
      ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "sieved");

  private ExNihiloStats() {
  }

  public static void register() {
    Registry.register(Registry.CUSTOM_STAT, SIEVED, SIEVED);
    Stats.CUSTOM.get(SIEVED, StatFormatter.DEFAULT);
  }
}
