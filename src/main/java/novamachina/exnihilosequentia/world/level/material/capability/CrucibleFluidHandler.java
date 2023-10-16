package novamachina.exnihilosequentia.world.level.material.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;

public class CrucibleFluidHandler extends FluidTank {

  @Nullable private CrucibleBlockEntity crucible;

  public CrucibleFluidHandler(@Nonnull final CrucibleBlockEntity crucibleTile) {
    super(CrucibleBlockEntity.MAX_FLUID_AMOUNT);
    this.crucible = crucibleTile;
  }

  @Override
  public int fill(FluidStack resource, FluidAction action) {
    if (crucible != null && !crucible.canAcceptFluidTemperature(resource)) {
      return 0;
    }

    return super.fill(resource, action);
  }
}
