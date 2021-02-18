package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;

public class JungleBarrelTile extends AbstractBarrelTile {
    public JungleBarrelTile() {
        super(ExNihiloTiles.BARREL_JUNGLE.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
