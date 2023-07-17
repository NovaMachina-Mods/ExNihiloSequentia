package novamachina.exnihilosequentia.common.blockentity.crucible;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class CrucibleFluidHandler extends FluidTank {

  @Nullable private BaseCrucibleEntity crucible;

  public CrucibleFluidHandler(@Nonnull final BaseCrucibleEntity crucibleTile) {
    super(BaseCrucibleEntity.MAX_FLUID_AMOUNT);
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
