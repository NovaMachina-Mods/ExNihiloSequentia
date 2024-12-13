package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.Optional;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;

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
    if (!getCurrentItem().isEmpty()) {
      final int itemCount = getCurrentItem().getCount();
      Optional<MeltingRecipe> recipe =
          ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(getCurrentItem().getItem());
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
}
