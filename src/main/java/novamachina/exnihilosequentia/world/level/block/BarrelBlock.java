package novamachina.exnihilosequentia.world.level.block;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.compat.ITooltipProvider;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;

public abstract class BarrelBlock extends Block implements ITooltipProvider {

  public BarrelBlock(Properties properties) {
    super(properties);
  }

  @Override
  public List<Component> getTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltips = new ArrayList<>();
    final BarrelBlockEntity barrelTile = (BarrelBlockEntity) world.getBlockEntity(pos);
    if (barrelTile == null) {
      return tooltips;
    }
    tooltips.addAll(barrelTile.getWailaInfo());
    return tooltips;
  }

  @Override
  public List<Component> getExpandedTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltips = new ArrayList<>();
    final BarrelBlockEntity barrelTile = (BarrelBlockEntity) world.getBlockEntity(pos);
    if (barrelTile == null) {
      return tooltips;
    }
    tooltips.add(
        Component.translatable("top.barrel.mode", barrelTile.getMode().getModeName().toUpperCase())
            .withStyle(
                style -> {
                  style.withColor(TextColor.fromLegacyFormat(ChatFormatting.GREEN));
                  return style;
                }));

    tooltips.addAll(this.getTooltipInfo(world, pos));

    return tooltips;
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

    @Nullable final BarrelBlockEntity tile = (BarrelBlockEntity) worldIn.getBlockEntity(pos);

    if (tile != null) {
      @Nonnull
      final IFluidHandler fluidHandler =
          tile.getCapability(Capabilities.FLUID_HANDLER, hit.getDirection())
              .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
      @Nonnull
      final IItemHandler itemHandler =
          tile.getCapability(Capabilities.ITEM_HANDLER, hit.getDirection())
              .orElseThrow(() -> new RuntimeException("Missing Item Handler"));
      return tile.onBlockActivated(player, handIn, fluidHandler, itemHandler);
    }

    return InteractionResult.SUCCESS;
  }

  @Override
  public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
    super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    if (!pPlayer.isCreative()) {
      BarrelBlockEntity barrelEntity = (BarrelBlockEntity) pLevel.getBlockEntity(pPos);
      barrelEntity.dropInventory();
    }
  }
}
