package novamachina.exnihilosequentia.common.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import novamachina.exnihilosequentia.common.blockentity.crucible.BaseCrucibleEntity;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import org.jetbrains.annotations.NotNull;

public class CrucibleBaseBlock extends BaseBlock implements ITOPInfoProvider {

  public CrucibleBaseBlock(@Nonnull final BlockBuilder builder) {
    super(builder);
  }

  @Override
  public void addProbeInfo(
      @Nonnull final ProbeMode probeMode,
      @Nonnull final IProbeInfo probeInfo,
      @Nonnull final Player playerEntity,
      @Nonnull final Level world,
      @Nonnull final BlockState blockState,
      final @Nonnull IProbeHitData data) {
    if (probeMode == ProbeMode.EXTENDED) {
      gatherExtendedData(probeInfo, world, data);
    }
  }

  private void gatherExtendedData(
      @NotNull IProbeInfo probeInfo, @NotNull Level world, @NotNull IProbeHitData data) {
    @Nullable
    final BaseCrucibleEntity crucibleTile =
        (BaseCrucibleEntity) world.getBlockEntity(data.getPos());
    if (crucibleTile == null) {
      return;
    }
    addSolidInformation(probeInfo, crucibleTile);
    addFluidInformation(probeInfo, crucibleTile);
    addHeatInformation(probeInfo, crucibleTile);
  }

  private void addHeatInformation(
      @NotNull IProbeInfo probeInfo, @NotNull BaseCrucibleEntity crucibleTile) {
    if (crucibleTile.getHeat() == 0) {
      probeInfo.text(Component.translatable("waila.crucible.no_heat"));
    } else {
      probeInfo.text(Component.translatable("waila.crucible.heat", crucibleTile.getHeat()));
    }
  }

  private void addFluidInformation(
      @NotNull IProbeInfo probeInfo, @NotNull BaseCrucibleEntity crucibleTile) {
    if (crucibleTile.getFluidAmount() > 0) {
      @Nullable final Fluid fluid = crucibleTile.getFluid();
      if (fluid != null) {
        probeInfo.text(
            Component.translatable(
                "waila.crucible.fluid",
                Component.translatable(
                    fluid.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()),
                crucibleTile.getFluidAmount()));
      }
    }
  }

  private void addSolidInformation(
      @NotNull IProbeInfo probeInfo, @NotNull BaseCrucibleEntity crucibleTile) {
    if (crucibleTile.getSolidAmount() > 0) {
      final ItemStack itemStack = crucibleTile.getCurrentItem();
      probeInfo.text(
          Component.translatable(
              "waila.crucible.solid",
              Component.translatable(itemStack.getItem().getDescriptionId()),
              crucibleTile.getSolidAmount()));
    }
  }

  /**
   * @deprecated Ask Mojang
   */
  @Nonnull
  @Deprecated(forRemoval = false)
  @Override
  public InteractionResult use(
      @Nonnull final BlockState state,
      @Nonnull final Level worldIn,
      @Nonnull final BlockPos pos,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final BlockHitResult hit) {
    if (worldIn.isClientSide()) {
      return InteractionResult.SUCCESS;
    }

    @Nullable final BaseCrucibleEntity tile = (BaseCrucibleEntity) worldIn.getBlockEntity(pos);

    if (tile != null) {
      IFluidHandler fluidHandler =
          tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
              .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
      return tile.onBlockActivated(player, handIn, fluidHandler);
    }
    return InteractionResult.SUCCESS;
  }
}
