package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class StoneBarrelTile extends AbstractBarrelTile{
    public StoneBarrelTile(BlockEntityType<? extends AbstractBarrelTile> tile, BlockPos pos, BlockState state) {
        super(tile, pos, state);
    }

    public StoneBarrelTile(BlockPos pos, BlockState state) {
        this(ExNihiloTiles.BARREL_STONE.get(), pos, state);
    }

    @Override
    public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
        return true;
    }
}
