package novamachina.exnihilosequentia.world.level.material;

import javax.annotation.Nonnull;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class SeaWaterFluid extends BaseFlowingFluid {

  @Nonnull
  public static final BaseFlowingFluid.Properties SEA_WATER_PROPS =
      new BaseFlowingFluid.Properties(
              () -> EXNFluids.SEA_WATER.getFluidType(),
              () -> EXNFluids.SEA_WATER.getStillFluid(),
              () -> EXNFluids.SEA_WATER.getFlowingFluid())
          .bucket(() -> EXNFluids.SEA_WATER.getBucket().asItem())
          .block(() -> EXNFluids.SEA_WATER.getBlock().block());

  protected SeaWaterFluid(@Nonnull final Properties properties) {
    super(properties);
  }

  public static class Flowing extends SeaWaterFluid {

    public Flowing(@Nonnull final Properties properties) {
      super(properties);
      registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
    }

    @Override
    public int getAmount(@Nonnull final FluidState fluidState) {
      return fluidState.getValue(LEVEL);
    }

    @Override
    public boolean isSource(@Nonnull final FluidState state) {
      return false;
    }

    @Override
    protected void createFluidStateDefinition(
        @Nonnull final StateDefinition.Builder<Fluid, FluidState> builder) {
      super.createFluidStateDefinition(builder);
      builder.add(LEVEL);
    }
  }

  public static class Source extends SeaWaterFluid {

    public Source(@Nonnull final Properties properties) {
      super(properties);
    }

    @Override
    public int getAmount(@Nonnull final FluidState fluidState) {
      return 8;
    }

    @Override
    public boolean isSource(@Nonnull final FluidState state) {
      return true;
    }
  }
}
