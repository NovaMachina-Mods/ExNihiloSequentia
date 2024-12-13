package novamachina.exnihilosequentia.data.recipes.providers;

import com.mojang.datafixers.util.Either;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.Ore;
import novamachina.exnihilosequentia.world.item.OreItem;
import novamachina.exnihilosequentia.world.item.PebbleItem;
import novamachina.exnihilosequentia.world.level.block.BarrelBlock;
import novamachina.exnihilosequentia.world.level.block.CrucibleBlock;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import novamachina.novacore.data.recipes.ISubRecipeProvider;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public class CraftingRecipes implements ISubRecipeProvider {
  public static final String PEBBLE_CONDITION = "has_pebble";
  private static final String MATERIAL_CONDITION = "has_material";
  private static final String CHUNK_CONDITION = "has_chunk";
  private static final String DOLL_CONDITION = "has_doll";
  private static final String PORCELAIN_CLAY_CONDITION = "has_porcelain_clay";

  @Override
  public void addRecipes(HolderGetter.Provider provider, RecipeOutput consumer) {
    HolderGetter<Item> holderGetter = provider.lookupOrThrow(Registries.ITEM);
    addCrooks(holderGetter, consumer);
    addPebbleBlocks(holderGetter, consumer);
    addBarrels(holderGetter, consumer);
    addCrucibles(holderGetter, consumer);
    addSieves(holderGetter, consumer);
    addOres(holderGetter, consumer);
    addHammers(holderGetter, consumer);
    addDolls(holderGetter, consumer);
    addMeshes(holderGetter, consumer);
    addMisc(holderGetter, consumer);
  }

  private void addMisc(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ResourceLocation beehive = BuiltInRegistries.BLOCK.getKey(Blocks.BEEHIVE);
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, Blocks.BEEHIVE)
        .pattern("xxx")
        .pattern("fff")
        .pattern("xxx")
        .define('x', ItemTags.PLANKS)
        .define('f', EXNItems.BEEHIVE_FRAME.asItem())
        .unlockedBy(
            "has_frame",
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.BEEHIVE_FRAME.asItem()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(beehive));

    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNItems.BEEHIVE_FRAME.asItem())
        .pattern("xxx")
        .pattern("xfx")
        .pattern("xxx")
        .define('x', Tags.Items.RODS_WOODEN)
        .define('f', Tags.Items.STRINGS)
        .unlockedBy(
            "has_stick",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.RODS_WOODEN).build()))
        .unlockedBy(
            "has_string",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.STRINGS).build()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.BEEHIVE_FRAME.getId()));

    createCookingRecipe(
        consumer,
        EXNItems.SILKWORM.asItem(),
        EXNItems.COOKED_SILKWORM.asItem(),
        0.1F,
        600,
        0.1F,
        100,
        "has_silkworm",
        EXNItems.COOKED_SILKWORM.getId());
    createSmeltingRecipe(
        consumer,
        EXNItems.SILKWORM.asItem(),
        EXNItems.COOKED_SILKWORM.asItem(),
        0.1F,
        200,
        0.1F,
        100,
        "has_silkworm",
        EXNItems.COOKED_SILKWORM.getId());

    createSmeltingRecipe(
        consumer,
        EXNBlocks.UNFIRED_CRUCIBLE.asItem(),
        EXNBlocks.FIRED_CRUCIBLE.asItem(),
        0.7F,
        200,
        0.7F,
        100,
        "has_uncooked_crucible",
        EXNBlocks.FIRED_CRUCIBLE.getId());
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNBlocks.UNFIRED_CRUCIBLE)
        .pattern("c c")
        .pattern("c c")
        .pattern("ccc")
        .define('c', EXNItems.PORCELAIN_CLAY.asItem())
        .unlockedBy(
            PORCELAIN_CLAY_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.PORCELAIN_CLAY.asItem()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(EXNBlocks.UNFIRED_CRUCIBLE.getId()));

    ShapedRecipeBuilder.shaped(
            holderGetter, RecipeCategory.MISC, EXNItems.CRAFTING_DOLL.asItem(), 4)
        .pattern("xex")
        .pattern(" x ")
        .pattern("x x")
        .define('x', EXNItems.PORCELAIN_CLAY.asItem())
        .define('e', Tags.Items.GEMS_DIAMOND)
        .unlockedBy(
            PORCELAIN_CLAY_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.PORCELAIN_CLAY.asItem()))
        .unlockedBy(
            "has_diamond",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.GEMS_DIAMOND).build()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, "doll_x4")));

    ShapedRecipeBuilder.shaped(
            holderGetter, RecipeCategory.MISC, EXNItems.CRAFTING_DOLL.asItem(), 6)
        .pattern("xex")
        .pattern(" x ")
        .pattern("x x")
        .define('x', EXNItems.PORCELAIN_CLAY.asItem())
        .define('e', Tags.Items.GEMS_EMERALD)
        .unlockedBy(
            PORCELAIN_CLAY_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.PORCELAIN_CLAY.asItem()))
        .unlockedBy(
            "has_emerald",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.GEMS_EMERALD).build()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, "doll_x6")));

    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.FOOD, EXNBlocks.END_CAKE)
        .pattern("ece")
        .pattern("eke")
        .pattern("ece")
        .define('e', Items.ENDER_EYE)
        .define('c', Items.END_CRYSTAL)
        .define('k', Items.CAKE)
        .unlockedBy(
            "has_ender_pearl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENDER_PEARL))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNBlocks.END_CAKE.getId()));

    ShapelessRecipeBuilder.shapeless(
            holderGetter, RecipeCategory.MISC, EXNItems.PORCELAIN_CLAY.asItem())
        .requires(ExNihiloTags.CLAY)
        .requires(Items.BONE_MEAL)
        .unlockedBy(
            "has_clay",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, ExNihiloTags.CLAY).build()))
        .save(
            consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.PORCELAIN_CLAY.getId()));

    ItemDefinition<PebbleItem> blackstonePebble = EXNItems.PEBBLE_BLACKSTONE;
    final ResourceLocation gildedBlackstoneResourceLocation =
        BuiltInRegistries.BLOCK.getKey(Blocks.GILDED_BLACKSTONE);
    if (EXNItems.GOLD.getRawOreItem() != null) {
      ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, Blocks.GILDED_BLACKSTONE)
          .pattern("xxx")
          .pattern("xgx")
          .pattern("xxx")
          .define('x', blackstonePebble.asItem())
          .define('g', Tags.Items.RAW_MATERIALS_GOLD)
          .unlockedBy("has_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
          .save(
              consumer,
              RecipeProviderUtilities.createSaveLocation(gildedBlackstoneResourceLocation));
    }
    ResourceLocation cryingObsidianResourceLocation =
        BuiltInRegistries.BLOCK.getKey(Blocks.CRYING_OBSIDIAN);
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.DECORATIONS, Blocks.CRYING_OBSIDIAN)
        .pattern(" o ")
        .pattern("obo")
        .pattern(" o ")
        .define('b', Items.WATER_BUCKET)
        .define('o', Blocks.OBSIDIAN)
        .unlockedBy(
            "has_obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OBSIDIAN))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(cryingObsidianResourceLocation));
    ResourceLocation ancientDebrisResourceLocation =
        BuiltInRegistries.BLOCK.getKey(Blocks.ANCIENT_DEBRIS);
    ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, Blocks.ANCIENT_DEBRIS)
        .requires(Items.NETHERITE_SCRAP)
        .requires(Blocks.OBSIDIAN)
        .unlockedBy(
            "has_scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SCRAP))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(ancientDebrisResourceLocation));
  }

  private void addMeshes(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createMesh(EXNItems.MESH_FLINT.asItem(), EXNItems.MESH_STRING.asItem(), Items.FLINT, holderGetter, consumer);
    createMesh(
        EXNItems.MESH_IRON.asItem(),
        EXNItems.MESH_FLINT.asItem(),
        Tags.Items.INGOTS_IRON,
        holderGetter,
        consumer);
    createMesh(
        EXNItems.MESH_DIAMOND.asItem(),
        EXNItems.MESH_IRON.asItem(),
        Tags.Items.GEMS_DIAMOND,
        holderGetter,
        consumer);
    createMesh(
        EXNItems.MESH_EMERALD.asItem(),
        EXNItems.MESH_DIAMOND.asItem(),
        Tags.Items.GEMS_EMERALD,
        holderGetter,
        consumer);
    SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
            Ingredient.of(EXNItems.MESH_EMERALD.asItem()),
            Ingredient.of(holderGetter.getOrThrow(Tags.Items.INGOTS_NETHERITE)),
            RecipeCategory.MISC,
            EXNItems.MESH_NETHERITE.asItem())
        .unlocks(
            "has_emerald_mesh",
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.MESH_EMERALD.asItem()))
        .unlocks(
            MATERIAL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.INGOTS_NETHERITE).build()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                ResourceLocation.fromNamespaceAndPath(
                    ExNihiloSequentia.MOD_ID, ExNihiloConstants.Items.NETHERITE_MESH)));

    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNItems.MESH_STRING.asItem())
        .pattern("iii")
        .pattern("iii")
        .pattern("iii")
        .define('i', Tags.Items.STRINGS)
        .unlockedBy(
            "has_sieve",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                EXNBlocks.OAK_SIEVE,
                EXNBlocks.ACACIA_SIEVE,
                EXNBlocks.BAMBOO_SIEVE,
                EXNBlocks.BIRCH_SIEVE,
                EXNBlocks.CHERRY_SIEVE,
                EXNBlocks.JUNGLE_SIEVE,
                EXNBlocks.MANGROVE_SIEVE,
                EXNBlocks.DARK_OAK_SIEVE,
                EXNBlocks.SPRUCE_SIEVE,
                EXNBlocks.CRIMSON_SIEVE,
                EXNBlocks.WARPED_SIEVE))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.MESH_STRING.getId()));
  }

  private void addDolls(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNItems.SHULKER_DOLL.asItem())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.DYES_PURPLE)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.END_STONES)
        .define('b', Tags.Items.ENDER_PEARLS)
        .define('m', EXNItems.CRAFTING_DOLL.asItem())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.CRAFTING_DOLL.asItem()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.SHULKER_DOLL.getId()));
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNItems.GUARDIAN_DOLL.asItem())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.GEMS_PRISMARINE)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.DUSTS_REDSTONE)
        .define('b', ItemTags.FISHES)
        .define('m', EXNItems.CRAFTING_DOLL.asItem())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.CRAFTING_DOLL.asItem()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.GUARDIAN_DOLL.getId()));
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNItems.BEE_DOLL.asItem())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.DYES_YELLOW)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', ItemTags.FLOWERS)
        .define('b', EXNItems.BEEHIVE_FRAME.asItem())
        .define('m', EXNItems.CRAFTING_DOLL.asItem())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.CRAFTING_DOLL.asItem()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.BEE_DOLL.getId()));
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNItems.BLAZE_DOLL.asItem())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Items.BLAZE_POWDER)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.DUSTS_REDSTONE)
        .define('b', Tags.Items.CROPS_NETHER_WART)
        .define('m', EXNItems.CRAFTING_DOLL.asItem())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.CRAFTING_DOLL.asItem()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.BLAZE_DOLL.getId()));
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, EXNItems.ENDERMAN_DOLL.asItem())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.DYES_BLACK)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.DUSTS_REDSTONE)
        .define('b', Tags.Items.CROPS_NETHER_WART)
        .define('m', EXNItems.CRAFTING_DOLL.asItem())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.CRAFTING_DOLL.asItem()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(EXNItems.ENDERMAN_DOLL.getId()));
  }

  private void addHammers(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createHammer(EXNItems.HAMMER_ANDESITE.asItem(), Items.ANDESITE, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_BAMBOO.asItem(), Items.BAMBOO, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_BASALT.asItem(), Items.BASALT, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_BLACKSTONE.asItem(), Items.BLACKSTONE, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_CALCITE.asItem(), Items.CALCITE, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_CHERRY.asItem(), Items.CHERRY_PLANKS, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_COPPER.asItem(), Tags.Items.INGOTS_COPPER, holderGetter, consumer);
    //    createHammer(ExNihiloItems.HAMMER_CRIMSON_FUNGUS.asItem(), Items.CRIMSON_PLANKS,
    // consumer);
    createHammer(EXNItems.HAMMER_DEEPSLATE.asItem(), Items.DEEPSLATE, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_DIAMOND.asItem(), Tags.Items.GEMS_DIAMOND, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_DIORITE.asItem(), Items.DIORITE, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_DRIPSTONE.asItem(), Items.DRIPSTONE_BLOCK, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_GOLD.asItem(), Tags.Items.INGOTS_GOLD, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_GRANITE.asItem(), Items.GRANITE, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_IRON.asItem(), Tags.Items.INGOTS_IRON, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_NETHER_BRICK.asItem(), Items.NETHER_BRICKS, holderGetter, consumer);
    SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
            Ingredient.of(EXNItems.HAMMER_DIAMOND.asItem()),
            Ingredient.of(holderGetter.getOrThrow(Tags.Items.INGOTS_NETHERITE)),
            RecipeCategory.TOOLS,
            EXNItems.HAMMER_NETHERITE.asItem())
        .unlocks(
            "has_diamond_hammer",
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.HAMMER_DIAMOND.asItem()))
        .unlocks(
            MATERIAL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.INGOTS_NETHERITE).build()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                ResourceLocation.fromNamespaceAndPath(
                    ExNihiloSequentia.MOD_ID, ExNihiloConstants.Items.NETHERITE_HAMMER)));
    //    createHammer(ExNihiloItems.HAMMER_PRISMARINE.asItem(), Tags.Items.GEMS_PRISMARINE,
    // consumer);
    createHammer(EXNItems.HAMMER_RED_NETHER_BRICK.asItem(), Items.RED_NETHER_BRICKS, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_STONE.asItem(), Items.COBBLESTONE, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_TERRACOTTA.asItem(), Items.TERRACOTTA, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_TUFF.asItem(), Items.TUFF, holderGetter, consumer);
    //    createHammer(ExNihiloItems.HAMMER_WARPED_FUNGUS.asItem(), Items.WARPED_PLANKS, holderGetter, consumer);
    createHammer(EXNItems.HAMMER_WOOD.asItem(), ItemTags.PLANKS, holderGetter, consumer);
  }

  private void addOres(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createOre(EXNItems.IRON, holderGetter, consumer);
    createOre(EXNItems.GOLD, holderGetter, consumer);
    createOre(EXNItems.COPPER, holderGetter, consumer);
    createOreRecipes(
        EXNItems.LEAD,
        ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, EXNItems.LEAD.getIngotId()),
        holderGetter,
        consumer);
    createOreRecipes(
        EXNItems.NICKEL,
        ResourceLocation.fromNamespaceAndPath(
            ExNihiloSequentia.MOD_ID, EXNItems.NICKEL.getIngotId()),
        holderGetter,
        consumer);
    createOreRecipes(
        EXNItems.SILVER,
        ResourceLocation.fromNamespaceAndPath(
            ExNihiloSequentia.MOD_ID, EXNItems.SILVER.getIngotId()),
        holderGetter,
        consumer);
    createOreRecipes(
        EXNItems.TIN,
        ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, EXNItems.TIN.getIngotId()),
        holderGetter,
        consumer);
    createOreRecipes(
        EXNItems.ALUMINUM,
        ResourceLocation.fromNamespaceAndPath(
            ExNihiloSequentia.MOD_ID, EXNItems.ALUMINUM.getIngotId()),
        holderGetter,
        consumer);
    createOreRecipes(
        EXNItems.PLATINUM,
        ResourceLocation.fromNamespaceAndPath(
            ExNihiloSequentia.MOD_ID, EXNItems.PLATINUM.getIngotId()),
        holderGetter,
        consumer);
    createOreRecipes(
        EXNItems.URANIUM,
        ResourceLocation.fromNamespaceAndPath(
            ExNihiloSequentia.MOD_ID, EXNItems.URANIUM.getIngotId()),
        holderGetter,
        consumer);
    createOreRecipes(
        EXNItems.ZINC,
        ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, EXNItems.ZINC.getIngotId()),
        holderGetter,
        consumer);
  }

  private void addSieves(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createSieve(holderGetter, consumer, EXNBlocks.ACACIA_SIEVE, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
    createSieve(holderGetter, consumer, EXNBlocks.BAMBOO_SIEVE, Items.BAMBOO_PLANKS, Items.BAMBOO_SLAB);
    createSieve(holderGetter, consumer, EXNBlocks.BIRCH_SIEVE, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
    createSieve(holderGetter, consumer, EXNBlocks.CHERRY_SIEVE, Items.CHERRY_PLANKS, Items.CHERRY_SLAB);
    createSieve(holderGetter,consumer, EXNBlocks.JUNGLE_SIEVE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
    createSieve(holderGetter,consumer, EXNBlocks.MANGROVE_SIEVE, Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB);
    createSieve(holderGetter,consumer, EXNBlocks.OAK_SIEVE, Items.OAK_PLANKS, Items.OAK_SLAB);
    createSieve(holderGetter,consumer, EXNBlocks.SPRUCE_SIEVE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
    createSieve(holderGetter,consumer, EXNBlocks.CRIMSON_SIEVE, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
    createSieve(holderGetter,consumer, EXNBlocks.WARPED_SIEVE, Items.WARPED_PLANKS, Items.WARPED_SLAB);
  }

  private void addCrucibles(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createCrucible(holderGetter, consumer, EXNBlocks.ACACIA_CRUCIBLE, Items.ACACIA_LOG, Items.ACACIA_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.BAMBOO_CRUCIBLE, Items.BAMBOO_BLOCK, Items.BAMBOO_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.BIRCH_CRUCIBLE, Items.BIRCH_LOG, Items.BIRCH_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.CHERRY_CRUCIBLE, Items.CHERRY_LOG, Items.CHERRY_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.DARK_OAK_CRUCIBLE, Items.DARK_OAK_LOG, Items.DARK_OAK_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.JUNGLE_CRUCIBLE, Items.JUNGLE_LOG, Items.JUNGLE_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.MANGROVE_CRUCIBLE, Items.MANGROVE_LOG, Items.MANGROVE_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.OAK_CRUCIBLE, Items.OAK_LOG, Items.OAK_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.SPRUCE_CRUCIBLE, Items.SPRUCE_LOG, Items.SPRUCE_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.CRIMSON_CRUCIBLE, Items.CRIMSON_STEM, Items.CRIMSON_SLAB);
    createCrucible(holderGetter, consumer, EXNBlocks.WARPED_CRUCIBLE, Items.WARPED_STEM, Items.WARPED_SLAB);
  }

  private void addBarrels(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createBarrel(holderGetter, consumer, EXNBlocks.STONE_BARREL, Tags.Items.STONES, Items.STONE_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.ACACIA_BARREL, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.BAMBOO_BARREL, Items.BAMBOO_PLANKS, Items.BAMBOO_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.BIRCH_BARREL, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.CHERRY_BARREL, Items.CHERRY_PLANKS, Items.CHERRY_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.DARK_OAK_BARREL, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.JUNGLE_BARREL, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.MANGROVE_BARREL, Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.OAK_BARREL, Items.OAK_PLANKS, Items.OAK_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.SPRUCE_BARREL, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.CRIMSON_BARREL, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
    createBarrel(holderGetter, consumer, EXNBlocks.WARPED_BARREL, Items.WARPED_PLANKS, Items.WARPED_SLAB);
  }

  private void addPebbleBlocks(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createPebbleBlock(Blocks.ANDESITE, EXNItems.PEBBLE_ANDESITE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.BASALT, EXNItems.PEBBLE_BASALT.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.BLACKSTONE, EXNItems.PEBBLE_BLACKSTONE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.COBBLESTONE, EXNItems.PEBBLE_STONE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.CALCITE, EXNItems.PEBBLE_CALCITE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.DEEPSLATE, EXNItems.PEBBLE_DEEPSLATE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.DIORITE, EXNItems.PEBBLE_DIORITE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.DRIPSTONE_BLOCK, EXNItems.PEBBLE_DRIPSTONE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.END_STONE, EXNItems.PEBBLE_END_STONE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.GRANITE, EXNItems.PEBBLE_GRANITE.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.NETHERRACK, EXNItems.PEBBLE_NETHERRACK.asItem(), holderGetter, consumer);
    createPebbleBlock(Blocks.TUFF, EXNItems.PEBBLE_TUFF.asItem(), holderGetter, consumer);
  }

  private void addCrooks(HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createCrook(EXNItems.CROOK_ANDESITE.asItem(), EXNItems.PEBBLE_ANDESITE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_BAMBOO.asItem(), Items.BAMBOO, holderGetter, consumer);
    createCrook(EXNItems.CROOK_BASALT.asItem(), EXNItems.PEBBLE_BASALT.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_BLACKSTONE.asItem(), EXNItems.PEBBLE_BLACKSTONE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_BONE.asItem(), Tags.Items.BONES, holderGetter, consumer);
    createCrook(EXNItems.CROOK_CALCITE.asItem(), EXNItems.PEBBLE_CALCITE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_CHERRY.asItem(), Items.CHERRY_PLANKS, holderGetter, consumer);
    createCrook(EXNItems.CROOK_COPPER.asItem(), ExNihiloTags.NUGGET_COPPER, holderGetter, consumer);
    //    createCrook(ExNihiloItems.CROOK_CRIMSON_FUNGUS.asItem(), Items.CRIMSON_PLANKS, consumer);
    createCrook(EXNItems.CROOK_DEEPSLATE.asItem(), EXNItems.PEBBLE_DEEPSLATE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_DIAMOND.asItem(), Tags.Items.GEMS_DIAMOND, holderGetter, consumer);
    createCrook(EXNItems.CROOK_DIORITE.asItem(), EXNItems.PEBBLE_DIORITE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_DRIPSTONE.asItem(), EXNItems.PEBBLE_DRIPSTONE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_GOLD.asItem(), Tags.Items.NUGGETS_GOLD, holderGetter, consumer);
    createCrook(EXNItems.CROOK_GRANITE.asItem(), EXNItems.PEBBLE_GRANITE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_IRON.asItem(), Tags.Items.NUGGETS_IRON, holderGetter, consumer);
    createCrook(EXNItems.CROOK_NETHER_BRICK.asItem(), Items.NETHER_BRICKS, holderGetter, consumer);
    SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
            Ingredient.of(EXNItems.CROOK_DIAMOND.asItem()),
            Ingredient.of(holderGetter.getOrThrow(Tags.Items.INGOTS_NETHERITE)),
            RecipeCategory.TOOLS,
            EXNItems.CROOK_NETHERITE.asItem())
        .unlocks(
            "has_diamond_crook",
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.CROOK_DIAMOND.asItem()))
        .unlocks(
            MATERIAL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.INGOTS_NETHERITE).build()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                ResourceLocation.fromNamespaceAndPath(
                    ExNihiloSequentia.MOD_ID, ExNihiloConstants.Items.NETHERITE_CROOK)));
    createCrook(
        EXNItems.CROOK_RED_NETHER_BRICK.asItem(), Items.RED_NETHER_BRICKS, holderGetter, consumer);
    //    createCrook(ExNihiloItems.CROOK_PRISMARINE.asItem(), Tags.Items.GEMS_PRISMARINE,
    // consumer);
    createCrook(
        EXNItems.CROOK_STONE.asItem(), EXNItems.PEBBLE_STONE.asItem(), holderGetter, consumer);
    createCrook(EXNItems.CROOK_TERRACOTTA.asItem(), Items.TERRACOTTA, holderGetter, consumer);
    createCrook(
        EXNItems.CROOK_TUFF.asItem(), EXNItems.PEBBLE_TUFF.asItem(), holderGetter, consumer);
    //    createCrook(ExNihiloItems.CROOK_WARPED_FUNGUS.asItem(), Items.WARPED_PLANKS, consumer);
    createCrook(EXNItems.CROOK_WOOD.asItem(), Tags.Items.RODS_WOODEN, holderGetter, consumer);
  }

  private void createCrook(
      Item result, Item input, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.TOOLS, result)
        .pattern("xx")
        .pattern(" x")
        .pattern(" x")
        .define('x', input)
        .group(ExNihiloSequentia.MOD_ID)
        .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(result))));
  }

  private void createCrook(
      Item result, TagKey<Item> input, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.TOOLS, result)
        .pattern("xx")
        .pattern(" x")
        .pattern(" x")
        .define('x', input)
        .group(ExNihiloSequentia.MOD_ID)
        .unlockedBy(
            PEBBLE_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, input).build()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(result))));
  }

  private void createPebbleBlock(
      Block result, Item input, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(result);
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.BUILDING_BLOCKS, result)
        .pattern("xx")
        .pattern("xx")
        .define('x', input)
        .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
        .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(resourceLocation));
  }

  private void createBarrel(
      HolderGetter<Item> holderGetter,
      RecipeOutput consumer,
      BlockDefinition<BarrelBlock> barrel,
      TagKey<Item> block,
      Item slab) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, barrel.block())
        .pattern("x x")
        .pattern("x x")
        .pattern("x-x")
        .define('x', block)
        .define('-', slab)
        .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
        .unlockedBy(
            "has_walls",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, block).build()))
        .unlockedBy("has_base", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(barrel.getId()));
  }

  private void createBarrel(
      HolderGetter<Item> holderGetter,
      RecipeOutput consumer,
      BlockDefinition<BarrelBlock> barrel,
      Item block,
      Item slab) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, barrel.block())
        .pattern("x x")
        .pattern("x x")
        .pattern("x-x")
        .define('x', block)
        .define('-', slab)
        .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
        .unlockedBy("has_walls", InventoryChangeTrigger.TriggerInstance.hasItems(block))
        .unlockedBy("has_base", InventoryChangeTrigger.TriggerInstance.hasItems(slab))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(barrel.getId()));
  }

  private void createCrucible(
      HolderGetter<Item> holderGetter, RecipeOutput consumer, BlockDefinition<CrucibleBlock> crucible, Item block, Item slab) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, crucible.block())
        .pattern("c c")
        .pattern("clc")
        .pattern("s s")
        .define('c', block)
        .define('l', slab)
        .define('s', Tags.Items.RODS_WOODEN)
        .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
        .unlockedBy("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(block))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(crucible.getId()));
  }

  private void createCrucible(
      HolderGetter<Item> holderGetter,
      RecipeOutput consumer,
      BlockDefinition<CrucibleBlock> crucible,
      TagKey<Item> block,
      TagKey<Item> slab) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, crucible.block())
        .pattern("c c")
        .pattern("clc")
        .pattern("s s")
        .define('c', block)
        .define('l', slab)
        .define('s', Tags.Items.RODS_WOODEN)
        .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
        .unlockedBy(
            "has_logs",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, block).build()))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(crucible.getId()));
  }

  private void createSieve(
      HolderGetter<Item> holderGetter,
      RecipeOutput consumer, BlockDefinition<SieveBlock> sieve, Item block, Item slab) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, sieve)
        .pattern("p p")
        .pattern("plp")
        .pattern("s s")
        .define('p', block)
        .define('l', slab)
        .define('s', Tags.Items.RODS_WOODEN)
        .unlockedBy("has_plank", InventoryChangeTrigger.TriggerInstance.hasItems(block))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(sieve.getId()));
  }

  private void createOre(Ore ore, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createRawRecipe(ore, holderGetter, consumer);
    createNuggetRecipes(ore, holderGetter, consumer);
  }

  private void createRawRecipe(Ore ore, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    Item piece = ore.getPieceItem();
    Either<ItemDefinition<OreItem>, Item> rawEither = ore.getRawOreItem();
    Item rawOre;
    Optional<ItemDefinition<OreItem>> left = rawEither.left();
    Optional<Item> right = rawEither.right();
    if (left.isPresent()) {
      rawOre = left.get().asItem();
    } else if (right.isPresent()) {
      rawOre = right.get();
    } else {
      throw new IllegalStateException("Raw either is completely empty");
    }
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, rawOre)
        .pattern("xx")
        .pattern("xx")
        .define('x', piece)
        .group(ExNihiloSequentia.MOD_ID)
        .unlockedBy("has_piece", InventoryChangeTrigger.TriggerInstance.hasItems(piece))
        .save(
            consumer,
            ResourceKey.create(Registries.RECIPE,ResourceLocation.fromNamespaceAndPath(
                ExNihiloSequentia.MOD_ID,
                RecipeProviderUtilities.prependRecipePrefix(
                    BuiltInRegistries.ITEM.getKey(rawOre).getPath()))));
  }

  private void createNuggetRecipes(Ore ore, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    Optional<ItemDefinition<OreItem>> nuggetItem = ore.getNuggetItem().left();
    if (nuggetItem.isPresent()) {
      Either<ItemDefinition<OreItem>, Item> eitherIngot = ore.getIngotItem();
      Item ingot;
      Optional<ItemDefinition<OreItem>> left = eitherIngot.left();
      Optional<Item> right = eitherIngot.right();
      if (left.isPresent()) {
        ingot = left.get().asItem();
      } else if (right.isPresent()) {
        ingot = right.get();
      } else {
        throw new IllegalStateException("Ingot either is completely empty");
      }
      Item nugget = nuggetItem.get().asItem();

      ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, ingot)
          .pattern("xxx")
          .pattern("xxx")
          .pattern("xxx")
          .define('x', nugget)
          .group(ExNihiloSequentia.MOD_ID)
          .unlockedBy("has_nugget", InventoryChangeTrigger.TriggerInstance.hasItems(nugget))
          .save(
              consumer,
              ResourceKey.create(Registries.RECIPE,
              ResourceLocation.fromNamespaceAndPath(
                  ExNihiloSequentia.MOD_ID,
                  RecipeProviderUtilities.prependRecipePrefix(
                      BuiltInRegistries.ITEM.getKey(ingot).getPath() + "_from_nugget"))));

      ShapelessRecipeBuilder.shapeless(holderGetter, RecipeCategory.MISC, nugget, 9)
          .requires(ingot)
          .unlockedBy("has_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ingot))
          .save(
              consumer,
              RecipeProviderUtilities.createSaveLocation(BuiltInRegistries.ITEM.getKey(nugget)));
    }
  }

  private void createOreRecipes(Ore ore, ResourceLocation registryId, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    createOre(ore, holderGetter, consumer);
    Optional<ItemDefinition<OreItem>> rawOre = ore.getRawOreItem().left();
    Optional<ItemDefinition<OreItem>> ingot = ore.getIngotItem().left();
    if (rawOre.isPresent() && ingot.isPresent()) {
      createSmeltingRecipe(
          consumer,
          rawOre.get().asItem(),
          ingot.get().asItem(),
          0.7F,
          200,
          0.7F,
          100,
          CHUNK_CONDITION,
          registryId);
    }
  }

  private void createSmeltingRecipe(
      RecipeOutput consumer,
      Item input,
      Item output,
      final float xpSmelt,
      final int durationSmelt,
      final float xpBlast,
      final int durationBlast,
      String condition,
      ResourceLocation rl) {
    SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(input), RecipeCategory.MISC, output, xpSmelt, durationSmelt)
        .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
        .save(consumer, RecipeProviderUtilities.createSaveLocation(rl));
    SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(input), RecipeCategory.MISC, output, xpBlast, durationBlast)
        .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(ResourceLocation.parse(rl + "_blast")));
  }

  private void createHammer(Item output, Item input, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.TOOLS, output)
        .pattern(" x ")
        .pattern(" -x")
        .pattern("-  ")
        .define('x', input)
        .define('-', Tags.Items.RODS)
        .unlockedBy(
            "has_stick",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.RODS).build()))
        .unlockedBy(MATERIAL_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(output))));
  }

  private void createHammer(Item output, TagKey<Item> input, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.TOOLS, output)
        .pattern(" x ")
        .pattern(" -x")
        .pattern("-  ")
        .define('x', input)
        .define('-', Tags.Items.RODS)
        .unlockedBy(
            "has_stick",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, Tags.Items.RODS).build()))
        .unlockedBy(
            MATERIAL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(holderGetter, input).build()))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(output))));
  }

  private void createMesh(
      Item output, Item inputMesh, TagKey<Item> inputItem, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, output)
        .pattern("i i")
        .pattern("imi")
        .pattern("i i")
        .define('i', inputItem)
        .define('m', inputMesh)
        .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(output))));
  }

  private void createMesh(Item output, Item inputMesh, Item inputItem, HolderGetter<Item> holderGetter, RecipeOutput consumer) {
    ShapedRecipeBuilder.shaped(holderGetter, RecipeCategory.MISC, output)
        .pattern("i i")
        .pattern("imi")
        .pattern("i i")
        .define('i', inputItem)
        .define('m', inputMesh)
        .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(output))));
  }

  private void createCookingRecipe(
      RecipeOutput consumer,
      Item input,
      Item output,
      final float xpCampfire,
      final int durationCampfire,
      final float xpSmoker,
      final int durationSmoker,
      String condition,
      ResourceLocation rl) {
    SimpleCookingRecipeBuilder.campfireCooking(
            Ingredient.of(input), RecipeCategory.MISC, output, xpCampfire, durationCampfire)
        .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                ResourceLocation.parse(rl + "_from_campfire")));
    SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(input), RecipeCategory.MISC, output, xpSmoker, durationSmoker)
        .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
        .save(
            consumer,
            RecipeProviderUtilities.createSaveLocation(
                ResourceLocation.parse(rl + "_from_smoker")));
  }
}
