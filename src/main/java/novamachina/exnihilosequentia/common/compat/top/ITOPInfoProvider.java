package novamachina.exnihilosequentia.common.compat.top;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface ITOPInfoProvider {
    void addProbeInfo(@Nonnull final ProbeMode mode, @Nonnull final IProbeInfo probeInfo,
                      @Nonnull final PlayerEntity player, @Nonnull final World world,
                      @Nonnull final BlockState blockState, @Nonnull final IProbeHitData data);
}
