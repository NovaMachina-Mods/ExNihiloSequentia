package novamachina.exnihilosequentia.common.init;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloStats {
    public static final ResourceLocation SIEVED = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "sieved");
    public static final ResourceLocation HAMMERED = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "hammered");
    public static final ResourceLocation CROOKED = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crooked");

    public static void register() {
        registerStat(SIEVED);
        registerStat(HAMMERED);
        registerStat(CROOKED);
    }

    private static void registerStat(ResourceLocation stat) {
        Registry.register(Registry.CUSTOM_STAT, stat, stat);
        Stats.CUSTOM.get(stat, IStatFormatter.DEFAULT);
    }
}
