package novamachina.exnihilosequentia.common.compat.top;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public interface ITOPInfoProvider {
    void addProbeInfo(@Nonnull final ProbeMode mode, @Nonnull final IProbeInfo probeInfo,
                      @Nonnull final Player player, @Nonnull final Level world,
                      @Nonnull final BlockState blockState, @Nonnull final IProbeHitData data);
}
