package novamachina.exnihilosequentia.datagen.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.CrookBaseItem;
import novamachina.exnihilosequentia.common.item.HammerBaseItem;
import novamachina.exnihilosequentia.common.item.MeshItem;
import novamachina.exnihilosequentia.common.item.PebbleItem;
import novamachina.exnihilosequentia.common.item.ResourceItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractItemGenerator;

public class ExNihiloItemGenerator extends AbstractItemGenerator {

  private static final String ITEMS_TAG = "item/";
  private static final String ITEM_HANDHELD_TAG = "item/handheld";
  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  public ExNihiloItemGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  private void registerCrook(CrookBaseItem crook) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(crook);
    if (resourceLocation == null) {
      return;
    }
    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_HANDHELD_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, "item/tools/crook/" + resourceLocation.getPath()));
  }

  private void registerCrooks() {
    for (RegistryObject<CrookBaseItem> crook : ExNihiloItems.CROOKS) {
      registerCrook(crook.get());
    }
  }

  private void registerDolls() {
    registerResource(ExNihiloItems.BEE_DOLL.get());
    //    registerResource(ExNihiloItems.BLAZE_DOLL.get());
    registerResource(ExNihiloItems.ENDERMAN_DOLL.get());
    registerResource(ExNihiloItems.GUARDIAN_DOLL.get());
    registerResource(ExNihiloItems.SHULKER_DOLL.get());

    singleTexture(
        ForgeRegistries.ITEMS.getKey(ExNihiloItems.BLAZE_DOLL.get()).getPath(),
        new ResourceLocation("exnihilosequentia:item/overlap_gui"),
        LAYER_0_TAG,
        new ResourceLocation(
            modid,
            ITEMS_TAG + ForgeRegistries.ITEMS.getKey(ExNihiloItems.BLAZE_DOLL.get()).getPath()));
  }

  private void registerHammers() {
    for (RegistryObject<HammerBaseItem> hammer : ExNihiloItems.HAMMERS) {
      registerHammer(hammer.get());
    }
  }

  private void registerHammer(HammerBaseItem hammer) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(hammer);
    if (resourceLocation == null) {
      return;
    }
    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_HANDHELD_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, "item/tools/hammer/" + resourceLocation.getPath()));
  }

  private void registerMesh(MeshItem mesh) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(mesh);
    if (resourceLocation == null) {
      return;
    }
    withExistingParent(
        resourceLocation.getPath(),
        new ResourceLocation(modid, "block/" + resourceLocation.getPath()));
  }

  private void registerMeshes() {
    registerMesh(ExNihiloItems.MESH_STRING.get());
    registerMesh(ExNihiloItems.MESH_FLINT.get());
    registerMesh(ExNihiloItems.MESH_IRON.get());
    registerMesh(ExNihiloItems.MESH_DIAMOND.get());
    registerMesh(ExNihiloItems.MESH_EMERALD.get());
    registerMesh(ExNihiloItems.MESH_NETHERITE.get());
  }

  @Override
  protected void registerModels() {
    for (RegistryObject<ResourceItem> resourceItem : ExNihiloItems.RESOURCE_ITEMS) {
      registerResource(resourceItem.get());
    }
    registerResource(ExNihiloItems.SILKWORM.get());
    registerResource(ExNihiloItems.COOKED_SILKWORM.get());
    registerResource(ExNihiloItems.WITCH_WATER_BUCKET.get());
    registerResource(ExNihiloItems.SEA_WATER_BUCKET.get());
    registerResource(ExNihiloItems.END_CAKE.get());

    registerCrooks();
    registerHammers();
    registerSeeds();
    registerResources();
    registerPebbles();
    registerMeshes();
    registerDolls();
    registerNuggets();
  }

  private void registerNuggets() {
    for (RegistryObject<Item> nugget : ExNihiloItems.NUGGETS) {
      registerNugget(nugget.get());
    }
  }

  private void registerResource(ItemLike item) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(item.asItem());
    if (resourceLocation == null) {
      return;
    }

    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, ITEMS_TAG + resourceLocation.getPath()));
  }

  private void registerNugget(ItemLike item) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(item.asItem());
    if (resourceLocation == null) {
      return;
    }

    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, ITEMS_TAG + "ore/nugget/" + resourceLocation.getPath()));
  }

  private void registerPebbles() {
    for (RegistryObject<PebbleItem> pebble : ExNihiloItems.PEBBLES) {
      registerResource(pebble.get());
    }
  }

  private void registerResources() {
    registerResource(ExNihiloItems.MYCELIUM_SPORE.get());
    registerResource(ExNihiloItems.CRIMSON_NYLIUM_SPORE.get());
    registerResource(ExNihiloItems.WARPED_NYLIUM_SPORE.get());
    registerResource(ExNihiloItems.GRASS_SEED.get());
    registerResource(ExNihiloItems.PORCELAIN_CLAY.get());
    registerResource(ExNihiloItems.CRAFTING_DOLL.get());
    registerResource(ExNihiloItems.TUBE_CORAL_LARVA.get());
    registerResource(ExNihiloItems.BRAIN_CORAL_LARVA.get());
    registerResource(ExNihiloItems.BUBBLE_CORAL_LARVA.get());
    registerResource(ExNihiloItems.FIRE_CORAL_LARVA.get());
    registerResource(ExNihiloItems.HORN_CORAL_LARVA.get());
    registerResource(ExNihiloItems.BEEHIVE_FRAME.get());
  }

  private void registerSeeds() {
    //    registerResource(ExNihiloItems.SEED_OAK.get());
    //    registerResource(ExNihiloItems.SEED_SPRUCE.get());
    //    registerResource(ExNihiloItems.SEED_BIRCH.get());
    //    registerResource(ExNihiloItems.SEED_JUNGLE.get());
    //    registerResource(ExNihiloItems.SEED_ACACIA.get());
    //    registerResource(ExNihiloItems.SEED_DARK_OAK.get());
    //    registerResource(ExNihiloItems.SEED_CACTUS.get());
    //    registerResource(ExNihiloItems.SEED_SUGARCANE.get());
    //    registerResource(ExNihiloItems.SEED_CARROT.get());
    //    registerResource(ExNihiloItems.SEED_POTATO.get());
    //    registerResource(ExNihiloItems.SEED_SWEET_BERRY.get());
    //    registerResource(ExNihiloItems.SEED_KELP.get());
    //    registerResource(ExNihiloItems.SEED_PICKLE.get());
    //    registerResource(ExNihiloItems.SEED_BAMBOO.get());
    //    registerResource(ExNihiloItems.SEED_FERN.get());
    //    registerResource(ExNihiloItems.SEED_LARGE_FERN.get());
  }
}
