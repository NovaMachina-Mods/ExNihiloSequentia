package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class CrucibleFluidHandler extends FluidTank {
    private BaseCrucibleTile crucible;

    public CrucibleFluidHandler(BaseCrucibleTile crucibleTile) {
        this(BaseCrucibleTile.MAX_FLUID_AMOUNT);
        this.crucible = crucibleTile;
    }

    private CrucibleFluidHandler(int bucketVolume) {
        super(bucketVolume);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if(!crucible.canAcceptFluidTemperature(resource)) {
            return 0;
        }

        return super.fill(resource, action);
    }
}
