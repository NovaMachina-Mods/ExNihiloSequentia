package novamachina.exnihilosequentia.common.tileentity.barrel;

import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class WoodBarrelTile extends AbstractBarrelTile {
    public WoodBarrelTile() {
        super(ExNihiloTiles.BARREL_WOOD.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
