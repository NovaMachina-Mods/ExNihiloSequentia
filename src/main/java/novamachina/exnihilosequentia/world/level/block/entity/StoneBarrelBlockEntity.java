package novamachina.exnihilosequentia.world.level.block.entity;

import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

public class StoneBarrelBlockEntity extends BarrelBlockEntity {

  public StoneBarrelBlockEntity(
      BlockEntityType<? extends BarrelBlockEntity> tile, BlockPos pos, BlockState state) {
    super(tile, pos, state);
  }

  @Override
  public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
    return true;
  }
}
