package novamachina.exnihilosequentia.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractItemGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.CrookBaseItem;
import novamachina.exnihilosequentia.common.item.HammerBaseItem;
import novamachina.exnihilosequentia.common.item.MeshItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloItemGenerator extends AbstractItemGenerator {

  private static final String ITEMS_TAG = "items/";
  private static final String ITEM_HANDHELD_TAG = "item/handheld";
  private static final String ITEM_GENERATED_TAG = "item/generated";
  private static final String LAYER_0_TAG = "layer0";

  public ExNihiloItemGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  private void registerCrook(CrookBaseItem crook) {
    @Nullable final ResourceLocation resourceLocation = crook.getRegistryName();
    if (resourceLocation == null) {
      return;
    }
    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_HANDHELD_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, "items/tools/crook/" + resourceLocation.getPath()));
  }

  private void registerCrooks() {
    registerCrook(ExNihiloItems.CROOK_WOOD.get());
    registerCrook(ExNihiloItems.CROOK_STONE.get());
    registerCrook(ExNihiloItems.CROOK_ANDESITE.get());
    registerCrook(ExNihiloItems.CROOK_GRANITE.get());
    registerCrook(ExNihiloItems.CROOK_DIORITE.get());
    registerCrook(ExNihiloItems.CROOK_GOLD.get());
    registerCrook(ExNihiloItems.CROOK_IRON.get());
    registerCrook(ExNihiloItems.CROOK_DIAMOND.get());
    registerCrook(ExNihiloItems.CROOK_BONE.get());
  }

  private void registerDolls() {
    registerResource(ExNihiloItems.BEE_DOLL.get());
    registerResource(ExNihiloItems.BLAZE_DOLL.get());
    registerResource(ExNihiloItems.ENDERMAN_DOLL.get());
    registerResource(ExNihiloItems.GUARDIAN_DOLL.get());
    registerResource(ExNihiloItems.SHULKER_DOLL.get());
  }

  private void registerHammers() {
    //    registerHammer(ExNihiloItems.HAMMER_ANDESITE.get());
    //    registerHammer(ExNihiloItems.HAMMER_BASALT.get());
    //    registerHammer(ExNihiloItems.HAMMER_BLACKSTONE.get());
    //    registerHammer(ExNihiloItems.HAMMER_CALCITE.get());
    //    registerHammer(ExNihiloItems.HAMMER_COPPER.get());
    //    registerHammer(ExNihiloItems.HAMMER_CRIMSON_FUNGUS.get());
    //    registerHammer(ExNihiloItems.HAMMER_DEEPSLATE.get());
    registerHammer(ExNihiloItems.HAMMER_DIAMOND.get());
    //    registerHammer(ExNihiloItems.HAMMER_DIORITE.get());
    //    registerHammer(ExNihiloItems.HAMMER_DRIPSTONE.get());
    registerHammer(ExNihiloItems.HAMMER_GOLD.get());
    //    registerHammer(ExNihiloItems.HAMMER_GRANITE.get());
    registerHammer(ExNihiloItems.HAMMER_IRON.get());
    //    registerHammer(ExNihiloItems.HAMMER_NETHER_BRICK.get());
    registerHammer(ExNihiloItems.HAMMER_NETHERITE.get());
    //    registerHammer(ExNihiloItems.HAMMER_PRISMARINE.get());
    //    registerHammer(ExNihiloItems.HAMMER_RED_NETHER_BRICK.get());
    registerHammer(ExNihiloItems.HAMMER_STONE.get());
    //    registerHammer(ExNihiloItems.HAMMER_TERRACOTTA.get());
    //    registerHammer(ExNihiloItems.HAMMER_TUFF.get());
    //    registerHammer(ExNihiloItems.HAMMER_WARPED_FUNGUS.get());
    registerHammer(ExNihiloItems.HAMMER_WOOD.get());
  }

  private void registerHammer(HammerBaseItem hammer) {
    @Nullable final ResourceLocation resourceLocation = hammer.getRegistryName();
    if (resourceLocation == null) {
      return;
    }
    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_HANDHELD_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, "items/tools/hammer/" + resourceLocation.getPath()));
  }

  private void registerMesh(MeshItem mesh) {
    @Nullable final ResourceLocation resourceLocation = mesh.getRegistryName();
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
    registerResource(ExNihiloItems.SILKWORM.get());
    registerResource(ExNihiloItems.COOKED_SILKWORM.get());
    registerResource(ExNihiloItems.WITCH_WATER_BUCKET.get());
    registerResource(ExNihiloItems.SEA_WATER_BUCKET.get());
    registerResource(ExNihiloItems.NUGGET_COPPER.get());

    registerCrooks();
    registerHammers();
    registerSeeds();
    registerResources();
    registerPebbles();
    registerMeshes();
    registerDolls();
  }

  private void registerResource(ItemLike item) {
    @Nullable final ResourceLocation resourceLocation = item.asItem().getRegistryName();
    if (resourceLocation == null) {
      return;
    }

    singleTexture(
        resourceLocation.getPath(),
        new ResourceLocation(ITEM_GENERATED_TAG),
        LAYER_0_TAG,
        new ResourceLocation(modid, ITEMS_TAG + resourceLocation.getPath()));
  }

  private void registerPebbles() {
    registerResource(ExNihiloItems.PEBBLE_ANDESITE.get());
    registerResource(ExNihiloItems.PEBBLE_BASALT.get());
    registerResource(ExNihiloItems.PEBBLE_BLACKSTONE.get());
    //    registerResource(ExNihiloItems.PEBBLE_DEEPSLATE.get());
    registerResource(ExNihiloItems.PEBBLE_DIORITE.get());
    //    registerResource(ExNihiloItems.PEBBLE_END_STONE.get());
    registerResource(ExNihiloItems.PEBBLE_GRANITE.get());
    //    registerResource(ExNihiloItems.PEBBLE_NETHERRACK.get());
    registerResource(ExNihiloItems.PEBBLE_STONE.get());
    //    registerResource(ExNihiloItems.PEBBLE_TUFF.get());
  }

  private void registerResources() {
    registerResource(ExNihiloItems.MYCELIUM_SPORE.get());
    //    registerResource(ExNihiloItems.CRIMSON_NYLIUM_SPORE.get());
    //    registerResource(ExNihiloItems.WARPED_NYLIUM_SPORE.get());
    registerResource(ExNihiloItems.GRASS_SEED.get());
    registerResource(ExNihiloItems.PORCELAIN_CLAY.get());
    registerResource(ExNihiloItems.CRAFTING_DOLL.get());
    registerResource(ExNihiloItems.BLUE_CORAL_SEED.get());
    registerResource(ExNihiloItems.PINK_CORAL_SEED.get());
    registerResource(ExNihiloItems.PURPLE_CORAL_SEED.get());
    registerResource(ExNihiloItems.RED_CORAL_SEED.get());
    registerResource(ExNihiloItems.YELLOW_CORAL_SEED.get());
    registerResource(ExNihiloItems.BEEHIVE_FRAME.get());
  }

  private void registerSeeds() {
    registerResource(ExNihiloItems.SEED_OAK.get());
    registerResource(ExNihiloItems.SEED_SPRUCE.get());
    registerResource(ExNihiloItems.SEED_BIRCH.get());
    registerResource(ExNihiloItems.SEED_JUNGLE.get());
    registerResource(ExNihiloItems.SEED_ACACIA.get());
    registerResource(ExNihiloItems.SEED_DARK_OAK.get());
    registerResource(ExNihiloItems.SEED_CACTUS.get());
    registerResource(ExNihiloItems.SEED_SUGARCANE.get());
    registerResource(ExNihiloItems.SEED_CARROT.get());
    registerResource(ExNihiloItems.SEED_POTATO.get());
    registerResource(ExNihiloItems.SEED_SWEET_BERRY.get());
    registerResource(ExNihiloItems.SEED_KELP.get());
    registerResource(ExNihiloItems.SEED_PICKLE.get());
    registerResource(ExNihiloItems.SEED_BAMBOO.get());
    registerResource(ExNihiloItems.SEED_FERN.get());
    registerResource(ExNihiloItems.SEED_LARGE_FERN.get());
  }
}
