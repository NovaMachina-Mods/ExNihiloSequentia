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
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipeBuilder;
import novamachina.exnihilosequentia.common.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.PebbleItem;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractRecipeGenerator;

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
  @Nonnull private static final Fluid seawater = ExNihiloFluids.SEA_WATER.get();
  @Nonnull private static final Fluid water = Fluids.WATER;
  @Nonnull private static final Fluid witchwater = ExNihiloFluids.WITCH_WATER.get();

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
    createBarrel(consumer, ExNihiloBlocks.BARREL_STONE, Tags.Items.STONE, Items.STONE_SLAB);
    createBarrel(consumer, ExNihiloBlocks.BARREL_ACACIA, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
    createBarrel(consumer, ExNihiloBlocks.BARREL_BIRCH, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
    createBarrel(
        consumer, ExNihiloBlocks.BARREL_DARK_OAK, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
    createBarrel(consumer, ExNihiloBlocks.BARREL_JUNGLE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
    createBarrel(consumer, ExNihiloBlocks.BARREL_OAK, Items.OAK_PLANKS, Items.OAK_SLAB);
    createBarrel(consumer, ExNihiloBlocks.BARREL_SPRUCE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
    createBarrel(consumer, ExNihiloBlocks.BARREL_CRIMSON, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
    createBarrel(consumer, ExNihiloBlocks.BARREL_WARPED, Items.WARPED_PLANKS, Items.WARPED_SLAB);
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
    createCompostRecipe(consumer, ExNihiloItems.SILKWORM.get(), 40, SILKWORM);
    createCompostRecipe(consumer, ExNihiloItems.COOKED_SILKWORM.get(), 40, "cooked_silkworm");
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
    createCompostRecipe(consumer, ExNihiloItems.GRASS_SEED.get(), 100, "grass_seed");
    createCompostRecipe(consumer, ExNihiloItems.MYCELIUM_SPORE.get(), 100, "mycelium_spore");
    createCompostRecipe(
        consumer, ExNihiloItems.CRIMSON_NYLIUM_SPORE.get(), 100, "crimson_nylium_spore");
    createCompostRecipe(
        consumer, ExNihiloItems.WARPED_NYLIUM_SPORE.get(), 100, "warped_nylium_spore");
    createCompostRecipe(consumer, Items.SWEET_BERRIES, 100, "sweet_berries");
  }

  private void registerCrookRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createCrookRecipes(consumer, ItemTags.LEAVES, ExNihiloItems.SILKWORM.get(), 0.1F, LEAVES);
    createCrookRecipes(
        consumer,
        ExNihiloBlocks.INFESTED_LEAVES.get(),
        ExNihiloItems.SILKWORM.get(),
        0.2F,
        SILKWORM);
    createCrookRecipes(
        consumer, ExNihiloBlocks.INFESTED_LEAVES.get(), Items.STRING, 0.5F, "string");
  }

  private void registerCrooks(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createCrook(ExNihiloItems.CROOK_ANDESITE.get(), ExNihiloItems.PEBBLE_ANDESITE.get(), consumer);
    createCrook(ExNihiloItems.CROOK_BASALT.get(), ExNihiloItems.PEBBLE_BASALT.get(), consumer);
    createCrook(
        ExNihiloItems.CROOK_BLACKSTONE.get(), ExNihiloItems.PEBBLE_BLACKSTONE.get(), consumer);
    createCrook(ExNihiloItems.CROOK_BONE.get(), Tags.Items.BONES, consumer);
    createCrook(ExNihiloItems.CROOK_CALCITE.get(), ExNihiloItems.PEBBLE_CALCITE.get(), consumer);
    createCrook(ExNihiloItems.CROOK_COPPER.get(), ExNihiloTags.NUGGET_COPPER, consumer);
    //    createCrook(ExNihiloItems.CROOK_CRIMSON_FUNGUS.get(), Items.CRIMSON_PLANKS, consumer);
    createCrook(
        ExNihiloItems.CROOK_DEEPSLATE.get(), ExNihiloItems.PEBBLE_DEEPSLATE.get(), consumer);
    createCrook(ExNihiloItems.CROOK_DIAMOND.get(), Tags.Items.GEMS_DIAMOND, consumer);
    createCrook(ExNihiloItems.CROOK_DIORITE.get(), ExNihiloItems.PEBBLE_DIORITE.get(), consumer);
    createCrook(
        ExNihiloItems.CROOK_DRIPSTONE.get(), ExNihiloItems.PEBBLE_DRIPSTONE.get(), consumer);
    createCrook(ExNihiloItems.CROOK_GOLD.get(), Tags.Items.NUGGETS_GOLD, consumer);
    createCrook(ExNihiloItems.CROOK_GRANITE.get(), ExNihiloItems.PEBBLE_GRANITE.get(), consumer);
    createCrook(ExNihiloItems.CROOK_IRON.get(), Tags.Items.NUGGETS_IRON, consumer);
    createCrook(ExNihiloItems.CROOK_NETHER_BRICK.get(), Items.NETHER_BRICKS, consumer);
    UpgradeRecipeBuilder.smithing(
            Ingredient.of(ExNihiloItems.CROOK_DIAMOND.get()),
            Ingredient.of(Tags.Items.INGOTS_NETHERITE),
            ExNihiloItems.CROOK_NETHERITE.get())
        .unlocks(
            "has_diamond_crook",
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CROOK_DIAMOND.get()))
        .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(
                    ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                    ExNihiloConstants.Items.NETHERITE_CROOK)));
    createCrook(ExNihiloItems.CROOK_RED_NETHER_BRICK.get(), Items.RED_NETHER_BRICKS, consumer);
    createCrook(ExNihiloItems.CROOK_PRISMARINE.get(), Tags.Items.GEMS_PRISMARINE, consumer);
    createCrook(ExNihiloItems.CROOK_STONE.get(), ExNihiloItems.PEBBLE_STONE.get(), consumer);
    createCrook(ExNihiloItems.CROOK_TERRACOTTA.get(), Items.TERRACOTTA, consumer);
    createCrook(ExNihiloItems.CROOK_TUFF.get(), ExNihiloItems.PEBBLE_TUFF.get(), consumer);
    //    createCrook(ExNihiloItems.CROOK_WARPED_FUNGUS.get(), Items.WARPED_PLANKS, consumer);
    createCrook(ExNihiloItems.CROOK_WOOD.get(), Tags.Items.RODS_WOODEN, consumer);
  }

  private void registerCrucibleRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createFiredCrucibleRecipes(consumer, Blocks.COBBLESTONE, 250, COBBLESTONE);
    createFiredCrucibleRecipes(consumer, Blocks.DIORITE, 250, "diorite");
    createFiredCrucibleRecipes(consumer, Blocks.ANDESITE, 250, "andesite");
    createFiredCrucibleRecipes(consumer, Blocks.GRANITE, 250, "granite");
    createFiredCrucibleRecipes(consumer, Blocks.STONE, 250, "stone");
    createFiredCrucibleRecipes(consumer, Blocks.GRAVEL, 200, GRAVEL);
    createFiredCrucibleRecipes(
        consumer, ExNihiloBlocks.CRUSHED_ANDESITE.get(), 200, "crushed_andesite");
    createFiredCrucibleRecipes(
        consumer, ExNihiloBlocks.CRUSHED_DIORITE.get(), 200, "crushed_diorite");
    createFiredCrucibleRecipes(
        consumer, ExNihiloBlocks.CRUSHED_END_STONE.get(), 200, "crushed_end_stone");
    createFiredCrucibleRecipes(
        consumer, ExNihiloBlocks.CRUSHED_GRANITE.get(), 200, "crushed_granite");
    createFiredCrucibleRecipes(
        consumer, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), 200, "crushed_netherrack");
    createFiredCrucibleRecipes(consumer, Blocks.SAND, 100, "sand");
    createFiredCrucibleRecipes(consumer, ExNihiloBlocks.DUST.get(), 50, "dust");
    createFiredCrucibleRecipes(consumer, Blocks.NETHERRACK, 1000, NETHERRACK);
    createFiredCrucibleRecipes(consumer, Blocks.OBSIDIAN, 1000, "obsidian");

    createWaterCrucibleRecipes(consumer, ItemTags.SAPLINGS, 250, "saplings");
    createWaterCrucibleRecipes(consumer, ItemTags.LEAVES, 250, LEAVES);
  }

  private void registerCrucibles(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_ACACIA, Items.ACACIA_LOG, Items.ACACIA_SLAB);
    createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_BIRCH, Items.BIRCH_LOG, Items.BIRCH_SLAB);
    createCrucible(
        consumer, ExNihiloBlocks.CRUCIBLE_DARK_OAK, Items.DARK_OAK_LOG, Items.DARK_OAK_SLAB);
    createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_JUNGLE, Items.JUNGLE_LOG, Items.JUNGLE_SLAB);
    createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_OAK, Items.OAK_LOG, Items.OAK_SLAB);
    createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_SPRUCE, Items.SPRUCE_LOG, Items.SPRUCE_SLAB);
    createCrucible(
        consumer, ExNihiloBlocks.CRUCIBLE_CRIMSON, Items.CRIMSON_STEM, Items.CRIMSON_SLAB);
    createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_WARPED, Items.WARPED_STEM, Items.WARPED_SLAB);
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
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.075F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.1F))
        .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
  }

  private void registerDolls(@Nonnull final Consumer<FinishedRecipe> consumer) {
    ShapedRecipeBuilder.shaped(ExNihiloItems.SHULKER_DOLL.get())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.DYES_PURPLE)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.END_STONES)
        .define('b', Tags.Items.ENDER_PEARLS)
        .define('m', ExNihiloItems.CRAFTING_DOLL.get())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
        .save(consumer, createSaveLocation(ExNihiloItems.SHULKER_DOLL.getId()));
    ShapedRecipeBuilder.shaped(ExNihiloItems.GUARDIAN_DOLL.get())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.GEMS_PRISMARINE)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.DUSTS_REDSTONE)
        .define('b', ItemTags.FISHES)
        .define('m', ExNihiloItems.CRAFTING_DOLL.get())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
        .save(consumer, createSaveLocation(ExNihiloItems.GUARDIAN_DOLL.getId()));
    ShapedRecipeBuilder.shaped(ExNihiloItems.BEE_DOLL.get())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.DYES_YELLOW)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', ItemTags.FLOWERS)
        .define('b', ExNihiloItems.BEEHIVE_FRAME.get())
        .define('m', ExNihiloItems.CRAFTING_DOLL.get())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
        .save(consumer, createSaveLocation(ExNihiloItems.BEE_DOLL.getId()));
    ShapedRecipeBuilder.shaped(ExNihiloItems.BLAZE_DOLL.get())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Items.BLAZE_POWDER)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.DUSTS_REDSTONE)
        .define('b', Tags.Items.CROPS_NETHER_WART)
        .define('m', ExNihiloItems.CRAFTING_DOLL.get())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
        .save(consumer, createSaveLocation(ExNihiloItems.BLAZE_DOLL.getId()));
    ShapedRecipeBuilder.shaped(ExNihiloItems.ENDERMAN_DOLL.get())
        .pattern("ctc")
        .pattern("sms")
        .pattern("cbc")
        .define('c', Tags.Items.DYES_BLACK)
        .define('s', Tags.Items.DUSTS_GLOWSTONE)
        .define('t', Tags.Items.DUSTS_REDSTONE)
        .define('b', Tags.Items.CROPS_NETHER_WART)
        .define('m', ExNihiloItems.CRAFTING_DOLL.get())
        .unlockedBy(
            DOLL_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
        .save(consumer, createSaveLocation(ExNihiloItems.ENDERMAN_DOLL.getId()));
  }

  private void registerFluidItemRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createFluidItemRecipes(
        consumer, water, ExNihiloBlocks.DUST.get().asItem(), Blocks.CLAY, "clay");
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
        ExNihiloItems.TUBE_CORAL_LARVA.get(),
        Blocks.TUBE_CORAL_BLOCK,
        "tube_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        ExNihiloItems.FIRE_CORAL_LARVA.get(),
        Blocks.FIRE_CORAL_BLOCK,
        "fire_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        ExNihiloItems.BRAIN_CORAL_LARVA.get(),
        Blocks.BRAIN_CORAL_BLOCK,
        "brain_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        ExNihiloItems.BUBBLE_CORAL_LARVA.get(),
        Blocks.BUBBLE_CORAL_BLOCK,
        "bubble_coral");
    createFluidItemRecipes(
        consumer,
        seawater,
        ExNihiloItems.HORN_CORAL_LARVA.get(),
        Blocks.HORN_CORAL_BLOCK,
        "horn_coral");
    createFluidItemRecipes(
        consumer,
        witchwater,
        ExNihiloItems.MYCELIUM_SPORE.get(),
        Blocks.BROWN_MUSHROOM_BLOCK,
        "brown_mushroom");
    createFluidItemRecipes(
        consumer,
        witchwater,
        Blocks.BROWN_MUSHROOM_BLOCK.asItem(),
        Blocks.RED_MUSHROOM_BLOCK,
        "red_mushroom");
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
    @Nullable final Item oreItem = ExNihiloItems.GOLD.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.25F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.25F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.4F))
        .build(consumer, sieveLoc(ExNihiloItems.GOLD.getPieceName() + "_crushed_netherrack"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.075F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.15F))
        .build(consumer, sieveLoc(ExNihiloItems.GOLD.getPieceName() + GRAVEL_SUFFIX));
  }

  private void registerHammerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createHammerRecipes(consumer, Blocks.STONE, Blocks.COBBLESTONE, COBBLESTONE);
    createHammerRecipes(consumer, Blocks.COBBLESTONE, Blocks.GRAVEL, GRAVEL);
    createHammerRecipes(consumer, Blocks.GRAVEL, Blocks.SAND, "sand");
    createHammerRecipes(consumer, Blocks.SAND, ExNihiloBlocks.DUST.get(), "dust");
    createHammerRecipes(
        consumer, Blocks.NETHERRACK, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), NETHERRACK);
    createHammerRecipes(
        consumer, Blocks.ANDESITE, ExNihiloBlocks.CRUSHED_ANDESITE.get(), "andesite");
    createHammerRecipes(consumer, Blocks.DIORITE, ExNihiloBlocks.CRUSHED_DIORITE.get(), "diorite");
    createHammerRecipes(consumer, Blocks.GRANITE, ExNihiloBlocks.CRUSHED_GRANITE.get(), "granite");
    createHammerRecipes(
        consumer, Blocks.END_STONE, ExNihiloBlocks.CRUSHED_END_STONE.get(), "end_stone");
    HammerRecipeBuilder.builder().input(Blocks.DRIPSTONE_BLOCK).addDrop(Blocks.POINTED_DRIPSTONE, 2)
            .build(consumer, hammerLoc("pointed_dripstone"));

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
    createHammer(ExNihiloItems.HAMMER_ANDESITE.get(), Items.ANDESITE, consumer);
    createHammer(ExNihiloItems.HAMMER_BASALT.get(), Items.BASALT, consumer);
    createHammer(ExNihiloItems.HAMMER_BLACKSTONE.get(), Items.BLACKSTONE, consumer);
    createHammer(ExNihiloItems.HAMMER_CALCITE.get(), Items.CALCITE, consumer);
    createHammer(ExNihiloItems.HAMMER_COPPER.get(), Tags.Items.INGOTS_COPPER, consumer);
    //    createHammer(ExNihiloItems.HAMMER_CRIMSON_FUNGUS.get(), Items.CRIMSON_PLANKS, consumer);
    createHammer(ExNihiloItems.HAMMER_DEEPSLATE.get(), Items.DEEPSLATE, consumer);
    createHammer(ExNihiloItems.HAMMER_DIAMOND.get(), Tags.Items.GEMS_DIAMOND, consumer);
    createHammer(ExNihiloItems.HAMMER_DIORITE.get(), Items.DIORITE, consumer);
    createHammer(ExNihiloItems.HAMMER_DRIPSTONE.get(), Items.DRIPSTONE_BLOCK, consumer);
    createHammer(ExNihiloItems.HAMMER_GOLD.get(), Tags.Items.INGOTS_GOLD, consumer);
    createHammer(ExNihiloItems.HAMMER_GRANITE.get(), Items.GRANITE, consumer);
    createHammer(ExNihiloItems.HAMMER_IRON.get(), Tags.Items.INGOTS_IRON, consumer);
    createHammer(ExNihiloItems.HAMMER_NETHER_BRICK.get(), Items.NETHER_BRICKS, consumer);
    UpgradeRecipeBuilder.smithing(
            Ingredient.of(ExNihiloItems.HAMMER_DIAMOND.get()),
            Ingredient.of(Tags.Items.INGOTS_NETHERITE),
            ExNihiloItems.HAMMER_NETHERITE.get())
        .unlocks(
            "has_diamond_hammer",
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.HAMMER_DIAMOND.get()))
        .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(
                    ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                    ExNihiloConstants.Items.NETHERITE_HAMMER)));
    createHammer(ExNihiloItems.HAMMER_PRISMARINE.get(), Tags.Items.GEMS_PRISMARINE, consumer);
    createHammer(ExNihiloItems.HAMMER_RED_NETHER_BRICK.get(), Items.RED_NETHER_BRICKS, consumer);
    createHammer(ExNihiloItems.HAMMER_STONE.get(), Tags.Items.COBBLESTONE, consumer);
    createHammer(ExNihiloItems.HAMMER_TERRACOTTA.get(), Items.TERRACOTTA, consumer);
    createHammer(ExNihiloItems.HAMMER_TUFF.get(), Items.TUFF, consumer);
    //    createHammer(ExNihiloItems.HAMMER_WARPED_FUNGUS.get(), Items.WARPED_PLANKS, consumer);
    createHammer(ExNihiloItems.HAMMER_WOOD.get(), ItemTags.PLANKS, consumer);
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
    @Nullable final Item oreItem = ExNihiloItems.IRON.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.1F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.15F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.25F))
        .build(consumer, sieveLoc(ExNihiloItems.IRON.getPieceName() + GRAVEL_SUFFIX));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(oreItem)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.5F))
        .build(consumer, sieveLoc(ExNihiloItems.IRON.getPieceName() + "_sand"));
  }

  private void registerMeshes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createMesh(
        ExNihiloItems.MESH_FLINT.get(), ExNihiloItems.MESH_STRING.get(), Items.FLINT, consumer);
    createMesh(
        ExNihiloItems.MESH_IRON.get(),
        ExNihiloItems.MESH_FLINT.get(),
        Tags.Items.INGOTS_IRON,
        consumer);
    createMesh(
        ExNihiloItems.MESH_DIAMOND.get(),
        ExNihiloItems.MESH_IRON.get(),
        Tags.Items.GEMS_DIAMOND,
        consumer);
    createMesh(
        ExNihiloItems.MESH_EMERALD.get(),
        ExNihiloItems.MESH_DIAMOND.get(),
        Tags.Items.GEMS_EMERALD,
        consumer);
    UpgradeRecipeBuilder.smithing(
            Ingredient.of(ExNihiloItems.MESH_EMERALD.get()),
            Ingredient.of(Tags.Items.INGOTS_NETHERITE),
            ExNihiloItems.MESH_NETHERITE.get())
        .unlocks(
            "has_emerald_mesh",
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.MESH_EMERALD.get()))
        .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(
                    ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
                    ExNihiloConstants.Items.NETHERITE_MESH)));

    ShapedRecipeBuilder.shaped(ExNihiloItems.MESH_STRING.get())
        .pattern("iii")
        .pattern("iii")
        .pattern("iii")
        .define('i', Tags.Items.STRING)
        .unlockedBy(
            "has_sieve",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ExNihiloBlocks.SIEVE_OAK.get(),
                ExNihiloBlocks.SIEVE_ACACIA.get(),
                ExNihiloBlocks.SIEVE_BIRCH.get(),
                ExNihiloBlocks.SIEVE_JUNGLE.get(),
                ExNihiloBlocks.SIEVE_DARK_OAK.get(),
                ExNihiloBlocks.SIEVE_SPRUCE.get(),
                ExNihiloBlocks.SIEVE_CRIMSON.get(),
                ExNihiloBlocks.SIEVE_WARPED.get()))
        .save(consumer, createSaveLocation(ExNihiloItems.MESH_STRING.getId()));
  }

  private void registerMisc(@Nonnull final Consumer<FinishedRecipe> consumer) {
    @Nullable final ResourceLocation beehive = ForgeRegistries.BLOCKS.getKey(Blocks.BEEHIVE);
    if (beehive != null) {
      ShapedRecipeBuilder.shaped(Blocks.BEEHIVE)
          .pattern("xxx")
          .pattern("fff")
          .pattern("xxx")
          .define('x', ItemTags.PLANKS)
          .define('f', ExNihiloItems.BEEHIVE_FRAME.get())
          .unlockedBy(
              "has_frame",
              InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.BEEHIVE_FRAME.get()))
          .save(consumer, createSaveLocation(beehive));
    }

    ShapedRecipeBuilder.shaped(ExNihiloItems.BEEHIVE_FRAME.get())
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
        .save(consumer, createSaveLocation(ExNihiloItems.BEEHIVE_FRAME.getId()));

    createCookingRecipe(
        consumer,
        ExNihiloItems.SILKWORM.get(),
        ExNihiloItems.COOKED_SILKWORM.get(),
        0.1F,
        600,
        0.1F,
        100,
        "has_silkworm",
        ExNihiloItems.COOKED_SILKWORM.getId());
    createSmeltingRecipe(
        consumer,
        ExNihiloItems.SILKWORM.get(),
        ExNihiloItems.COOKED_SILKWORM.get(),
        0.1F,
        200,
        0.1F,
        100,
        "has_silkworm",
        ExNihiloItems.COOKED_SILKWORM.getId());

    createSmeltingRecipe(
        consumer,
        ExNihiloBlocks.CRUCIBLE_UNFIRED.get().asItem(),
        ExNihiloBlocks.CRUCIBLE_FIRED.get().asItem(),
        0.7F,
        200,
        0.7F,
        100,
        "has_uncooked_crucible",
        ExNihiloBlocks.CRUCIBLE_FIRED.getId());
    ShapedRecipeBuilder.shaped(ExNihiloBlocks.CRUCIBLE_UNFIRED.get())
        .pattern("c c")
        .pattern("c c")
        .pattern("ccc")
        .define('c', ExNihiloItems.PORCELAIN_CLAY.get())
        .unlockedBy(
            PORCELAIN_CLAY_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.PORCELAIN_CLAY.get()))
        .save(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_UNFIRED.getId()));

    ShapedRecipeBuilder.shaped(ExNihiloItems.CRAFTING_DOLL.get(), 4)
        .pattern("xex")
        .pattern(" x ")
        .pattern("x x")
        .define('x', ExNihiloItems.PORCELAIN_CLAY.get())
        .define('e', Tags.Items.GEMS_DIAMOND)
        .unlockedBy(
            PORCELAIN_CLAY_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.PORCELAIN_CLAY.get()))
        .unlockedBy(
            "has_diamond",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(Tags.Items.GEMS_DIAMOND).build()))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x4")));

    ShapedRecipeBuilder.shaped(ExNihiloItems.CRAFTING_DOLL.get(), 6)
        .pattern("xex")
        .pattern(" x ")
        .pattern("x x")
        .define('x', ExNihiloItems.PORCELAIN_CLAY.get())
        .define('e', Tags.Items.GEMS_EMERALD)
        .unlockedBy(
            PORCELAIN_CLAY_CONDITION,
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.PORCELAIN_CLAY.get()))
        .unlockedBy(
            "has_emerald",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(Tags.Items.GEMS_EMERALD).build()))
        .save(
            consumer,
            createSaveLocation(
                new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x6")));

    ShapedRecipeBuilder.shaped(ExNihiloBlocks.END_CAKE.get())
        .pattern("ece")
        .pattern("eke")
        .pattern("ece")
        .define('e', Items.ENDER_EYE)
        .define('c', Items.END_CRYSTAL)
        .define('k', Items.CAKE)
        .unlockedBy(
            "has_ender_pearl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENDER_PEARL))
        .save(consumer, createSaveLocation(ExNihiloBlocks.END_CAKE.getId()));

    ShapelessRecipeBuilder.shapeless(ExNihiloItems.PORCELAIN_CLAY.get())
        .requires(ExNihiloTags.CLAY)
        .requires(Items.BONE_MEAL)
        .unlockedBy(
            "has_clay",
            InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(ExNihiloTags.CLAY).build()))
        .save(consumer, createSaveLocation(ExNihiloItems.PORCELAIN_CLAY.getId()));

    @Nullable final RegistryObject<PebbleItem> blackstonePebble = ExNihiloItems.PEBBLE_BLACKSTONE;
    @Nullable
    final ResourceLocation gildedBlackstoneResourceLocation =
        ForgeRegistries.BLOCKS.getKey(Blocks.GILDED_BLACKSTONE);
    if (ExNihiloItems.GOLD.getRawOreItem() != null && gildedBlackstoneResourceLocation != null) {
      ShapedRecipeBuilder.shaped(Blocks.GILDED_BLACKSTONE)
          .pattern("xxx")
          .pattern("xgx")
          .pattern("xxx")
          .define('x', blackstonePebble.get())
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
    createOre(ExNihiloItems.IRON, consumer);
    createOre(ExNihiloItems.GOLD, consumer);
    createOre(ExNihiloItems.COPPER, consumer);
    createOreRecipes(
        ExNihiloItems.LEAD,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.LEAD.getIngotName()),
        consumer);
    createOreRecipes(
        ExNihiloItems.NICKEL,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.NICKEL.getIngotName()),
        consumer);
    createOreRecipes(
        ExNihiloItems.SILVER,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.SILVER.getIngotName()),
        consumer);
    createOreRecipes(
        ExNihiloItems.TIN,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.TIN.getIngotName()),
        consumer);
    createOreRecipes(
        ExNihiloItems.ALUMINUM,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.ALUMINUM.getIngotName()),
        consumer);
    createOreRecipes(
        ExNihiloItems.PLATINUM,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.PLATINUM.getIngotName()),
        consumer);
    createOreRecipes(
        ExNihiloItems.URANIUM,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.URANIUM.getIngotName()),
        consumer);
    createOreRecipes(
        ExNihiloItems.ZINC,
        new ResourceLocation(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloItems.ZINC.getIngotName()),
        consumer);
  }

  private void registerPebbleBlocks(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createPebbleBlock(Blocks.ANDESITE, ExNihiloItems.PEBBLE_ANDESITE.get(), consumer);
    createPebbleBlock(Blocks.BASALT, ExNihiloItems.PEBBLE_BASALT.get(), consumer);
    createPebbleBlock(Blocks.BLACKSTONE, ExNihiloItems.PEBBLE_BLACKSTONE.get(), consumer);
    createPebbleBlock(Blocks.COBBLESTONE, ExNihiloItems.PEBBLE_STONE.get(), consumer);
    createPebbleBlock(Blocks.CALCITE, ExNihiloItems.PEBBLE_CALCITE.get(), consumer);
    createPebbleBlock(Blocks.DEEPSLATE, ExNihiloItems.PEBBLE_DEEPSLATE.get(), consumer);
    createPebbleBlock(Blocks.DIORITE, ExNihiloItems.PEBBLE_DIORITE.get(), consumer);
    createPebbleBlock(Blocks.DRIPSTONE_BLOCK, ExNihiloItems.PEBBLE_DRIPSTONE.get(), consumer);
    createPebbleBlock(Blocks.END_STONE, ExNihiloItems.PEBBLE_END_STONE.get(), consumer);
    createPebbleBlock(Blocks.GRANITE, ExNihiloItems.PEBBLE_GRANITE.get(), consumer);
    createPebbleBlock(Blocks.NETHERRACK, ExNihiloItems.PEBBLE_NETHERRACK.get(), consumer);
    createPebbleBlock(Blocks.TUFF, ExNihiloItems.PEBBLE_TUFF.get(), consumer);
  }

  private void registerSieveRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
    // TODO: Add all pebble drops
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_STONE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 1.0F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 1.0F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_stone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_ANDESITE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_andesite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_DIORITE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_diorite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_GRANITE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_granite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_BASALT.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_basalt"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_BLACKSTONE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_blackstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_CALCITE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_calcite"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_DEEPSLATE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_deepslate"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_DRIPSTONE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_dripstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.CRUSHED_END_STONE.get()))
        .addResult(ExNihiloItems.PEBBLE_END_STONE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_end_stone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
        .addResult(ExNihiloItems.PEBBLE_NETHERRACK.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_netherrack"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.PEBBLE_TUFF.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.5F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("pebble_tuff"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.WHEAT_SEEDS)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.7F))
        .build(consumer, sieveLoc("seed_wheat"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.MELON_SEEDS)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.35F))
        .build(consumer, sieveLoc("seed_melon"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.PUMPKIN_SEEDS)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.35F))
        .build(consumer, sieveLoc("seed_pumpkin"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(Items.BEETROOT_SEEDS)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.35F))
        .build(consumer, sieveLoc("seed_beetroot"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.MYCELIUM_SPORE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.05F))
        .build(consumer, sieveLoc("mycelium_spore"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
        .addResult(ExNihiloItems.CRIMSON_NYLIUM_SPORE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.05F))
        .build(consumer, sieveLoc("crimson_nylium_spore"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
        .addResult(ExNihiloItems.WARPED_NYLIUM_SPORE.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.05F))
        .build(consumer, sieveLoc("warped_nylium_spore"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.DIRT))
        .addResult(ExNihiloItems.GRASS_SEED.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.05F))
        .build(consumer, sieveLoc("seed_grass"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(Items.COCOA_BEANS)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.03F))
        .build(consumer, sieveLoc("cocoa_beans"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(Items.PRISMARINE_SHARD)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.02F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.04F))
        .build(consumer, sieveLoc("prismarine_shard"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(Items.PRISMARINE_CRYSTALS)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.01F))
        .build(consumer, sieveLoc("prismarine_crystals"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.FLINT)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.25F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.25F))
        .build(consumer, sieveLoc("flint"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.COAL)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.125F))
        .build(consumer, sieveLoc("coal"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.LAPIS_LAZULI)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
        .build(consumer, sieveLoc("lapis_lazuli"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.DIAMOND)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.008F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.016F))
        .build(consumer, sieveLoc("diamond"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
        .addResult(Items.NETHERITE_SCRAP)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.004F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.008F))
        .build(consumer, sieveLoc("netherite_scrap"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .addResult(Items.EMERALD)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.008F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.016F))
        .build(consumer, sieveLoc("emerald"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SOUL_SAND))
        .addResult(Items.QUARTZ)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 1.0F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.33F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 1.0F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.8F))
        .build(consumer, sieveLoc("quartz"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SOUL_SAND))
        .addResult(Items.NETHER_WART)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.1F))
        .build(consumer, sieveLoc("nether_wart"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SOUL_SAND))
        .addResult(Items.GHAST_TEAR)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.02F))
        .build(consumer, sieveLoc("ghast_tear"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.DUST.get()))
        .addResult(Items.BONE_MEAL)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.2F))
        .build(consumer, sieveLoc("bone_meal"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.DUST.get()))
        .addResult(Items.GUNPOWDER)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.07F))
        .build(consumer, sieveLoc("gunpowder"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.DUST.get()))
        .addResult(Items.REDSTONE)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.125F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.25F))
        .build(consumer, sieveLoc("redstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.DUST.get()))
        .addResult(Items.GLOWSTONE_DUST)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.0625F))
        .build(consumer, sieveLoc("glowstone"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.DUST.get()))
        .addResult(Items.BLAZE_POWDER)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.05F))
        .build(consumer, sieveLoc("blaze_powder"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ExNihiloBlocks.CRUSHED_END_STONE.get()))
        .addResult(Items.ENDER_PEARL)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.005F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.01F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_EMERALD.get().getType(), 0.015F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_NETHERITE.get().getType(), 0.02F))
        .build(consumer, sieveLoc("ender_pearl"));

    registerIronOres(consumer);
    registerGoldOres(consumer);
    registerDefaultOres(consumer, ExNihiloItems.COPPER);
    registerDefaultOres(consumer, ExNihiloItems.LEAD);
    registerDefaultOres(consumer, ExNihiloItems.NICKEL);
    registerDefaultOres(consumer, ExNihiloItems.SILVER);
    registerDefaultOres(consumer, ExNihiloItems.TIN);
    registerDefaultOres(consumer, ExNihiloItems.ALUMINUM);
    registerDefaultOres(consumer, ExNihiloItems.PLATINUM);
    registerDefaultOres(consumer, ExNihiloItems.URANIUM);
    registerDefaultOres(consumer, ExNihiloItems.ZINC);

    //    createSeeds(ExNihiloItems.SEED_OAK.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_SPRUCE.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_BIRCH.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_JUNGLE.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_ACACIA.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_DARK_OAK.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_CACTUS.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_SUGARCANE.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_CARROT.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_POTATO.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_SWEET_BERRY.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_BAMBOO.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_FERN.get(), consumer);
    //    createSeeds(ExNihiloItems.SEED_LARGE_FERN.get(), consumer);
    //    createWaterSeeds(ExNihiloItems.SEED_KELP.get(), consumer);
    //    createWaterSeeds(ExNihiloItems.SEED_PICKLE.get(), consumer);

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
                          new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.025F))
                      .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
                      .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.075F))
                      .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.1F))
                      .build(consumer, sieveLoc(resourceLocation.getPath()));
                } else {
                  SieveRecipeBuilder.builder()
                      .input(Ingredient.of(input))
                      .addResult(drop)
                      .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.05F))
                      .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.1F))
                      .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.15F))
                      .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.2F))
                      .build(consumer, sieveLoc(resourceLocation.getPath()));
                }
              }
            });
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.LEAVES))
        .addResult(Items.APPLE)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.05F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.1F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.15F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.2F))
        .build(consumer, sieveLoc("apple"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.LEAVES))
        .addResult(Items.GOLDEN_APPLE)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.001F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.003F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.005F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.01F))
        .build(consumer, sieveLoc("golden_apple"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.LEAVES))
        .addResult(ExNihiloItems.SILKWORM.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.025F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.1F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.2F))
        .build(consumer, sieveLoc(SILKWORM));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(ExNihiloItems.TUBE_CORAL_LARVA.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_blue_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(ExNihiloItems.BUBBLE_CORAL_LARVA.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_purple_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(ExNihiloItems.BRAIN_CORAL_LARVA.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_pink_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(ExNihiloItems.HORN_CORAL_LARVA.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_yellow_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(ItemTags.SAND))
        .addResult(ExNihiloItems.FIRE_CORAL_LARVA.get())
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seed_red_coral"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .addResult(Items.SEAGRASS)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.05F))
        .isWaterlogged()
        .build(consumer, sieveLoc("seagrass"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.COARSE_DIRT))
        .addResult(Items.DIRT)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 1F))
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.4F))
        .build(consumer, sieveLoc("dirt"));
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.COARSE_DIRT))
        .addResult(Items.GRAVEL)
        .addRoll(new MeshWithChance(ExNihiloItems.MESH_STRING.get().getType(), 0.4F))
        .build(consumer, sieveLoc(GRAVEL));
  }

  private void registerSieves(@Nonnull final Consumer<FinishedRecipe> consumer) {
    createSieve(consumer, ExNihiloBlocks.SIEVE_ACACIA, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
    createSieve(consumer, ExNihiloBlocks.SIEVE_BIRCH, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
    createSieve(
        consumer, ExNihiloBlocks.SIEVE_DARK_OAK, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
    createSieve(consumer, ExNihiloBlocks.SIEVE_JUNGLE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
    createSieve(consumer, ExNihiloBlocks.SIEVE_OAK, Items.OAK_PLANKS, Items.OAK_SLAB);
    createSieve(consumer, ExNihiloBlocks.SIEVE_SPRUCE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
    createSieve(consumer, ExNihiloBlocks.SIEVE_CRIMSON, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
    createSieve(consumer, ExNihiloBlocks.SIEVE_WARPED, Items.WARPED_PLANKS, Items.WARPED_SLAB);
  }
}
