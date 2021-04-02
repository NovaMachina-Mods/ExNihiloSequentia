package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;

public class GlassBarrelTile extends AbstractBarrelTile{
    public GlassBarrelTile() {
        super(ExNihiloTiles.BARRELS_GLASS.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return true;
    }
}
