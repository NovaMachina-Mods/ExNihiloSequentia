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

public abstract class WitchWaterFluid extends ForgeFlowingFluid {

  @Nonnull
  public static final Properties WITCH_WATER_PROPS =
      new Properties(ExNihiloFluidTypes.WITCH_WATER_FLUID_TYPE,
          ExNihiloFluids.WITCH_WATER, ExNihiloFluids.WITCH_WATER_FLOW)
          .bucket(ExNihiloItems.WITCH_WATER_BUCKET)
          .block(ExNihiloBlocks.WITCH_WATER);

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
