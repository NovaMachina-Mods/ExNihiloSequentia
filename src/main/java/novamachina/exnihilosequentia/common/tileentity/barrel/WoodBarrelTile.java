package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraftforge.fluids.FluidStack;

public class WoodBarrelTile extends AbstractBarrelTile {
    public WoodBarrelTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.BARREL_WOOD.get(), pos, state);
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
