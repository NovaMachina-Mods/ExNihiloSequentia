package novamachina.exnihilosequentia.common.compat.top;

import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.InterModComms;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompatTOP {

  private static Logger log = LoggerFactory.getLogger(CompatTOP.class);
  private static boolean registered;

  private CompatTOP() {}

  public static void register() {
    log.debug("Registering The One Probe");
    if (registered) {
      return;
    }
    registered = true;
    InterModComms.sendTo("theoneprobe", "getTheOneProbe", GetTheOneProbe::new);
  }

  public static class GetTheOneProbe implements Function<ITheOneProbe, Void> {

    @Override
    @Nullable
    public Void apply(@Nonnull final ITheOneProbe iTheOneProbe) {
      log.info("Enabled support for The One Probe");
      iTheOneProbe.registerProvider(
          new IProbeInfoProvider() {
            @Override
            public void addProbeInfo(
                @Nonnull final ProbeMode probeMode,
                @Nonnull final IProbeInfo iProbeInfo,
                @Nonnull final Player playerEntity,
                @Nonnull final Level world,
                @Nonnull final BlockState blockState,
                @Nonnull final IProbeHitData iProbeHitData) {
              if (blockState.getBlock() instanceof ITOPInfoProvider) {
                @Nonnull final ITOPInfoProvider provider = (ITOPInfoProvider) blockState.getBlock();
                provider.addProbeInfo(
                    probeMode, iProbeInfo, playerEntity, world, blockState, iProbeHitData);
              }
            }

            @Override
            @Nonnull
            public ResourceLocation getID() {
              return new ResourceLocation(
                  ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":default");
            }
          });
      return null;
    }
  }
}
