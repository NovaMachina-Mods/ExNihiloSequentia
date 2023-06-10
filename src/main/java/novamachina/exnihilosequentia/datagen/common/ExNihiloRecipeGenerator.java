package novamachina.exnihilosequentia.datagen.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.exnihilosequentia.common.item.PebbleItem;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractRecipeGenerator;
import novamachina.novacore.world.item.ItemDefinition;

public class ExNihiloRecipeGenerator extends AbstractRecipeGenerator {

  public static final String GRAVEL = "gravel";
  @Nonnull private static final String COBBLESTONE = "cobblestone";
  @Nonnull private static final String DOLL_CONDITION = "has_doll";
  @Nonnull private static final String GRAVEL_SUFFIX = "_gravel";
  @Nonnull private static final String LEAVES = "leaves";
  @Nonnull private static final String MATERIAL_CONDITION = "has_material";
  @Nonnull private static final String NETHERRACK = "netherrack";
  @Nonnull private static final String PORCELAIN_CLAY_CONDITION = "has_porcelain_clay";
  private static final String SILKWORM = "silkworm";
  @Nonnull private static final Fluid lava = Fluids.LAVA;
  @Nonnull private static final Fluid seawater = EXNFluids.SEA_WATER.getStillFluid();
  @Nonnull private static final Fluid water = Fluids.WATER;
  @Nonnull private static final Fluid witchwater = EXNFluids.WITCH_WATER.getStillFluid();

