package novamachina.exnihilosequentia.common.fluid;

import javax.annotation.Nonnull;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;

public abstract class WitchWaterFluid extends ForgeFlowingFluid {

  @Nonnull
  public static final ForgeFlowingFluid.Properties WITCH_WATER_PROPS =
      new ForgeFlowingFluid.Properties(
              () -> EXNFluids.WITCH_WATER.getFluidType(),
              () -> EXNFluids.WITCH_WATER.getStillFluid(),
              () -> EXNFluids.WITCH_WATER.getFlowingFluid())
          .bucket(() -> EXNFluids.WITCH_WATER.getBucket().asItem())
          .block(() -> EXNFluids.WITCH_WATER.getBlock().block());

  protected WitchWaterFluid(@Nonnull final Properties properties) {
    super(properties);
  }

  public static class Flowing extends WitchWaterFluid {

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

  public static class Source extends WitchWaterFluid {

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
