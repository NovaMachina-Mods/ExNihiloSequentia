package com.novamachina.exnihilosequentia.common.top;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.InterModComms;

import java.util.function.Function;

public class CompatTOP {
    private static boolean registered;

    public static void register() {
        if(registered) {
            return;
        }
        registered = true;
        InterModComms.sendTo("theoneprobe", "getTheOneProbe", GetTheOneProbe::new);
    }

    public static class GetTheOneProbe implements Function<ITheOneProbe, Void> {

        public static ITheOneProbe probe;

        @Override
        public Void apply(ITheOneProbe iTheOneProbe) {
            probe = iTheOneProbe;
            LogUtil.info("Enabled support for The One Probe");
            probe.registerProvider(new IProbeInfoProvider() {
                @Override
                public String getID() {
                    return Constants.ModIds.EX_NIHILO_SEQUENTIA + ":default";
                }

                @Override
                public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
                    if (blockState.getBlock() instanceof IProbeInfoProvider) {
                        IProbeInfoProvider provider = (IProbeInfoProvider) blockState.getBlock();
                        provider.addProbeInfo(probeMode, iProbeInfo, playerEntity, world, blockState, iProbeHitData);
                    }
                }
            });
            return null;
        }
    }
}
