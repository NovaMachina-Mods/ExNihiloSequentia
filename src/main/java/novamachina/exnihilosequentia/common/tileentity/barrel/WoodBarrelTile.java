package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;

public class WoodBarrelTile extends AbstractBarrelTile {
    public WoodBarrelTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.BARREL_WOOD.get(), pos, state);
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
