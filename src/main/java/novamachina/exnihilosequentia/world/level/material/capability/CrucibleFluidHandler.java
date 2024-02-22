package novamachina.exnihilosequentia.world.level.material.capability;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;

public class CrucibleFluidHandler extends FluidTank {
  private static final Map<BlockEntity, CrucibleFluidHandler> BLOCK_TO_CRUCIBLE =
      new IdentityHashMap<>();

  public static CrucibleFluidHandler getHandler(CrucibleBlockEntity entity) {

    return BLOCK_TO_CRUCIBLE.computeIfAbsent(
        entity, (block1) -> new CrucibleFluidHandler(entity::canAcceptFluidTemperature));
  }

  public CrucibleFluidHandler(Predicate<FluidStack> validator) {
    super(CrucibleBlockEntity.MAX_FLUID_AMOUNT, validator);
  }
}
