package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class WoodBarrelTile extends AbstractBarrelTile {
    public WoodBarrelTile(BlockPos pos, BlockState state) {
        this(ExNihiloTiles.BARREL_WOOD.get(), pos, state);
    }

    public WoodBarrelTile(BlockEntityType<? extends AbstractBarrelTile> tile, BlockPos pos, BlockState state) {
        super(tile, pos, state);
    }

    @Override
    public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }
}
