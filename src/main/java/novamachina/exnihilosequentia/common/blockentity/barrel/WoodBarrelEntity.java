package novamachina.exnihilosequentia.common.blockentity.barrel;

import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;
import novamachina.exnihilosequentia.common.utility.Config;

public class WoodBarrelEntity extends AbstractBarrelEntity {

  public WoodBarrelEntity(BlockPos pos, BlockState state) {
    this(ExNihiloBlockEntities.WOODEN_BARREL_ENTITY.get(), pos, state);
  }

  public WoodBarrelEntity(BlockEntityType<? extends AbstractBarrelEntity> tile, BlockPos pos,
      BlockState state) {
    super(tile, pos, state);
  }

  @Override
  public boolean canAcceptFluidTemperature(@Nonnull final FluidStack resource) {
    return resource.getFluid().getFluidType().getTemperature() <= Config.getWoodBarrelMaxTemp();
  }
}
