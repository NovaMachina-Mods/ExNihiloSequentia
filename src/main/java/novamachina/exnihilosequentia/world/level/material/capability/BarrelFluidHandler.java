package novamachina.exnihilosequentia.world.level.material.capability;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;

public class BarrelFluidHandler extends FluidTank {

  private static final Map<BlockEntity, BarrelFluidHandler> BLOCK_TO_BARREL =
      new IdentityHashMap<>();
  private final BarrelBlockEntity barrel;

  public static BarrelFluidHandler getHandler(BarrelBlockEntity entity) {
    return BLOCK_TO_BARREL.computeIfAbsent(
        entity,
        (block) ->
            new BarrelFluidHandler(
                (fluidStack ->
                    entity.canAcceptFluidTemperature(fluidStack)
                        && entity.getMode().canFillWithFluid(entity)),
                entity));
  }

  public BarrelFluidHandler(Predicate<FluidStack> validator, BarrelBlockEntity entity) {
    super(BarrelBlockEntity.MAX_FLUID_AMOUNT);
    this.barrel = entity;
  }

  @Override
  public int fill(@Nonnull final FluidStack resource, @Nonnull final FluidAction action) {
    int amount = super.fill(resource, action);

    if (amount > 0
        && this.fluid != FluidStack.EMPTY
        && (this.barrel.getMode().getModeName().equals(ExNihiloConstants.BarrelModes.EMPTY)
            || this.barrel.getMode().getModeName().equals(ExNihiloConstants.BarrelModes.FLUID))) {
      this.barrel.setMode(ExNihiloConstants.BarrelModes.FLUID);
    }
    return amount;
  }
}
