package novamachina.exnihilosequentia.common.blockentity.barrel;

import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;

public class StoneBarrelEntity extends AbstractBarrelEntity {

  public StoneBarrelEntity(BlockEntityType<? extends AbstractBarrelEntity> tile, BlockPos pos,
      BlockState state) {
    super(tile, pos, state);
  }

  public StoneBarrelEntity(BlockPos pos, BlockState state) {
    this(ExNihiloBlockEntities.STONE_BARREL_ENTITY.get(), pos, state);
  }

  @Override
  public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
    return true;
  }
}
