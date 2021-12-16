package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExNihiloFluidTagsGenerator extends FluidTagsProvider {
    public ExNihiloFluidTagsGenerator(@Nonnull final DataGenerator generator,
                                      @Nullable final ExistingFileHelper existingFileHelper) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(FluidTags.WATER).add(ExNihiloFluids.SEA_WATER.get());
        tag(FluidTags.WATER).add(ExNihiloFluids.SEA_WATER_FLOW.get());
    }
}
