package novamachina.exnihilosequentia.common.compat.top;

import java.util.function.Function;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.InterModComms;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompatTOP {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static boolean registered;

    private CompatTOP() {
    }

    public static void register() {
        logger.debug("Registering The One Probe");
        if (registered) {
            return;
        }
        registered = true;
        InterModComms.sendTo("theoneprobe", "getTheOneProbe", GetTheOneProbe::new);
    }

    public static class GetTheOneProbe implements Function<ITheOneProbe, Void> {
        @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

        @Override
        @Nullable
        public Void apply(@Nonnull final ITheOneProbe iTheOneProbe) {
            logger.info("Enabled support for The One Probe");
            iTheOneProbe.registerProvider(new IProbeInfoProvider() {
                @Override
                public void addProbeInfo(@Nonnull final ProbeMode probeMode, @Nonnull final IProbeInfo iProbeInfo,
                                         @Nonnull final PlayerEntity playerEntity, @Nonnull final World world,
                                         @Nonnull final BlockState blockState,
                                         @Nonnull final IProbeHitData iProbeHitData) {
                    if (blockState.getBlock() instanceof ITOPInfoProvider) {
                        @Nonnull final ITOPInfoProvider provider = (ITOPInfoProvider) blockState.getBlock();
                        provider.addProbeInfo(probeMode, iProbeInfo, playerEntity, world, blockState, iProbeHitData);
                    }
                }

                @Override
                @Nonnull
                public String getID() {
                    return ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":default";
                }
            });
            return null;
        }
    }
}
