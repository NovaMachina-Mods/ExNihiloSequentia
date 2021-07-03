package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractFluidTagGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

public class ExNihiloFluidTagsGenerator extends AbstractFluidTagGenerator {
    public ExNihiloFluidTagsGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(FluidTags.WATER).add(ExNihiloFluids.SEA_WATER.get(), ExNihiloFluids.SEA_WATER_FLOW.get());
    }
}
