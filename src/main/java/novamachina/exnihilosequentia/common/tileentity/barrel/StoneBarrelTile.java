package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;

public class StoneBarrelTile extends AbstractBarrelTile{
    public StoneBarrelTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.BARREL_STONE.get(), pos, state);
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return true;
    }
}
