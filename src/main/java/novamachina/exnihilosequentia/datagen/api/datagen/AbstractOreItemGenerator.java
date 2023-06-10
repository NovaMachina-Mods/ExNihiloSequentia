package novamachina.exnihilosequentia.datagen.api.datagen;

import com.mojang.datafixers.util.Either;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.item.OreItem;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.novacore.world.item.ItemDefinition;

public abstract class AbstractOreItemGenerator extends ItemModelProvider {

  private static final String ITEMS_TAG = "item/ore";
  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  protected AbstractOreItemGenerator(
      @Nonnull final DataGenerator generator,
      String modid,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, modid, existingFileHelper);
  }

  protected void registerRaw(@Nonnull final Ore ore) {
    Either<ItemDefinition<OreItem>, Item> rawEither = ore.getRawOreItem();
    if (rawEither.left().isPresent()) {
      @Nullable final Item rawRegistryObject = rawEither.left().get().asItem();
      @Nullable
      final ResourceLocation rawResourceLocation = ForgeRegistries.ITEMS.getKey(rawRegistryObject);
      if (rawResourceLocation == null) {
        return;
      }
      singleTexture(
          rawResourceLocation.getPath(),
          new ResourceLocation(ITEM_GENERATED_TAG),
          LAYER_0_TAG,
          new ResourceLocation(modid, ITEMS_TAG + "/raw/" + rawResourceLocation.getPath()));
    }
  }

  protected void registerIngot(@Nonnull final Ore ore) {
    Either<ItemDefinition<OreItem>, Item> ingotEither = ore.getIngotItem();
    if (ingotEither.left().isPresent()) {
      @Nullable final Item ingotItem = ingotEither.left().get().asItem();
      final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(ingotItem);
      singleTexture(
          resourceLocation.getPath(),
          new ResourceLocation(ITEM_GENERATED_TAG),
          LAYER_0_TAG,
          new ResourceLocation(modid, ITEMS_TAG + "/ingot/" + resourceLocation.getPath()));
    }
  }

  protected void registerPiece(@Nonnull final Ore ore) {
    @Nullable final Item pieceRegistryObject = ore.getPieceItem();
    if (pieceRegistryObject == null) {
      return;
    }
    @Nullable
    final ResourceLocation pieceResourceLocation =
        ForgeRegistries.ITEMS.getKey(pieceRegistryObject);
    if (pieceResourceLocation == null) {
      return;
    }
    singleTexture(
        pieceResourceLocation.getPath(),
        new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, ITEMS_TAG + "/piece/" + pieceResourceLocation.getPath()));
  }

  protected void registerNugget(@Nonnull final Ore ore) {
    Either<ItemDefinition<OreItem>, Item> nuggetEither = ore.getNuggetItem();
    if (nuggetEither.left().isPresent()) {
      @Nullable final Item nuggetItem = nuggetEither.left().get().asItem();
      final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(nuggetItem);
      singleTexture(
          resourceLocation.getPath(),
          new ResourceLocation(ITEM_GENERATED_TAG),
          LAYER_0_TAG,
          new ResourceLocation(modid, ITEMS_TAG + "/nugget/" + resourceLocation.getPath()));
    }
  }

  @Nonnull
  private ResourceLocation exnihiloLoc(@Nonnull final String path) {
    return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
  }
}
