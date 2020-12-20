package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class AbstractItemGenerator extends ItemModelProvider {
    public AbstractItemGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

}
