package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.tileentity.TileEntityType;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import net.minecraftforge.fluids.FluidStack;

public class StoneBarrelTile extends AbstractBarrelTile{
    public StoneBarrelTile(TileEntityType<? extends AbstractBarrelTile> tile) {
        super(tile);
    }

    public StoneBarrelTile() {
        this(ExNihiloTiles.BARREL_STONE.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return true;
    }
}
