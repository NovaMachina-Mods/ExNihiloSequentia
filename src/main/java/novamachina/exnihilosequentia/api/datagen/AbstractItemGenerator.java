package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public abstract class AbstractItemGenerator extends ItemModelProvider {
    protected AbstractItemGenerator(@Nonnull final DataGenerator generator, @Nonnull final String modId,
                                    @Nonnull final ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

}
