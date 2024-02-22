package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.capability.MeltableItemHandler;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.material.capability.CrucibleFluidHandler;

public class FiredCrucibleBlockBlockEntity extends CrucibleBlockEntity {

  public FiredCrucibleBlockBlockEntity(
      BlockEntityType<? extends FiredCrucibleBlockBlockEntity> tile,
      BlockPos pos,
      BlockState state) {
    super(tile, pos, state);
  }

  @Override
  @Nonnull
  public CrucibleType getCrucibleType() {
    return CrucibleType.FIRED;
  }

  @Override
  public int getSolidAmount() {
    if (!currentItem.isEmpty()) {
      final int itemCount = MeltableItemHandler.getHandler(this).getStackInSlot(0).getCount();
      @Nullable
      final Optional<MeltingRecipe> recipe =
          ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem());
      if (recipe.isPresent()) {
        return solidAmount + (itemCount * recipe.get().getResultFluid().getAmount());
      }
    }
    return solidAmount;
  }

  @Override
  public boolean canAcceptFluidTemperature(@Nonnull FluidStack fluidStack) {
    return true;
  }

  @Override
  protected void processSolid(int heat) {
    final Optional<MeltingRecipe> recipe =
        ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem());
    if (recipe.isPresent()) {
      FluidStack fluidStack = new FluidStack(recipe.get().getResultFluid(), heat);
      int filled = CrucibleFluidHandler.getHandler(this).fill(fluidStack, FluidAction.EXECUTE);
      solidAmount -= filled;
    }
  }

  @Override
  protected void consumeNewSolid() {
    currentItem = MeltableItemHandler.getHandler(this).getStackInSlot(0).copy();
    MeltableItemHandler.getHandler(this).getStackInSlot(0).shrink(1);

    if (MeltableItemHandler.getHandler(this).getStackInSlot(0).isEmpty()) {
      MeltableItemHandler.getHandler(this).setStackInSlot(0, ItemStack.EMPTY);
    }

    ExNihiloRegistries.CRUCIBLE_REGISTRY
        .findRecipe(currentItem.getItem())
        .ifPresent(recipe -> solidAmount = recipe.getResultFluid().getAmount());
  }
}
