package novamachina.exnihilosequentia.common.tileentity.barrel;

import novamachina.exnihilosequentia.common.init.ModTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraftforge.fluids.FluidStack;

public class WoodBarrelTile extends AbstractBarrelTile {
    public WoodBarrelTile() {
        super(ModTiles.BARREL_WOOD.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
