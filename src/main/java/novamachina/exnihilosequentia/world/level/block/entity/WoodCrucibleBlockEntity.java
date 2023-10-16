package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.Optional;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;

public class WoodCrucibleBlockEntity extends CrucibleBlockEntity {

  public WoodCrucibleBlockEntity(
      BlockEntityType<? extends WoodCrucibleBlockEntity> tile, BlockPos pos, BlockState state) {
    super(tile, pos, state);
  }

  @Override
  protected void processSolid(int heat) {
    final Optional<MeltingRecipe> recipe =
        ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem());
    if (recipe.isPresent()) {
      FluidStack fluidStack = new FluidStack(recipe.get().getResultFluid(), heat);
      int filled = tank.fill(fluidStack, FluidAction.EXECUTE);
      solidAmount -= filled;
    }
  }

  @Override
  protected void consumeNewSolid() {
    currentItem = inventory.getStackInSlot(0).copy();
    inventory.getStackInSlot(0).shrink(1);

    if (inventory.getStackInSlot(0).isEmpty()) {
      inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    ExNihiloRegistries.CRUCIBLE_REGISTRY
        .findRecipe(currentItem.getItem())
        .ifPresent(recipe -> solidAmount = recipe.getResultFluid().getAmount());
  }

  @Override
  public int getHeat() {
    return Math.max(super.getHeat(), Config.getWoodHeatRate());
  }

  @Override
  @Nonnull
  public CrucibleType getCrucibleType() {
    return CrucibleType.WOOD;
  }

  @Override
  public int getSolidAmount() {
    if (!currentItem.isEmpty()) {
      final Optional<MeltingRecipe> recipe =
          ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem());
      if (recipe.isPresent()) {
        int itemCount = inventory.getStackInSlot(0).getCount();
        return solidAmount + (itemCount * recipe.get().getResultFluid().getAmount());
      }
    }
    return solidAmount;
  }

  @Override
  public boolean canAcceptFluidTemperature(@Nonnull final FluidStack fluidStack) {
    return fluidStack.getFluid().getFluidType().getTemperature() <= Config.getWoodBarrelMaxTemp();
  }
}
