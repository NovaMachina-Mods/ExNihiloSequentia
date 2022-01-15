package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractOreItemGenerator;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

import javax.annotation.Nonnull;

public class ExNihiloOreItemGenerator extends AbstractOreItemGenerator {
    public ExNihiloOreItemGenerator(@Nonnull final DataGenerator generator,
                                    @Nonnull final ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (@Nonnull final EnumOre ore : EnumOre.values()) {
            registerChunk(ore);
            registerPiece(ore);
            if (ore.shouldGenerateIngot()) {
                registerIngot(ore);
            }
        }
    }
}
