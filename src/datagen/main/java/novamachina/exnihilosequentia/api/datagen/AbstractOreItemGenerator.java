package novamachina.exnihilosequentia.api.datagen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public abstract class AbstractOreItemGenerator extends ItemModelProvider {

  private static final String ITEMS_TAG = "items/";
  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  protected AbstractOreItemGenerator(@Nonnull final DataGenerator generator, String modid,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, modid, existingFileHelper);
  }

  protected void registerRaw(@Nonnull final Ore ore) {
    @Nullable final Item rawRegistryObject = ore.getRawOreItem();
    if (rawRegistryObject == null) {
      return;
    }
    @Nullable final ResourceLocation rawResourceLocation = rawRegistryObject.getRegistryName();
    if (rawResourceLocation == null) {
      return;
    }
    singleTexture(rawResourceLocation.getPath(), new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + rawResourceLocation.getPath()));
  }

  protected void registerIngot(@Nonnull final Ore ore) {
    @Nullable final ResourceLocation resourceLocation;
    @Nullable final Item ingotItem = ore.getIngotItem();
    if (ingotItem != null) {
      resourceLocation = ingotItem.getRegistryName();
    } else {
      @Nullable final Item ingotRegistryObject = ore.getIngotItem();
      if (ingotRegistryObject != null) {
        resourceLocation = ingotRegistryObject.getRegistryName();
      } else {
        return;
      }
    }
    if (resourceLocation == null) {
      return;
    }
    singleTexture(resourceLocation.getPath(), new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + resourceLocation.getPath()));
  }

  protected void registerPiece(@Nonnull final Ore ore) {
    @Nullable final Item pieceRegistryObject = ore.getPieceItem();
    if (pieceRegistryObject == null) {
      return;
    }
    @Nullable final ResourceLocation pieceResourceLocation = pieceRegistryObject.getRegistryName();
    if (pieceResourceLocation == null) {
      return;
    }
    singleTexture(pieceResourceLocation.getPath(), new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + pieceResourceLocation.getPath()));
  }

  @Nonnull
  private ResourceLocation exnihiloLoc(@Nonnull final String path) {
    return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
  }
}
