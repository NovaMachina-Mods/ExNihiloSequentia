package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleFluidHandler extends FluidTank {
    @Nullable private BaseCrucibleTile crucible;

    public CrucibleFluidHandler(@Nonnull final BaseCrucibleTile crucibleTile) {
        this(BaseCrucibleTile.MAX_FLUID_AMOUNT);
        this.crucible = crucibleTile;
    }

    private CrucibleFluidHandler(int bucketVolume) {
        super(bucketVolume);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if(crucible != null && !crucible.canAcceptFluidTemperature(resource)) {
            return 0;
        }

        return super.fill(resource, action);
    }
}
