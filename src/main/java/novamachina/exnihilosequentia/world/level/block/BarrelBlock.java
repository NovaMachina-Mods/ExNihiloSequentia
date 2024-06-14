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
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.compat.ITooltipProvider;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;

public abstract class BarrelBlock extends Block implements ITooltipProvider {

  protected BarrelBlock(Properties properties) {
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

  @Override
  protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
    if (level.isClientSide()) {
      return ItemInteractionResult.SUCCESS;
    }

    @Nullable final BarrelBlockEntity tile = (BarrelBlockEntity) level.getBlockEntity(blockPos);

    if (tile != null) {
      @Nonnull
      final IFluidHandler fluidHandler =
          level.getCapability(
              Capabilities.FluidHandler.BLOCK, blockPos, blockState, tile, blockHitResult.getDirection());
      @Nonnull
      final IItemHandler itemHandler =
          level.getCapability(
              Capabilities.ItemHandler.BLOCK, blockPos, blockState, tile, blockHitResult.getDirection());
      return tile.onBlockActivated(player, interactionHand, fluidHandler, itemHandler);
    }

    return ItemInteractionResult.SUCCESS;
  }

  @Override
  public BlockState playerWillDestroy(
      Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
    super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    if (!pPlayer.isCreative()) {
      BarrelBlockEntity barrelEntity = (BarrelBlockEntity) pLevel.getBlockEntity(pPos);
      barrelEntity.dropInventory();
    }
    return pState;
  }
}
