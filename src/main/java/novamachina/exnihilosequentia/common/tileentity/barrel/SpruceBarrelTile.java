package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;

public class SpruceBarrelTile extends AbstractBarrelTile {
    public SpruceBarrelTile() {
        super(ExNihiloTiles.BARREL_SPRUCE.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
