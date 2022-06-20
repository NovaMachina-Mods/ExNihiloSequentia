package novamachina.exnihilosequentia.common.fluid;

import javax.annotation.Nonnull;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluidTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;

public abstract class SeaWaterFluid extends ForgeFlowingFluid {

  @Nonnull
  public static final ForgeFlowingFluid.Properties SEA_WATER_PROPS =
      new ForgeFlowingFluid.Properties(ExNihiloFluidTypes.SEA_WATER_FLUID_TYPE,
          ExNihiloFluids.SEA_WATER, ExNihiloFluids.SEA_WATER_FLOW)
          .bucket(ExNihiloItems.SEA_WATER_BUCKET)
          .block(ExNihiloBlocks.SEA_WATER);

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