  public ExNihiloRecipeGenerator(@Nonnull final DataGenerator generator) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  }

  @Override
  protected void buildCraftingRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    registerCrooks(consumer);
    registerPebbleBlocks(consumer);
    registerBarrels(consumer);
    registerCrucibles(consumer);
    registerSieves(consumer);
    registerOres(consumer);
    registerHammers(consumer);
    registerDolls(consumer);
    registerMeshes(consumer);
    registerMisc(consumer);
    registerCustomRecipes(consumer);
  }

  private Map<Block, Item> getLeavesSaplings() {
    @Nonnull final Map<Block, Item> saplingMap = new HashMap<>();
    saplingMap.put(Blocks.ACACIA_LEAVES, Items.ACACIA_SAPLING);
    saplingMap.put(Blocks.BIRCH_LEAVES, Items.BIRCH_SAPLING);
    saplingMap.put(Blocks.DARK_OAK_LEAVES, Items.DARK_OAK_SAPLING);
    saplingMap.put(Blocks.JUNGLE_LEAVES, Items.JUNGLE_SAPLING);
    saplingMap.put(Blocks.OAK_LEAVES, Items.OAK_SAPLING);
    saplingMap.put(Blocks.SPRUCE_LEAVES, Items.SPRUCE_SAPLING);

    return saplingMap;
  }

  private void registerBarrels(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createBarrel(consumer, EXNBlocks.STONE_BARREL, Tags.Items.STONE, Items.STONE_SLAB);
    createBarrel(consumer, EXNBlocks.ACACIA_BARREL, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
    createBarrel(consumer, EXNBlocks.BIRCH_BARREL, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
    createBarrel(
        consumer, EXNBlocks.DARK_OAK_BARREL, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
    createBarrel(consumer, EXNBlocks.JUNGLE_BARREL, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
    createBarrel(
        consumer, EXNBlocks.MANGROVE_BARREL, Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB);
    createBarrel(consumer, EXNBlocks.OAK_BARREL, Items.OAK_PLANKS, Items.OAK_SLAB);
    createBarrel(consumer, EXNBlocks.SPRUCE_BARREL, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
    createBarrel(consumer, EXNBlocks.CRIMSON_BARREL, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
    createBarrel(consumer, EXNBlocks.WARPED_BARREL, Items.WARPED_PLANKS, Items.WARPED_SLAB);
  }

  private void registerCompostRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createCompostRecipe(consumer, ItemTags.SAPLINGS, 125, "saplings");
    createCompostRecipe(consumer, ItemTags.LEAVES, 125, LEAVES);
    createCompostRecipe(consumer, ItemTags.FLOWERS, 100, "flowers");
    createCompostRecipe(consumer, ItemTags.FISHES, 150, "fishes");
    createCompostRecipe(consumer, ExNihiloTags.MEAT_COOKED, 200, "meat_cooked");
    createCompostRecipe(consumer, ExNihiloTags.MEAT_UNCOOKED, 200, "meat_uncooked");
    createCompostRecipe(consumer, Tags.Items.SEEDS, 80, "seeds");
    createCompostRecipe(consumer, Tags.Items.CROPS_WHEAT, 80, "wheat");
    createCompostRecipe(consumer, Tags.Items.CROPS_CARROT, 100, "carrot");
    createCompostRecipe(consumer, Tags.Items.CROPS_BEETROOT, 100, "beetroot");
    createCompostRecipe(consumer, Tags.Items.CROPS_POTATO, 100, "potato");
    createCompostRecipe(consumer, Tags.Items.CROPS_NETHER_WART, 100, "nether_wart");
    createCompostRecipe(consumer, Tags.Items.EGGS, 80, "eggs");
    createCompostRecipe(consumer, Tags.Items.STRING, 40, "string");
    createCompostRecipe(consumer, Items.ROTTEN_FLESH, 100, "rotten_flesh");
    createCompostRecipe(consumer, Items.SPIDER_EYE, 80, "spider_eye");
    createCompostRecipe(consumer, Items.BREAD, 160, "bread");
    createCompostRecipe(consumer, Blocks.BROWN_MUSHROOM, 100, "brown_mushroom");
    createCompostRecipe(consumer, Blocks.RED_MUSHROOM, 100, "red_mushroom");
    createCompostRecipe(consumer, Items.CRIMSON_FUNGUS, 100, "crimson_fungus");
    createCompostRecipe(consumer, Items.WARPED_FUNGUS, 100, "warped_fungus");
    createCompostRecipe(consumer, Items.PUMPKIN_PIE, 160, "pumpkin_pie");
    createCompostRecipe(consumer, EXNItems.SILKWORM.asItem(), 40, SILKWORM);
    createCompostRecipe(consumer, EXNItems.COOKED_SILKWORM.asItem(), 40, "cooked_silkworm");
    createCompostRecipe(consumer, Items.APPLE, 100, "apple");
    createCompostRecipe(consumer, Items.MELON_SLICE, 40, "melon_slice");
    createCompostRecipe(consumer, Items.MELON, 1000 / 6, "melon");
    createCompostRecipe(consumer, Items.PUMPKIN, 1000 / 6, "pumpkin");
    createCompostRecipe(consumer, Items.CARVED_PUMPKIN, 1000 / 6, "carved_pumpkin");
    createCompostRecipe(consumer, Items.JACK_O_LANTERN, 1000 / 6, "jack_o_lantern");
    createCompostRecipe(consumer, Items.CACTUS, 100, "cactus");
    createCompostRecipe(consumer, Items.BAKED_POTATO, 150, "baked_potato");
    createCompostRecipe(consumer, Items.POISONOUS_POTATO, 200, "poisonous_potato");
    createCompostRecipe(consumer, Items.LILY_PAD, 100, "lily_pad");
    createCompostRecipe(consumer, Items.VINE, 100, "vine");
    createCompostRecipe(consumer, Items.WEEPING_VINES, 100, "weeping_vine");
    createCompostRecipe(consumer, Items.TWISTING_VINES, 100, "twisting_vine");
    createCompostRecipe(consumer, Items.TALL_GRASS, 100, "tall_grass");
    createCompostRecipe(consumer, Items.SUGAR_CANE, 80, "sugar_cane");
    createCompostRecipe(consumer, EXNItems.GRASS_SEED.asItem(), 100, "grass_seed");
    createCompostRecipe(consumer, EXNItems.MYCELIUM_SPORE.asItem(), 100, "mycelium_spore");
    createCompostRecipe(
        consumer, EXNItems.CRIMSON_NYLIUM_SPORE.asItem(), 100, "crimson_nylium_spore");
    createCompostRecipe(
        consumer, EXNItems.WARPED_NYLIUM_SPORE.asItem(), 100, "warped_nylium_spore");
    createCompostRecipe(consumer, Items.SWEET_BERRIES, 100, "sweet_berries");
    createCompostRecipe(consumer, Items.SPORE_BLOSSOM, 100, "spore_blossom");
    createCompostRecipe(consumer, Items.KELP, 100, "kelp");
    createCompostRecipe(consumer, Items.MANGROVE_ROOTS, 100, "mangrove_roots");
    createCompostRecipe(consumer, Items.CRIMSON_ROOTS, 100, "crimson_roots");
    createCompostRecipe(consumer, Items.WARPED_ROOTS, 100, "warped_roots");
    createCompostRecipe(consumer, Items.HANGING_ROOTS, 100, "hanging_roots");
    createCompostRecipe(consumer, Items.BIG_DRIPLEAF, 100, "big_dripleaf");
    createCompostRecipe(consumer, Items.SMALL_DRIPLEAF, 100, "small_dripleaf");
    createCompostRecipe(consumer, Items.FERN, 100, "small_fern");
    createCompostRecipe(consumer, Items.LARGE_FERN, 100, "large_fern");
    createCompostRecipe(consumer, Items.SEAGRASS, 100, "sea_grass");
    createCompostRecipe(consumer, Items.BAMBOO, 100, "bamboo");
    createCompostRecipe(consumer, Items.GLOW_BERRIES, 100, "glow_berries");
  }

  private void registerCrookRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createCrookRecipes(consumer, ItemTags.LEAVES, EXNItems.SILKWORM.asItem(), 0.1F, LEAVES);
    createCrookRecipes(
        consumer,
        EXNBlocks.INFESTED_LEAVES.block(),
        EXNItems.SILKWORM.asItem(),
        0.2F,
        SILKWORM);
    createCrookRecipes(
        consumer, EXNBlocks.INFESTED_LEAVES.block(), Items.STRING, 0.5F, "string");
  }

  private void registerCrooks(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createCrook(
        EXNItems.CROOK_ANDESITE.asItem(), EXNItems.PEBBLE_ANDESITE.asItem(), consumer);
    createCrook(
        EXNItems.CROOK_BASALT.asItem(), EXNItems.PEBBLE_BASALT.asItem(), consumer);
    createCrook(
        EXNItems.CROOK_BLACKSTONE.asItem(),
        EXNItems.PEBBLE_BLACKSTONE.asItem(),
        consumer);
    createCrook(EXNItems.CROOK_BONE.asItem(), Tags.Items.BONES, consumer);
    createCrook(
        EXNItems.CROOK_CALCITE.asItem(), EXNItems.PEBBLE_CALCITE.asItem(), consumer);
    createCrook(EXNItems.CROOK_COPPER.asItem(), ExNihiloTags.NUGGET_COPPER, consumer);
    //    createCrook(ExNihiloItems.CROOK_CRIMSON_FUNGUS.asItem(), Items.CRIMSON_PLANKS, consumer);
    createCrook(
        EXNItems.CROOK_DEEPSLATE.asItem(), EXNItems.PEBBLE_DEEPSLATE.asItem(), consumer);
    createCrook(EXNItems.CROOK_DIAMOND.asItem(), Tags.Items.GEMS_DIAMOND, consumer);
    createCrook(
        EXNItems.CROOK_DIORITE.asItem(), EXNItems.PEBBLE_DIORITE.asItem(), consumer);
    createCrook(
        EXNItems.CROOK_DRIPSTONE.asItem(), EXNItems.PEBBLE_DRIPSTONE.asItem(), consumer);
    createCrook(EXNItems.CROOK_GOLD.asItem(), Tags.Items.NUGGETS_GOLD, consumer);
    createCrook(
        EXNItems.CROOK_GRANITE.asItem(), EXNItems.PEBBLE_GRANITE.asItem(), consumer);
    createCrook(EXNItems.CROOK_IRON.asItem(), Tags.Items.NUGGETS_IRON, consumer);
    createCrook(EXNItems.CROOK_NETHER_BRICK.asItem(), Items.NETHER_BRICKS, consumer);
    UpgradeRecipeBuilder.smithing(
            Ingredient.of(EXNItems.CROOK_DIAMOND.asItem()),
            Ingredient.of(Tags.Items.INGOTS_NETHERITE),
            EXNItems.CROOK_NETHERITE.asItem())
        .unlocks(
            "has_diamond_crook",
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.CROOK_DIAMOND.asItem()))
        .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(
                    ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                    ExNihiloConstants.Items.NETHERITE_CROOK)));
    createCrook(EXNItems.CROOK_RED_NETHER_BRICK.asItem(), Items.RED_NETHER_BRICKS, consumer);
    //    createCrook(ExNihiloItems.CROOK_PRISMARINE.asItem(), Tags.Items.GEMS_PRISMARINE,
    // consumer);
    createCrook(EXNItems.CROOK_STONE.asItem(), EXNItems.PEBBLE_STONE.asItem(), consumer);
    createCrook(EXNItems.CROOK_TERRACOTTA.asItem(), Items.TERRACOTTA, consumer);
    createCrook(EXNItems.CROOK_TUFF.asItem(), EXNItems.PEBBLE_TUFF.asItem(), consumer);
    //    createCrook(ExNihiloItems.CROOK_WARPED_FUNGUS.asItem(), Items.WARPED_PLANKS, consumer);
    createCrook(EXNItems.CROOK_WOOD.asItem(), Tags.Items.RODS_WOODEN, consumer);
  }

  private void registerCrucibleRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createFiredCrucibleRecipes(consumer, Blocks.COBBLESTONE, 250, COBBLESTONE);
    createFiredCrucibleRecipes(consumer, Blocks.ANDESITE, 250, "andesite");
    createFiredCrucibleRecipes(consumer, Blocks.BASALT, 250, "basalt");
    createFiredCrucibleRecipes(consumer, Blocks.BLACKSTONE, 250, "blackstone");
    createFiredCrucibleRecipes(consumer, Blocks.CALCITE, 250, "calcite");
    createFiredCrucibleRecipes(consumer, Blocks.DEEPSLATE, 250, "deepslate");
    createFiredCrucibleRecipes(consumer, Blocks.DIORITE, 250, "diorite");
    createFiredCrucibleRecipes(consumer, Blocks.DRIPSTONE_BLOCK, 250, "dripstone");
    createFiredCrucibleRecipes(consumer, Blocks.END_STONE, 250, "end_stone");
    createFiredCrucibleRecipes(consumer, Blocks.GRANITE, 250, "granite");
    createFiredCrucibleRecipes(consumer, Blocks.TUFF, 250, "tuff");
    createFiredCrucibleRecipes(consumer, Blocks.STONE, 250, "stone");
    createFiredCrucibleRecipes(consumer, Blocks.GRAVEL, 200, GRAVEL);
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_ANDESITE.block(), 200, "crushed_andesite");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_BASALT.block(), 200, "crushed_basalt");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_BLACKSTONE.block(), 200, "crushed_blackstone");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_CALCITE.block(), 200, "crushed_calcite");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_DEEPSLATE.block(), 200, "crushed_deepslate");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_DIORITE.block(), 200, "crushed_diorite");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_DRIPSTONE.block(), 200, "crushed_dripstone");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_END_STONE.block(), 200, "crushed_end_stone");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_GRANITE.block(), 200, "crushed_granite");
    createFiredCrucibleRecipes(
        consumer, EXNBlocks.CRUSHED_NETHERRACK.block(), 200, "crushed_netherrack");
    createFiredCrucibleRecipes(consumer, EXNBlocks.CRUSHED_TUFF.block(), 200, "crushed_tuff");
    createFiredCrucibleRecipes(consumer, Blocks.SAND, 100, "sand");
    createFiredCrucibleRecipes(consumer, EXNBlocks.DUST.block(), 50, "dust");
    createFiredCrucibleRecipes(consumer, Blocks.NETHERRACK, 1000, NETHERRACK);
    createFiredCrucibleRecipes(consumer, Blocks.OBSIDIAN, 1000, "obsidian");

    createWaterCrucibleRecipes(consumer, ItemTags.SAPLINGS, 250, "saplings");
    createWaterCrucibleRecipes(consumer, ItemTags.LEAVES, 250, LEAVES);
  }

  private void registerCrucibles(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createCrucible(consumer, EXNBlocks.ACACIA_CRUCIBLE, Items.ACACIA_LOG, Items.ACACIA_SLAB);
    createCrucible(consumer, EXNBlocks.BIRCH_CRUCIBLE, Items.BIRCH_LOG, Items.BIRCH_SLAB);
    createCrucible(
        consumer, EXNBlocks.DARK_OAK_CRUCIBLE, Items.DARK_OAK_LOG, Items.DARK_OAK_SLAB);
    createCrucible(consumer, EXNBlocks.JUNGLE_CRUCIBLE, Items.JUNGLE_LOG, Items.JUNGLE_SLAB);
    createCrucible(
        consumer, EXNBlocks.MANGROVE_CRUCIBLE, Items.MANGROVE_LOG, Items.MANGROVE_SLAB);
    createCrucible(consumer, EXNBlocks.OAK_CRUCIBLE, Items.OAK_LOG, Items.OAK_SLAB);
    createCrucible(consumer, EXNBlocks.SPRUCE_CRUCIBLE, Items.SPRUCE_LOG, Items.SPRUCE_SLAB);
    createCrucible(
        consumer, EXNBlocks.CRIMSON_CRUCIBLE, Items.CRIMSON_STEM, Items.CRIMSON_SLAB);
    createCrucible(consumer, EXNBlocks.WARPED_CRUCIBLE, Items.WARPED_STEM, Items.WARPED_SLAB);
  }

  private void registerCustomRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    registerHammerRecipes(consumer);
    registerCrookRecipes(consumer);
    registerCompostRecipes(consumer);
    registerFluidItemRecipes(consumer);
    registerFluidOnTopRecipes(consumer);
    registerFluidTransformRecipes(consumer);
    registerCrucibleRecipes(consumer);
    registerHeatRecipes(consumer);
    registerSieveRecipes(consumer);
  }

  private void registerDefaultOres(
      @Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Ore ore) {
    @Nullable final Item oreItem = ore.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.075F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc(ore.getPieceId() + GRAVEL_SUFFIX));
  }

  private void registerDolls(@Nonnull final Consumer<FinishedRecipe> consumer) {
    ShapedRecipeBuilder.shaped(EXNItems.SHULKER_DOLL.asItem())
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
        .save(consumer, createSaveLocation(EXNItems.SHULKER_DOLL.getId()));
    ShapedRecipeBuilder.shaped(EXNItems.GUARDIAN_DOLL.asItem())
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
        .save(consumer, createSaveLocation(EXNItems.GUARDIAN_DOLL.getId()));
    ShapedRecipeBuilder.shaped(EXNItems.BEE_DOLL.asItem())
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
        .save(consumer, createSaveLocation(EXNItems.BEE_DOLL.getId()));
    ShapedRecipeBuilder.shaped(EXNItems.BLAZE_DOLL.asItem())
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
        .save(consumer, createSaveLocation(EXNItems.BLAZE_DOLL.getId()));
    ShapedRecipeBuilder.shaped(EXNItems.ENDERMAN_DOLL.asItem())
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
        .save(consumer, createSaveLocation(EXNItems.ENDERMAN_DOLL.getId()));
  }

  private void registerFluidItemRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createFluidItemRecipes(
        consumer, water, EXNBlocks.DUST.block().asItem(), Blocks.CLAY, "clay");
    createFluidItemRecipes(consumer, water, Blocks.DIRT.asItem(), Blocks.MUD, "mud");
    createFluidItemRecipes(
        consumer, lava, Tags.Items.DUSTS_REDSTONE, Blocks.NETHERRACK, NETHERRACK);
    createFluidItemRecipes(
        consumer, lava, Tags.Items.DUSTS_GLOWSTONE, Blocks.END_STONE, "end_stone");
    createFluidItemRecipes(consumer, witchwater, Tags.Items.SAND, Blocks.SOUL_SAND, "soul_sand");
    createFluidItemRecipes(consumer, witchwater, Items.COARSE_DIRT, Blocks.SOUL_SOIL, "soul_soil");
    createFluidItemRecipes(consumer, witchwater, Tags.Items.MUSHROOMS, Blocks.SLIME_BLOCK, "slime");
    createFluidItemRecipes(
        consumer,
        seawater,
        EXNItems.TUBE_CORAL_LARVA.asItem(),
        Blocks.TUBE_CORAL_BLOCK,
        "tube_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        EXNItems.FIRE_CORAL_LARVA.asItem(),
        Blocks.FIRE_CORAL_BLOCK,
        "fire_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        EXNItems.BRAIN_CORAL_LARVA.asItem(),
        Blocks.BRAIN_CORAL_BLOCK,
        "brain_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        EXNItems.BUBBLE_CORAL_LARVA.asItem(),
        Blocks.BUBBLE_CORAL_BLOCK,
        "bubble_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        EXNItems.HORN_CORAL_LARVA.asItem(),
        Blocks.HORN_CORAL_BLOCK,
        "horn_coral");
    createFluidItemRecipes(
        consumer,
        witchwater,
        EXNItems.MYCELIUM_SPORE.asItem(),
        Blocks.BROWN_MUSHROOM_BLOCK,
        "brown_mushroom");
    createFluidItemRecipes(
        consumer,
        witchwater,
        Blocks.BROWN_MUSHROOM_BLOCK.asItem(),
        Blocks.RED_MUSHROOM_BLOCK,
        "red_mushroom");
    createFluidItemRecipes(
        consumer,
        water,
        EXNBlocks.CRUSHED_DRIPSTONE.asItem(),
        Blocks.POINTED_DRIPSTONE,
        "pointed_dripstone");
  }

  private void registerFluidOnTopRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createFluidOnTopRecipes(consumer, lava, water, Blocks.OBSIDIAN, "obsidian");
    createFluidOnTopRecipes(consumer, water, lava, Blocks.COBBLESTONE, COBBLESTONE);
  }

  private void registerFluidTransformRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createFluidTransformRecipes(
        consumer, water, Blocks.MYCELIUM.asItem(), witchwater, "witch_water");
    createFluidTransformRecipes(consumer, water, Tags.Items.SAND, seawater, "sea_water");
  }

  private void registerGoldOres(@Nonnull final Consumer<FinishedRecipe> consumer) {
    @Nullable final Item oreItem = EXNItems.GOLD.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.25F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.25F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.4F))
        .build(consumer, sieveLoc(EXNItems.GOLD.getPieceId() + "_crushed_netherrack"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.075F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.15F))
        .build(consumer, sieveLoc(EXNItems.GOLD.getPieceId() + GRAVEL_SUFFIX));
  }

  private void registerHammerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createHammerRecipes(consumer, Blocks.STONE, Blocks.COBBLESTONE, COBBLESTONE);
    createHammerRecipes(consumer, Blocks.COBBLESTONE, Blocks.GRAVEL, GRAVEL);
    createHammerRecipes(consumer, Blocks.GRAVEL, Blocks.SAND, "sand");
    createHammerRecipes(consumer, Blocks.SAND, EXNBlocks.DUST.block(), "dust");
    createHammerRecipes(
        consumer, Blocks.ANDESITE, EXNBlocks.CRUSHED_ANDESITE.block(), "andesite");
    createHammerRecipes(consumer, Blocks.BASALT, EXNBlocks.CRUSHED_BASALT.block(), "basalt");
    createHammerRecipes(
        consumer, Blocks.BLACKSTONE, EXNBlocks.CRUSHED_BLACKSTONE.block(), "blackstone");
    createHammerRecipes(
        consumer, Blocks.CALCITE, EXNBlocks.CRUSHED_CALCITE.block(), "calcite");
    createHammerRecipes(
        consumer, Blocks.DEEPSLATE, EXNBlocks.CRUSHED_DEEPSLATE.block(), "deepslate");
    createHammerRecipes(
        consumer, Blocks.DIORITE, EXNBlocks.CRUSHED_DIORITE.block(), "diorite");
    createHammerRecipes(
        consumer, Blocks.DRIPSTONE_BLOCK, EXNBlocks.CRUSHED_DRIPSTONE.block(), "dripstone");
    createHammerRecipes(
        consumer, Blocks.END_STONE, EXNBlocks.CRUSHED_END_STONE.block(), "end_stone");
    createHammerRecipes(
        consumer, Blocks.GRANITE, EXNBlocks.CRUSHED_GRANITE.block(), "granite");
    createHammerRecipes(
        consumer, Blocks.NETHERRACK, EXNBlocks.CRUSHED_NETHERRACK.block(), NETHERRACK);
    createHammerRecipes(consumer, Blocks.TUFF, EXNBlocks.CRUSHED_TUFF.block(), "tuff");

    createHammerRecipes(consumer, Blocks.TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL, "tube_coral");
    createHammerRecipes(consumer, Blocks.BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL, "brain_coral");
    createHammerRecipes(consumer, Blocks.BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL, "bubble_coral");
    createHammerRecipes(consumer, Blocks.FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL, "fire_coral");
    createHammerRecipes(consumer, Blocks.HORN_CORAL_BLOCK, Blocks.HORN_CORAL, "horn_coral");
    createHammerRecipes(consumer, Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, "tube_coral_fan");
    createHammerRecipes(consumer, Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, "brain_coral_fan");
    createHammerRecipes(consumer, Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, "bubble_coral_fan");
    createHammerRecipes(consumer, Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, "fire_coral_fan");
    createHammerRecipes(consumer, Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, "horn_coral_fan");
  }

  private void registerHammers(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createHammer(EXNItems.HAMMER_ANDESITE.asItem(), Items.ANDESITE, consumer);
    createHammer(EXNItems.HAMMER_BASALT.asItem(), Items.BASALT, consumer);
    createHammer(EXNItems.HAMMER_BLACKSTONE.asItem(), Items.BLACKSTONE, consumer);
    createHammer(EXNItems.HAMMER_CALCITE.asItem(), Items.CALCITE, consumer);
    createHammer(EXNItems.HAMMER_COPPER.asItem(), Tags.Items.INGOTS_COPPER, consumer);
    //    createHammer(ExNihiloItems.HAMMER_CRIMSON_FUNGUS.asItem(), Items.CRIMSON_PLANKS,
    // consumer);
    createHammer(EXNItems.HAMMER_DEEPSLATE.asItem(), Items.DEEPSLATE, consumer);
    createHammer(EXNItems.HAMMER_DIAMOND.asItem(), Tags.Items.GEMS_DIAMOND, consumer);
    createHammer(EXNItems.HAMMER_DIORITE.asItem(), Items.DIORITE, consumer);
    createHammer(EXNItems.HAMMER_DRIPSTONE.asItem(), Items.DRIPSTONE_BLOCK, consumer);
    createHammer(EXNItems.HAMMER_GOLD.asItem(), Tags.Items.INGOTS_GOLD, consumer);
    createHammer(EXNItems.HAMMER_GRANITE.asItem(), Items.GRANITE, consumer);
    createHammer(EXNItems.HAMMER_IRON.asItem(), Tags.Items.INGOTS_IRON, consumer);
    createHammer(EXNItems.HAMMER_NETHER_BRICK.asItem(), Items.NETHER_BRICKS, consumer);
    UpgradeRecipeBuilder.smithing(
            Ingredient.of(EXNItems.HAMMER_DIAMOND.asItem()),
            Ingredient.of(Tags.Items.INGOTS_NETHERITE),
            EXNItems.HAMMER_NETHERITE.asItem())
        .unlocks(
            "has_diamond_hammer",
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.HAMMER_DIAMOND.asItem()))
        .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(
                    ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                    ExNihiloConstants.Items.NETHERITE_HAMMER)));
    //    createHammer(ExNihiloItems.HAMMER_PRISMARINE.asItem(), Tags.Items.GEMS_PRISMARINE,
    // consumer);
    createHammer(EXNItems.HAMMER_RED_NETHER_BRICK.asItem(), Items.RED_NETHER_BRICKS, consumer);
    createHammer(EXNItems.HAMMER_STONE.asItem(), Items.COBBLESTONE, consumer);
    createHammer(EXNItems.HAMMER_TERRACOTTA.asItem(), Items.TERRACOTTA, consumer);
    createHammer(EXNItems.HAMMER_TUFF.asItem(), Items.TUFF, consumer);
    //    createHammer(ExNihiloItems.HAMMER_WARPED_FUNGUS.asItem(), Items.WARPED_PLANKS, consumer);
    createHammer(EXNItems.HAMMER_WOOD.asItem(), ItemTags.PLANKS, consumer);
  }

  private void registerHeatRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createHeatRecipes(consumer, Blocks.LAVA, 3, "lava");
    createHeatRecipes(consumer, Blocks.FIRE, 4, "fire");
    createHeatRecipes(consumer, Blocks.TORCH, 1, "torch");
    createHeatRecipes(consumer, Blocks.WALL_TORCH, 1, "wall_torch");
    createHeatRecipes(consumer, Blocks.MAGMA_BLOCK, 2, "magma_block");
    createHeatRecipes(consumer, Blocks.GLOWSTONE, 2, "glowstone");
    createHeatRecipes(consumer, Blocks.SHROOMLIGHT, 2, "shroomlight");
    createHeatRecipes(consumer, Blocks.SOUL_FIRE, 4, "soul_fire");

    // Lit blocks
    StatePropertiesPredicate lit =
        StatePropertiesPredicate.Builder.properties()
            .hasProperty(BlockStateProperties.LIT, true)
            .build();
    createHeatRecipes(consumer, Blocks.CAMPFIRE, 4, lit, "campfire");
    createHeatRecipes(consumer, Blocks.SOUL_CAMPFIRE, 4, lit, "soul_campfire");
    createHeatRecipes(consumer, Blocks.FURNACE, 3, lit, "furnace");
    createHeatRecipes(consumer, Blocks.REDSTONE_TORCH, 1, lit, "redstone_torch");
    createHeatRecipes(consumer, Blocks.REDSTONE_WALL_TORCH, 1, lit, "redstone_wall_torch");
  }

  private void registerIronOres(@Nonnull final Consumer<FinishedRecipe> consumer) {
    @Nullable final Item oreItem = EXNItems.IRON.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.25F))
        .build(consumer, sieveLoc(EXNItems.IRON.getPieceId() + GRAVEL_SUFFIX));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.5F))
        .build(consumer, sieveLoc(EXNItems.IRON.getPieceId() + "_sand"));
  }

  private void registerMeshes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createMesh(
        EXNItems.MESH_FLINT.asItem(),
        EXNItems.MESH_STRING.asItem(),
        Items.FLINT,
        consumer);
    createMesh(
        EXNItems.MESH_IRON.asItem(),
        EXNItems.MESH_FLINT.asItem(),
        Tags.Items.INGOTS_IRON,
        consumer);
    createMesh(
        EXNItems.MESH_DIAMOND.asItem(),
        EXNItems.MESH_IRON.asItem(),
        Tags.Items.GEMS_DIAMOND,
        consumer);
    createMesh(
        EXNItems.MESH_EMERALD.asItem(),
        EXNItems.MESH_DIAMOND.asItem(),
        Tags.Items.GEMS_EMERALD,
        consumer);
    UpgradeRecipeBuilder.smithing(
            Ingredient.of(EXNItems.MESH_EMERALD.asItem()),
            Ingredient.of(Tags.Items.INGOTS_NETHERITE),
            EXNItems.MESH_NETHERITE.asItem())
        .unlocks(
            "has_emerald_mesh",
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.MESH_EMERALD.asItem()))
        .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(
                    ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                    ExNihiloConstants.Items.NETHERITE_MESH)));

    ShapedRecipeBuilder.shaped(EXNItems.MESH_STRING.asItem())
        .pattern("iii")
        .pattern("iii")
        .pattern("iii")
        .define('i', Tags.Items.STRING)
        .unlockedBy(
            "has_sieve",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                EXNBlocks.OAK_SIEVE,
                EXNBlocks.ACACIA_SIEVE,
                EXNBlocks.BIRCH_SIEVE,
                EXNBlocks.JUNGLE_SIEVE,
                EXNBlocks.MANGROVE_SIEVE,
                EXNBlocks.DARK_OAK_SIEVE,
                EXNBlocks.SPRUCE_SIEVE,
                EXNBlocks.CRIMSON_SIEVE,
                EXNBlocks.WARPED_SIEVE))
        .save(consumer, createSaveLocation(EXNItems.MESH_STRING.getId()));
  }

  private void registerMisc(@Nonnull final Consumer<FinishedRecipe> consumer) {
    @Nullable final ResourceLocation beehive = ForgeRegistries.BLOCKS.getKey(Blocks.BEEHIVE);
    if (beehive != null) {
      ShapedRecipeBuilder.shaped(Blocks.BEEHIVE)
          .pattern("xxx")
          .pattern("fff")
          .pattern("xxx")
          .define('x', ItemTags.PLANKS)
          .define('f', EXNItems.BEEHIVE_FRAME.asItem())
          .unlockedBy(
              "has_frame",
              InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.BEEHIVE_FRAME.asItem()))
          .save(consumer, createSaveLocation(beehive));
    }

    ShapedRecipeBuilder.shaped(EXNItems.BEEHIVE_FRAME.asItem())
        .pattern("xxx")
        .pattern("xfx")
        .pattern("xxx")
        .define('x', Tags.Items.RODS_WOODEN)
        .define('f', Tags.Items.STRING)
        .unlockedBy(
            "has_stick",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(Tags.Items.RODS_WOODEN).build()))
        .unlockedBy(
            "has_string",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(Tags.Items.STRING).build()))
        .save(consumer, createSaveLocation(EXNItems.BEEHIVE_FRAME.getId()));

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
    ShapedRecipeBuilder.shaped(EXNBlocks.UNFIRED_CRUCIBLE)
        .pattern("c c")
        .pattern("c c")
        .pattern("ccc")
        .define('c', EXNItems.PORCELAIN_CLAY.asItem())
        .unlockedBy(
            PORCELAIN_CLAY_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(EXNItems.PORCELAIN_CLAY.asItem()))
        .save(consumer, createSaveLocation(EXNBlocks.UNFIRED_CRUCIBLE.getId()));

    ShapedRecipeBuilder.shaped(EXNItems.CRAFTING_DOLL.asItem(), 4)
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
                ItemPredicate.Builder.item().of(Tags.Items.GEMS_DIAMOND).build()))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x4")));

    ShapedRecipeBuilder.shaped(EXNItems.CRAFTING_DOLL.asItem(), 6)
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
                ItemPredicate.Builder.item().of(Tags.Items.GEMS_EMERALD).build()))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x6")));

    ShapedRecipeBuilder.shaped(EXNBlocks.END_CAKE)
        .pattern("ece")
        .pattern("eke")
        .pattern("ece")
        .define('e', Items.ENDER_EYE)
        .define('c', Items.END_CRYSTAL)
        .define('k', Items.CAKE)
        .unlockedBy(
            "has_ender_pearl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENDER_PEARL))
        .save(consumer, createSaveLocation(EXNBlocks.END_CAKE.getId()));

    ShapelessRecipeBuilder.shapeless(EXNItems.PORCELAIN_CLAY.asItem())
        .requires(ExNihiloTags.CLAY)
        .requires(Items.BONE_MEAL)
        .unlockedBy(
            "has_clay",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(ExNihiloTags.CLAY).build()))
        .save(consumer, createSaveLocation(EXNItems.PORCELAIN_CLAY.getId()));

    @Nullable final ItemDefinition<PebbleItem> blackstonePebble = EXNItems.PEBBLE_BLACKSTONE;
    @Nullable
    final ResourceLocation gildedBlackstoneResourceLocation =
        ForgeRegistries.BLOCKS.getKey(Blocks.GILDED_BLACKSTONE);
    if (EXNItems.GOLD.getRawOreItem() != null && gildedBlackstoneResourceLocation != null) {
      ShapedRecipeBuilder.shaped(Blocks.GILDED_BLACKSTONE)
          .pattern("xxx")
          .pattern("xgx")
          .pattern("xxx")
          .define('x', blackstonePebble.asItem())
          .define('g', Tags.Items.RAW_MATERIALS_GOLD)
          .unlockedBy("has_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
          .save(consumer, createSaveLocation(gildedBlackstoneResourceLocation));
    }
    @Nullable
    final ResourceLocation cryingObsidianResourceLocation =
        ForgeRegistries.BLOCKS.getKey(Blocks.CRYING_OBSIDIAN);
    if (cryingObsidianResourceLocation != null) {
      ShapedRecipeBuilder.shaped(Blocks.CRYING_OBSIDIAN)
          .pattern(" o ")
          .pattern("obo")
          .pattern(" o ")
          .define('b', Items.WATER_BUCKET)
          .define('o', Blocks.OBSIDIAN)
          .unlockedBy(
              "has_obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OBSIDIAN))
          .save(consumer, createSaveLocation(cryingObsidianResourceLocation));
    }
    @Nullable
    final ResourceLocation ancientDebrisResourceLocation =
        ForgeRegistries.BLOCKS.getKey(Blocks.ANCIENT_DEBRIS);
    if (ancientDebrisResourceLocation != null) {
      ShapelessRecipeBuilder.shapeless(Blocks.ANCIENT_DEBRIS)
          .requires(Items.NETHERITE_SCRAP)
          .requires(Blocks.OBSIDIAN)
          .unlockedBy(
              "has_scrap", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SCRAP))
          .save(consumer, createSaveLocation(ancientDebrisResourceLocation));
    }
  }

  private void registerOres(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createOre(EXNItems.IRON, consumer);
    createOre(EXNItems.GOLD, consumer);
    createOre(EXNItems.COPPER, consumer);
    createOreRecipes(
        EXNItems.LEAD,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.LEAD.getIngotId()),
        consumer);
    createOreRecipes(
        EXNItems.NICKEL,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.NICKEL.getIngotId()),
        consumer);
    createOreRecipes(
        EXNItems.SILVER,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.SILVER.getIngotId()),
        consumer);
    createOreRecipes(
        EXNItems.TIN,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.TIN.getIngotId()),
        consumer);
    createOreRecipes(
        EXNItems.ALUMINUM,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.ALUMINUM.getIngotId()),
        consumer);
    createOreRecipes(
        EXNItems.PLATINUM,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.PLATINUM.getIngotId()),
        consumer);
    createOreRecipes(
        EXNItems.URANIUM,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.URANIUM.getIngotId()),
        consumer);
    createOreRecipes(
        EXNItems.ZINC,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EXNItems.ZINC.getIngotId()),
        consumer);
  }

  private void registerPebbleBlocks(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createPebbleBlock(Blocks.ANDESITE, EXNItems.PEBBLE_ANDESITE.asItem(), consumer);
    createPebbleBlock(Blocks.BASALT, EXNItems.PEBBLE_BASALT.asItem(), consumer);
    createPebbleBlock(Blocks.BLACKSTONE, EXNItems.PEBBLE_BLACKSTONE.asItem(), consumer);
    createPebbleBlock(Blocks.COBBLESTONE, EXNItems.PEBBLE_STONE.asItem(), consumer);
    createPebbleBlock(Blocks.CALCITE, EXNItems.PEBBLE_CALCITE.asItem(), consumer);
    createPebbleBlock(Blocks.DEEPSLATE, EXNItems.PEBBLE_DEEPSLATE.asItem(), consumer);
    createPebbleBlock(Blocks.DIORITE, EXNItems.PEBBLE_DIORITE.asItem(), consumer);
    createPebbleBlock(Blocks.DRIPSTONE_BLOCK, EXNItems.PEBBLE_DRIPSTONE.asItem(), consumer);
    createPebbleBlock(Blocks.END_STONE, EXNItems.PEBBLE_END_STONE.asItem(), consumer);
    createPebbleBlock(Blocks.GRANITE, EXNItems.PEBBLE_GRANITE.asItem(), consumer);
    createPebbleBlock(Blocks.NETHERRACK, EXNItems.PEBBLE_NETHERRACK.asItem(), consumer);
    createPebbleBlock(Blocks.TUFF, EXNItems.PEBBLE_TUFF.asItem(), consumer);
  }

  private void registerSieveRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    // TODO: Add all pebble drops
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_STONE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_stone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_ANDESITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_andesite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_DIORITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_diorite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_GRANITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_granite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_BASALT.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_basalt"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_BLACKSTONE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_blackstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_CALCITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_calcite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_DEEPSLATE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_deepslate"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_DRIPSTONE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_dripstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_END_STONE))
        .addResult(EXNItems.PEBBLE_END_STONE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_end_stone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK))
        .addResult(EXNItems.PEBBLE_NETHERRACK.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_netherrack"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.PEBBLE_TUFF.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_tuff"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.WHEAT_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.7F))
        .build(consumer, sieveLoc("seed_wheat"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.MELON_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.35F))
        .build(consumer, sieveLoc("seed_melon"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.PUMPKIN_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.35F))
        .build(consumer, sieveLoc("seed_pumpkin"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.BEETROOT_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.35F))
        .build(consumer, sieveLoc("seed_beetroot"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.MYCELIUM_SPORE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, sieveLoc("mycelium_spore"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK))
        .addResult(EXNItems.CRIMSON_NYLIUM_SPORE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, sieveLoc("crimson_nylium_spore"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK))
        .addResult(EXNItems.WARPED_NYLIUM_SPORE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, sieveLoc("warped_nylium_spore"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(EXNItems.GRASS_SEED.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, sieveLoc("seed_grass"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.MUD))
        .addResult(Items.MANGROVE_PROPAGULE)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.03F))
        .isWaterlogged()
        .build(consumer, sieveLoc("mangrove_propagule"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(Items.COCOA_BEANS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.03F))
        .build(consumer, sieveLoc("cocoa_beans"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(Items.PRISMARINE_SHARD)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.02F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.04F))
        .isWaterlogged()
        .build(consumer, sieveLoc("prismarine_shard"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(Items.PRISMARINE_CRYSTALS)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01F))
        .isWaterlogged()
        .build(consumer, sieveLoc("prismarine_crystals"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.FLINT)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.25F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.25F))
        .build(consumer, sieveLoc("flint"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.COAL)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.125F))
        .build(consumer, sieveLoc("coal"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.LAPIS_LAZULI)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .build(consumer, sieveLoc("lapis_lazuli"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.DIAMOND)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.008F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.016F))
        .build(consumer, sieveLoc("diamond"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK))
        .addResult(Items.NETHERITE_SCRAP)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.004F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.008F))
        .build(consumer, sieveLoc("netherite_scrap"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.EMERALD)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.008F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.016F))
        .build(consumer, sieveLoc("emerald"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SOUL_SAND))
        .addResult(Items.QUARTZ)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.33F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.8F))
        .build(consumer, sieveLoc("quartz"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SOUL_SAND))
        .addResult(Items.NETHER_WART)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, sieveLoc("nether_wart"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SOUL_SAND))
        .addResult(Items.GHAST_TEAR)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.02F))
        .build(consumer, sieveLoc("ghast_tear"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.DUST))
        .addResult(Items.BONE_MEAL)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.2F))
        .build(consumer, sieveLoc("bone_meal"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.DUST))
        .addResult(Items.GUNPOWDER)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.07F))
        .build(consumer, sieveLoc("gunpowder"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.DUST))
        .addResult(Items.REDSTONE)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.125F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.25F))
        .build(consumer, sieveLoc("redstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.DUST))
        .addResult(Items.GLOWSTONE_DUST)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.0625F))
        .build(consumer, sieveLoc("glowstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.DUST))
        .addResult(Items.BLAZE_POWDER)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, sieveLoc("blaze_powder"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_END_STONE))
        .addResult(Items.ENDER_PEARL)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.005F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01F))
        .addRoll(new MeshWithChance(EXNItems.MESH_EMERALD.asItem().getType(), 0.015F))
        .addRoll(new MeshWithChance(EXNItems.MESH_NETHERITE.asItem().getType(), 0.02F))
        .build(consumer, sieveLoc("ender_pearl"));

    registerIronOres(consumer);
    registerGoldOres(consumer);
    registerDefaultOres(consumer, EXNItems.COPPER);
    registerDefaultOres(consumer, EXNItems.LEAD);
    registerDefaultOres(consumer, EXNItems.NICKEL);
    registerDefaultOres(consumer, EXNItems.SILVER);
    registerDefaultOres(consumer, EXNItems.TIN);
    registerDefaultOres(consumer, EXNItems.ALUMINUM);
    registerDefaultOres(consumer, EXNItems.PLATINUM);
    registerDefaultOres(consumer, EXNItems.URANIUM);
    registerDefaultOres(consumer, EXNItems.ZINC);

    createSeeds(Items.DARK_OAK_SAPLING, consumer);
    createSeeds(Items.SPRUCE_SAPLING, consumer);
    createSeeds(Items.BIRCH_SAPLING, consumer);
    createSeeds(Items.JUNGLE_SAPLING, consumer);
    createSeeds(Items.ACACIA_SAPLING, consumer);
    createSeeds(Items.OAK_SAPLING, consumer);
    createSeeds(Items.CARROT, consumer);
    createSeeds(Items.POTATO, consumer);
    createSeeds(Items.SWEET_BERRIES, consumer);
    createSeeds(Items.BAMBOO, consumer);
    createSeeds(Items.FERN, consumer);
    createSeeds(Items.LARGE_FERN, consumer);
    createSeeds(Items.CACTUS, consumer, Blocks.SAND);
    createSeeds(Items.SUGAR_CANE, consumer, Blocks.SAND);
    createWaterSeeds(Items.KELP, consumer);
    createWaterSeeds(Items.SEA_PICKLE, consumer);

    SieveRecipeBuilder.builder()
        .input(Ingredient.of(EXNBlocks.CRUSHED_END_STONE))
        .addResult(Items.CHORUS_FLOWER)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01f))
        .addRoll(new MeshWithChance(EXNItems.MESH_EMERALD.asItem().getType(), 0.02f))
        .addRoll(new MeshWithChance(EXNItems.MESH_NETHERITE.asItem().getType(), 0.05f))
        .build(consumer, sieveLoc("chorus_flower"));

    getLeavesSaplings()
        .forEach(
            (input, drop) -> {
              @Nullable
              final ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(input);
              if (resourceLocation != null) {
                if (Objects.equals(
                    ForgeRegistries.BLOCKS.getKey(input), new ResourceLocation("jungle_leaves"))) {
                  SieveRecipeBuilder.builder()
                      .input(Ingredient.of(input))
                      .addResult(drop)
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.025F))
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.075F))
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.1F))
                      .build(consumer, sieveLoc(resourceLocation.getPath()));
                } else {
                  SieveRecipeBuilder.builder()
                      .input(Ingredient.of(input))
                      .addResult(drop)
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
                      .addRoll(
                          new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
                      .build(consumer, sieveLoc(resourceLocation.getPath()));
                }
              }
            });
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.LEAVES))
        .addResult(Items.APPLE)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
        .build(consumer, sieveLoc("apple"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.LEAVES))
        .addResult(Items.GOLDEN_APPLE)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.001F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.003F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.005F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01F))
        .build(consumer, sieveLoc("golden_apple"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.LEAVES))
        .addResult(EXNItems.SILKWORM.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.025F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
        .build(consumer, sieveLoc(SILKWORM));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(EXNItems.TUBE_CORAL_LARVA.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_blue_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(EXNItems.BUBBLE_CORAL_LARVA.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_purple_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(EXNItems.BRAIN_CORAL_LARVA.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_pink_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(EXNItems.HORN_CORAL_LARVA.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_yellow_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(EXNItems.FIRE_CORAL_LARVA.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_red_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(Items.SEAGRASS)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seagrass"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.COARSE_DIRT))
        .addResult(Items.DIRT)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.4F))
        .build(consumer, sieveLoc("dirt"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.COARSE_DIRT))
        .addResult(Items.GRAVEL)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.4F))
        .build(consumer, sieveLoc(GRAVEL));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.AMETHYST_SHARD)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
        .build(consumer, sieveLoc("amethyst_shard"));
  }

  private void registerSieves(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createSieve(consumer, EXNBlocks.ACACIA_SIEVE, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
    createSieve(consumer, EXNBlocks.BIRCH_SIEVE, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
    createSieve(
        consumer, EXNBlocks.DARK_OAK_SIEVE, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
    createSieve(consumer, EXNBlocks.JUNGLE_SIEVE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
    createSieve(
        consumer, EXNBlocks.MANGROVE_SIEVE, Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB);
    createSieve(consumer, EXNBlocks.OAK_SIEVE, Items.OAK_PLANKS, Items.OAK_SLAB);
    createSieve(consumer, EXNBlocks.SPRUCE_SIEVE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
    createSieve(consumer, EXNBlocks.CRIMSON_SIEVE, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
    createSieve(consumer, EXNBlocks.WARPED_SIEVE, Items.WARPED_PLANKS, Items.WARPED_SLAB);
  }
}
