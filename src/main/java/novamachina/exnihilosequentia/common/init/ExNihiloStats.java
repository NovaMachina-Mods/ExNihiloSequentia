package novamachina.exnihilosequentia.common.init;

import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloStats {
    public static final ResourceLocation SIEVED = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "sieved");

    public static void register() {
        Registry.register(Registry.CUSTOM_STAT, SIEVED, SIEVED);
        Stats.CUSTOM.get(SIEVED, StatFormatter.DEFAULT);
    }
}
