package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.tileentity.TileEntityType;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class WoodBarrelTile extends AbstractBarrelTile {
    public WoodBarrelTile() {
        this(ExNihiloTiles.BARREL_WOOD.get());
    }

    public WoodBarrelTile(TileEntityType<? extends AbstractBarrelTile> tile) {
        super(tile);
    }

    @Override
    public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
