package novamachina.exnihilosequentia.common.fluid;

import novamachina.exnihilosequentia.common.init.ModBlocks;
import novamachina.exnihilosequentia.common.init.ModFluids;
import novamachina.exnihilosequentia.common.init.ModItems;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SeaWaterFluid extends ForgeFlowingFluid {

    public static final ForgeFlowingFluid.Properties SEA_WATER_PROPS =
        new ForgeFlowingFluid.Properties(
            ModFluids.SEA_WATER, ModFluids.SEA_WATER_FLOW, FluidAttributes
            .builder(new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "block/sea_water"),
                new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "block/sea_water_flow"))
            .color(0x3F1080FF))
            .bucket(ModItems.SEA_WATER_BUCKET)
            .block(ModBlocks.SEA_WATER);

    protected SeaWaterFluid(Properties properties) {
        super(properties);
    }

    public static class Source extends SeaWaterFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }
    }

    public static class Flowing extends SeaWaterFluid {

        public Flowing(Properties properties) {
            super(properties);
            setDefaultState(getStateContainer().getBaseState().with(LEVEL_1_8, 7));
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL_1_8);
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }
    }
}
