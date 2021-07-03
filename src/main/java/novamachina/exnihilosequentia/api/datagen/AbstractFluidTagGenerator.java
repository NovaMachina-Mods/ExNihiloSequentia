package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class AbstractFluidTagGenerator extends FluidTagsProvider {
    public AbstractFluidTagGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Fluid Tags: " + modId;
    }
}
