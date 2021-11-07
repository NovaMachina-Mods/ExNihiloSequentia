package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public abstract class AbstractOreItemGenerator extends ItemModelProvider {
    protected AbstractOreItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    protected void registerChunk(EnumOre ore) {
        withExistingParent(ore.getChunkItem().get().getRegistryName().getPath(), exnihiloLoc("item/chunk_ore"));
    }

    protected void registerIngot(EnumOre ore) {
        withExistingParent(ore.getIngotItem() != null ? ore.getIngotItem().getRegistryName().getPath() : ore.getIngotRegistryItem().get().getRegistryName().getPath(), exnihiloLoc("item/ingot_ore"));
    }

    protected void registerPiece(EnumOre ore) {
        withExistingParent(ore.getPieceItem().get().getRegistryName().getPath(), exnihiloLoc("item/piece_ore"));
    }

    private ResourceLocation exnihiloLoc(String path) {
        return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
    }
}
