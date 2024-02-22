package novamachina.exnihilosequentia.world.level.block;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import novamachina.exnihilosequentia.common.compat.ITooltipProvider;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;

public abstract class CrucibleBlock extends Block implements ITooltipProvider {

  public CrucibleBlock(Properties properties) {
    super(properties);
  }

  @Override
  public List<Component> getTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltip = new ArrayList<>();
    final CrucibleBlockEntity crucibleTile = (CrucibleBlockEntity) world.getBlockEntity(pos);
    if (crucibleTile == null) {
      return tooltip;
    }
    return tooltip;
  }

  @Override
  public List<Component> getExpandedTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltip = new ArrayList<>();
    final CrucibleBlockEntity crucibleTile = (CrucibleBlockEntity) world.getBlockEntity(pos);
    if (crucibleTile == null) {
      return tooltip;
    }

    if (crucibleTile.getSolidAmount() > 0) {
      final ItemStack itemStack = crucibleTile.getCurrentItem();
      tooltip.add(
          Component.translatable(
              "waila.crucible.solid",
              Component.translatable(itemStack.getItem().getDescriptionId()),
              crucibleTile.getSolidAmount()));
    }
    if (crucibleTile.getFluidAmount() > 0) {
      @Nullable final Fluid fluid = crucibleTile.getFluid();
      if (fluid != null) {
        tooltip.add(
            Component.translatable(
                "waila.crucible.fluid",
                Component.translatable(
                    fluid.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()),
                crucibleTile.getFluidAmount()));
      }
    }
    if (crucibleTile.getHeat() == 0) {
      tooltip.add(Component.translatable("waila.crucible.no_heat"));
    } else {
      tooltip.add(Component.translatable("waila.crucible.heat", crucibleTile.getHeat()));
    }

    tooltip.addAll(this.getTooltipInfo(world, pos));

    return tooltip;
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

    @Nullable final CrucibleBlockEntity tile = (CrucibleBlockEntity) worldIn.getBlockEntity(pos);

    if (tile != null) {
      IFluidHandler fluidHandler =
          worldIn.getCapability(
              Capabilities.FluidHandler.BLOCK, pos, state, tile, hit.getDirection());
      return tile.onBlockActivated(player, handIn, fluidHandler);
    }
    return InteractionResult.SUCCESS;
  }
}
