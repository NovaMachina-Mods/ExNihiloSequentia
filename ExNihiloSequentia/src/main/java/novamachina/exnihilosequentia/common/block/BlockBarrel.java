package novamachina.exnihilosequentia.common.block;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;

public class BlockBarrel extends BaseBlock implements ITOPInfoProvider {

  public BlockBarrel(@Nonnull final BlockBuilder builder) {
    super(builder);
  }

  @Override
  public void addProbeInfo(
      @Nonnull final ProbeMode probeMode,
      @Nonnull final IProbeInfo probeInfo,
      @Nonnull final Player playerEntity,
      @Nonnull final Level world,
      @Nonnull final BlockState blockState,
      @Nonnull final IProbeHitData data) {

    @Nullable
    final AbstractBarrelEntity barrelTile =
        (AbstractBarrelEntity) world.getBlockEntity(data.getPos());
    if (barrelTile == null) {
      return;
    }
    if (probeMode == ProbeMode.EXTENDED) {
      probeInfo.text(
          Component.translatable(
                  "top.barrel.mode", barrelTile.getMode().getModeName().toUpperCase())
              .withStyle(
                  style -> {
                    style.withColor(TextColor.fromLegacyFormat(ChatFormatting.GREEN));
                    return style;
                  }));
    }

    @Nonnull final List<Component> info = barrelTile.getWailaInfo();
    for (Component tooltip : info) {
      probeInfo.text(tooltip);
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

    @Nullable final AbstractBarrelEntity tile = (AbstractBarrelEntity) worldIn.getBlockEntity(pos);

    if (tile != null) {
      @Nonnull
      final IFluidHandler fluidHandler =
          tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
              .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
      @Nonnull
      final IItemHandler itemHandler =
          tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, hit.getDirection())
              .orElseThrow(() -> new RuntimeException("Missing Item Handler"));
      return tile.onBlockActivated(player, handIn, fluidHandler, itemHandler);
    }

    return InteractionResult.SUCCESS;
  }

  @Override
  public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
    super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    if (!pPlayer.isCreative()) {
      AbstractBarrelEntity barrelEntity = (AbstractBarrelEntity) pLevel.getBlockEntity(pPos);
      barrelEntity.dropInventory();
    }
  }
}
