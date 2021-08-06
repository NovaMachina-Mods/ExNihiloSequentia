package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractOreItemGenerator;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class ExNihiloOreItemGenerator extends AbstractOreItemGenerator {
    public ExNihiloOreItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (EnumOre ore : EnumOre.values()) {
            registerPiece(ore);
            if (!ore.isVanilla()) {
                registerRawOre(ore);
                if (ore.shouldGenerateIngot()) {
                    registerIngot(ore);
                }
            }
        }
    }
}
