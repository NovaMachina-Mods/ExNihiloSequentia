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

public class CompatTOP {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
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
        private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

        @Override
        public Void apply(ITheOneProbe iTheOneProbe) {
            logger.info("Enabled support for The One Probe");
            iTheOneProbe.registerProvider(new IProbeInfoProvider() {
                @Override
                public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
                    if (blockState.getBlock() instanceof ITOPInfoProvider) {
                        ITOPInfoProvider provider = (ITOPInfoProvider) blockState.getBlock();
                        provider.addProbeInfo(probeMode, iProbeInfo, playerEntity, world, blockState, iProbeHitData);
                    }
                }

                @Override
                public String getID() {
                    return ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":default";
                }
            });
            return null;
        }
    }
}
