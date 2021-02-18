package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;

public class OakBarrelTile extends AbstractBarrelTile {
    public OakBarrelTile() {
        super(ExNihiloTiles.BARREL_OAK.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
