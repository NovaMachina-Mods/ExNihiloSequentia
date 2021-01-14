package novamachina.exnihilosequentia.common.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public abstract class WitchWaterFluid extends ForgeFlowingFluid {

    public static final ForgeFlowingFluid.Properties WITCH_WATER_PROPS =
            new ForgeFlowingFluid.Properties(
                    ExNihiloFluids.WITCH_WATER, ExNihiloFluids.WITCH_WATER_FLOW, FluidAttributes
                    .builder(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/witchwater"),
                            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/witchwater_flow"))
                    .color(0x3F1080FF))
                    .bucket(ExNihiloItems.WITCH_WATER_BUCKET)
                    .block(ExNihiloBlocks.WITCH_WATER);

    protected WitchWaterFluid(Properties properties) {
        super(properties);
    }

    public static class Flowing extends WitchWaterFluid {

        public Flowing(Properties properties) {
            super(properties);
            setDefaultState(getStateContainer().getBaseState().with(LEVEL_1_8, 7));
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL_1_8);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }
    }

    public static class Source extends WitchWaterFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
