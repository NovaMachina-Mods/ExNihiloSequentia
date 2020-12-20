package novamachina.exnihilosequentia.common.tileentity.barrel;

import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import net.minecraftforge.fluids.FluidStack;

public class StoneBarrelTile extends AbstractBarrelTile{
    public StoneBarrelTile() {
        super(ExNihiloTiles.BARREL_STONE.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return true;
    }
}
