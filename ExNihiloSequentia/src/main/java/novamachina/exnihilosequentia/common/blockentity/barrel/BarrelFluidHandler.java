package novamachina.exnihilosequentia.common.blockentity.barrel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class BarrelFluidHandler extends FluidTank {

  @Nullable
  private AbstractBarrelEntity barrel;

  public BarrelFluidHandler(@Nonnull final AbstractBarrelEntity barrelTile) {
    super(AbstractBarrelEntity.MAX_FLUID_AMOUNT);
    this.barrel = barrelTile;
  }

  @Override
  public int fill(@Nonnull final FluidStack resource, @Nonnull final FluidAction action) {
    if (barrel == null || !barrel.canAcceptFluidTemperature(resource)) {
      return 0;
    }

    if (!barrel.getMode().canFillWithFluid(barrel)) {
      return 0;
    }

    int amount = super.fill(resource, action);

    if (amount > 0 && this.fluid != FluidStack.EMPTY && (this.barrel.getMode().getModeName()
        .equals(ExNihiloConstants.BarrelModes.EMPTY) || this.barrel.getMode()
        .getModeName().equals(ExNihiloConstants.BarrelModes.FLUID))) {
      this.barrel.setMode(ExNihiloConstants.BarrelModes.FLUID);
    }
    return amount;
  }
}
