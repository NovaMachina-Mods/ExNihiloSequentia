package novamachina.exnihilosequentia.common.tileentity.barrel;

import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class BarrelFluidHandler extends FluidTank {
    private AbstractBarrelTile barrel;

    public BarrelFluidHandler(AbstractBarrelTile barrelTile) {
        this(AbstractBarrelTile.MAX_FLUID_AMOUNT);
        this.barrel = barrelTile;
    }

    public BarrelFluidHandler(int bucketVolume) {
        super(bucketVolume);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if(!barrel.canAcceptFluidTemperature(resource)) {
            return 0;
        }

        if (barrel.getMode() != null && !barrel.getMode().canFillWithFluid(barrel)) {
            return 0;
        }

        int amount = super.fill(resource, action);
        if (amount > 0) {
            if (this.fluid != FluidStack.EMPTY && (this.barrel.getMode().getModeName()
                .equals(Constants.BarrelModes.EMPTY) || this.barrel.getMode()
                .getModeName().equals(Constants.BarrelModes.FLUID))) {
                this.barrel.setMode(Constants.BarrelModes.FLUID);
            }
        }
        return amount;
    }
}
