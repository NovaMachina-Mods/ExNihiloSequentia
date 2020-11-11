package novamachina.exnihilosequentia.common.tileentity.barrel;

import novamachina.exnihilosequentia.common.init.ModTiles;
import net.minecraftforge.fluids.FluidStack;

public class StoneBarrelTile extends AbstractBarrelTile{
    public StoneBarrelTile() {
        super(ModTiles.BARREL_STONE.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return true;
    }
}
