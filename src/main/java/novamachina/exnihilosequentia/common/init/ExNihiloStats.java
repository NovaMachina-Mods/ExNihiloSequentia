package novamachina.exnihilosequentia.common.init;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

public class ExNihiloStats {
    @Nonnull public static final ResourceLocation SIEVED = new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "sieved");

    public static void register() {
        Registry.register(Registry.CUSTOM_STAT, SIEVED, SIEVED);
        Stats.CUSTOM.get(SIEVED, IStatFormatter.DEFAULT);
    }
}
