package novamachina.exnihilosequentia.common.blockentity.crucible;

import java.util.Optional;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;

public class WoodCrucibleEntity extends BaseCrucibleEntity {

  public WoodCrucibleEntity(BlockPos pos, BlockState state) {
    this(ExNihiloBlockEntities.WOODEN_CRUCIBLE_ENTITY.get(), pos, state);
  }

  public WoodCrucibleEntity(
      BlockEntityType<? extends WoodCrucibleEntity> tile, BlockPos pos, BlockState state) {
    super(tile, pos, state);
  }

  @Override
  protected void processSolid(int heat) {
    final Optional<CrucibleRecipe> recipe =
        ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
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
        .findRecipeByItemStack(currentItem)
        .ifPresent(recipe -> solidAmount = recipe.getAmount());
  }

  @Override
  public int getHeat() {
    return Math.max(super.getHeat(), Config.getWoodHeatRate());
  }

  @Override
  @Nonnull
  public CrucibleTypeEnum getCrucibleType() {
    return CrucibleTypeEnum.WOOD;
  }

  @Override
  public int getSolidAmount() {
    if (!currentItem.isEmpty()) {
      final CrucibleRecipe recipe =
          ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem).orElse(null);
      if (recipe != null) {
        int itemCount = inventory.getStackInSlot(0).getCount();
        return solidAmount + (itemCount * recipe.getAmount());
      }
    }
    return solidAmount;
  }

  @Override
  public boolean canAcceptFluidTemperature(@Nonnull final FluidStack fluidStack) {
    return fluidStack.getFluid().getFluidType().getTemperature() <= Config.getWoodBarrelMaxTemp();
  }
}
