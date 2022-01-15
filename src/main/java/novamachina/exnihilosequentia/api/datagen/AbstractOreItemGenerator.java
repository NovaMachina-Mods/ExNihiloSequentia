package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractOreItemGenerator extends ItemModelProvider {
    protected AbstractOreItemGenerator(@Nonnull final DataGenerator generator,
                                       @Nonnull final ExistingFileHelper existingFileHelper) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    protected void registerChunk(@Nonnull final EnumOre ore) {
        @Nullable final RegistryObject<OreItem> chunkRegistryObject = ore.getChunkItem();
        if (chunkRegistryObject == null)
            return;
        @Nullable final ResourceLocation chunkResourceLocation = chunkRegistryObject.get().getRegistryName();
        if (chunkResourceLocation == null)
            return;
        withExistingParent(chunkResourceLocation.getPath(), exnihiloLoc("item/chunk_ore"));
    }

    protected void registerIngot(@Nonnull final EnumOre ore) {
        @Nullable final ResourceLocation resourceLocation;
        @Nullable final Item ingotItem = ore.getIngotItem();
        if (ingotItem != null)
            resourceLocation = ingotItem.getRegistryName();
        else {
            @Nullable final RegistryObject<OreItem> ingotRegistryObject = ore.getIngotRegistryItem();
            if (ingotRegistryObject != null)
                resourceLocation = ingotRegistryObject.get().getRegistryName();
            else
                return;
        }
        if (resourceLocation == null)
            return;
        withExistingParent(resourceLocation.getPath(), exnihiloLoc("item/ingot_ore"));
    }

    protected void registerPiece(@Nonnull final EnumOre ore) {
        @Nullable final RegistryObject<OreItem> pieceRegistryObject = ore.getPieceItem();
        if (pieceRegistryObject == null)
            return;
        @Nullable final ResourceLocation pieceResourceLocation = pieceRegistryObject.get().getRegistryName();
        if (pieceResourceLocation == null)
            return;
        withExistingParent(pieceResourceLocation.getPath(), exnihiloLoc("item/piece_ore"));
    }

    @Nonnull
    private ResourceLocation exnihiloLoc(@Nonnull final String path) {
        return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
    }
}
