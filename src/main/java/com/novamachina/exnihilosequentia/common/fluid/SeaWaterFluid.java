package com.novamachina.exnihilosequentia.common.fluid;

import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SeaWaterFluid extends ForgeFlowingFluid {

    public static final ForgeFlowingFluid.Properties SEA_WATER_PROPS =
        new ForgeFlowingFluid.Properties(
            ModFluids.SEA_WATER, ModFluids.SEA_WATER_FLOW, FluidAttributes
            .builder(new ResourceLocation(Constants.ModInfo.MOD_ID, "block/sea_water"),
                new ResourceLocation(Constants.ModInfo.MOD_ID, "block/sea_water_flow"))
            .color(0x3F1080FF))
            .bucket(ModItems.SEA_WATER_BUCKET)
            .block(ModBlocks.SEA_WATER);

    public SeaWaterFluid(Properties properties) {
        super(properties);
    }

    public static class Source extends SeaWaterFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }

        @Override
        public int getLevel(IFluidState fluidState) {
            return 8;
        }
    }

    public static class Flowing extends SeaWaterFluid {

        public Flowing(Properties properties) {
            super(properties);
            setDefaultState(getStateContainer().getBaseState().with(LEVEL_1_8, 7));
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }

        @Override
        public int getLevel(IFluidState fluidState) {
            return fluidState.get(LEVEL_1_8);
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }
    }
}
