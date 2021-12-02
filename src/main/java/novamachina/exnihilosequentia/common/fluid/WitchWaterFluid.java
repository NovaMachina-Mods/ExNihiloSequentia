package novamachina.exnihilosequentia.common.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;

import javax.annotation.Nonnull;

public abstract class WitchWaterFluid extends ForgeFlowingFluid {

    public static final ForgeFlowingFluid.Properties WITCH_WATER_PROPS =
            new ForgeFlowingFluid.Properties(
                    ExNihiloFluids.WITCH_WATER, ExNihiloFluids.WITCH_WATER_FLOW, FluidAttributes
                    .builder(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + ExNihiloConstants.Fluids.WITCH_WATER_STILL),
                            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + ExNihiloConstants.Fluids.WITCH_WATER_FLOW))
                    .color(0x3F1080FF)
                    .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY))
                    .bucket(ExNihiloItems.WITCH_WATER_BUCKET)
                    .block(ExNihiloBlocks.WITCH_WATER);

    protected WitchWaterFluid(Properties properties) {
        super(properties);
    }

    public static class Flowing extends WitchWaterFluid {

        public Flowing(Properties properties) {
            super(properties);
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        @Override
        public boolean isSource(@Nonnull FluidState state) {
            return false;
        }

        @Override
        protected void createFluidStateDefinition(@Nonnull StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
    }

    public static class Source extends WitchWaterFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getAmount(@Nonnull FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(@Nonnull FluidState state) {
            return true;
        }
    }
}
