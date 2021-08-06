package novamachina.exnihilosequentia.common.fluid;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;

public abstract class SeaWaterFluid extends ForgeFlowingFluid {

    public static final ForgeFlowingFluid.Properties SEA_WATER_PROPS =
            new ForgeFlowingFluid.Properties(
                    ExNihiloFluids.SEA_WATER, ExNihiloFluids.SEA_WATER_FLOW, FluidAttributes
                    .builder(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + ExNihiloConstants.Fluids.SEA_WATER_STILL),
                            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + ExNihiloConstants.Fluids.SEA_WATER_FLOW))
                    .color(0x3F1080FF)
                    .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY))
                    .bucket(ExNihiloItems.SEA_WATER_BUCKET)
                    .block(ExNihiloBlocks.SEA_WATER);

    protected SeaWaterFluid(Properties properties) {
        super(properties);
    }

    public static class Flowing extends SeaWaterFluid {

        public Flowing(Properties properties) {
            super(properties);
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
    }

    public static class Source extends SeaWaterFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
