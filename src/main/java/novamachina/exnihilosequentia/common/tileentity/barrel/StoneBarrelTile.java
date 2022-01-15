package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.tileentity.TileEntityType;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class StoneBarrelTile extends AbstractBarrelTile{
    public StoneBarrelTile(TileEntityType<? extends AbstractBarrelTile> tile) {
        super(tile);
    }

    public StoneBarrelTile() {
        this(ExNihiloTiles.BARREL_STONE.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
        return true;
    }
}
