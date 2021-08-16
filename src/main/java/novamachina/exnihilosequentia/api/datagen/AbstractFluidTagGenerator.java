package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public abstract class AbstractFluidTagGenerator extends FluidTagsProvider {
    public AbstractFluidTagGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Fluid Tags: " + modId;
    }
}
