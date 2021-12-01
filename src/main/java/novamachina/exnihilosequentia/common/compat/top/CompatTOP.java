package novamachina.exnihilosequentia.common.compat.top;

//import mcjty.theoneprobe.api.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.InterModComms;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.function.Function;

public class CompatTOP {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static boolean registered;

    private CompatTOP() {
    }

    /*public static void register() {
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
                public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData iProbeHitData) {
                    if (blockState.getBlock() instanceof ITOPInfoProvider provider) {
                        provider.addProbeInfo(probeMode, iProbeInfo, playerEntity, world, blockState, iProbeHitData);
                    }
                }

                @Override
                public ResourceLocation getID() {
                    return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":default");
                }
            });
            return null;
        }
    }*/
}
