package com.novamachina.exnihilosequentia.common.fluid;

import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.utility.Constants.ModInfo;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class WitchWaterFluid extends ForgeFlowingFluid {

    public static final ForgeFlowingFluid.Properties WITCH_WATER_PROPS =
        new ForgeFlowingFluid.Properties(
            ModFluids.WITCH_WATER_STILL, ModFluids.WITCH_WATER_FLOW, FluidAttributes
            .builder(new ResourceLocation(ModInfo.MOD_ID, "block/witchwater_still"),
                new ResourceLocation(ModInfo.MOD_ID, "block/witchwater_flow"))
            .color(0x3F1080FF))
            .bucket(ModItems.WITCH_WATER_BUCKET)
            .block(ModBlocks.WITCH_WATER);

    public WitchWaterFluid(Properties properties) {
        super(properties);
    }

    public static class Source extends WitchWaterFluid {

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

    public static class Flowing extends WitchWaterFluid {

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
        protected void fillStateContainer(Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }
    }
}
