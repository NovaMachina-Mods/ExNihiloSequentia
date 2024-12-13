package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.Optional;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.capability.MeltableItemHandler;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;

public class WoodCrucibleBlockEntity extends CrucibleBlockEntity {

  public WoodCrucibleBlockEntity(
      BlockEntityType<? extends WoodCrucibleBlockEntity> tile, BlockPos pos, BlockState state) {
    super(tile, pos, state);
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
    if (!getCurrentItem().isEmpty()) {
      final Optional<MeltingRecipe> recipe =
          ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(getCurrentItem().getItem());
      if (recipe.isPresent()) {
        int itemCount = MeltableItemHandler.getHandler(this).getStackInSlot(0).getCount();
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
