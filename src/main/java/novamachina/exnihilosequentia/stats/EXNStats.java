package novamachina.exnihilosequentia.stats;

import javax.annotation.Nonnull;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.novacore.registries.AbstractRegistry;
import novamachina.novacore.registries.SimpleResourceLocationRegistry;

import java.util.ArrayList;
import java.util.List;

public class EXNStats {

  private static final SimpleResourceLocationRegistry STATS = new SimpleResourceLocationRegistry(ExNihiloSequentia.MOD_ID);

  public static List<ResourceLocation> getDefinitions() {
    return STATS;
  }

  @Nonnull
  public static final ResourceLocation SIEVED = stat("sieved");

  private EXNStats() {
  }
}
