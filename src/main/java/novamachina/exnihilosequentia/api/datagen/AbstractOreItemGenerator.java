package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import java.util.Objects;

public abstract class AbstractOreItemGenerator extends ItemModelProvider {
    protected AbstractOreItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    protected void createRawOre(EnumOre ore) {
        withExistingParent(Objects.requireNonNull(ore.getRawOreItem().get().getRegistryName())
                .getPath(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "item/raw_ore"));
    }

    protected void createIngot(EnumOre ore) {
        withExistingParent(ore.getIngotItem() != null ? Objects.requireNonNull(ore.getIngotItem().getRegistryName()).getPath() :
            Objects.requireNonNull(ore.getIngotRegistryItem().get().getRegistryName()).getPath(),
                new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "item/ingot_ore"));
    }

    protected void createPiece(EnumOre ore) {
        withExistingParent(Objects.requireNonNull(ore.getPieceItem().get().getRegistryName())
                .getPath(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "item/piece_ore"));
    }
}
