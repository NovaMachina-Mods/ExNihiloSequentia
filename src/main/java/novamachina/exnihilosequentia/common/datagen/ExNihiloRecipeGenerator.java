package novamachina.exnihilosequentia.common.datagen;

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
import novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.api.utility.ExNihiloTags;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Consumer;

public class ExNihiloRecipeGenerator extends AbstractRecipeGenerator {
    private static final String COBBLESTONE = "cobblestone";
    private static final String DOLL_CONDITION = "has_doll";
    private static final String GRAVEL_SUFFIX = "_gravel";
    private static final String LEAVES = "leaves";
    private static final String MATERIAL_CONDITION = "has_material";
    private static final String NETHERRACK = "netherrack";
    private static final String PORCELAIN_CLAY_CONDITION = "has_porcelain_clay";
    private static final Fluid water = Fluids.WATER;
    private static final Fluid lava = Fluids.LAVA;
    private static final Fluid witchwater = ExNihiloFluids.WITCH_WATER.get();
    private static final Fluid seawater = ExNihiloFluids.SEA_WATER.get();

    public ExNihiloRecipeGenerator(DataGenerator generator) {
        super(generator, ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
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
        Map<Block, Item> saplingMap = new HashMap<>();
        saplingMap.put(Blocks.ACACIA_LEAVES, Items.ACACIA_SAPLING);
        saplingMap.put(Blocks.BIRCH_LEAVES, Items.BIRCH_SAPLING);
        saplingMap.put(Blocks.DARK_OAK_LEAVES, Items.DARK_OAK_SAPLING);
        saplingMap.put(Blocks.JUNGLE_LEAVES, Items.JUNGLE_SAPLING);
        saplingMap.put(Blocks.OAK_LEAVES, Items.OAK_SAPLING);
        saplingMap.put(Blocks.SPRUCE_LEAVES, Items.SPRUCE_SAPLING);

        return saplingMap;
    }

    private void registerBarrels(Consumer<FinishedRecipe> consumer) {
        createBarrel(consumer, ExNihiloBlocks.STONE_BARREL, Tags.Items.STONE, Items.STONE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.ACACIA_BARREL, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BIRCH_BARREL, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createBarrel(consumer, ExNihiloBlocks.DARK_OAK_BARREL, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createBarrel(consumer, ExNihiloBlocks.JUNGLE_BARREL, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.OAK_BARREL, Items.OAK_PLANKS, Items.OAK_SLAB);
        createBarrel(consumer, ExNihiloBlocks.SPRUCE_BARREL, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.CRIMSON_BARREL, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createBarrel(consumer, ExNihiloBlocks.WARPED_BARREL, Items.WARPED_PLANKS, Items.WARPED_SLAB);
    }

    private void registerCompostRecipes(Consumer<FinishedRecipe> consumer) {
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
        createCompostRecipe(consumer, ExNihiloItems.SILKWORM.get(), 40, "silkworm");
        createCompostRecipe(consumer, ExNihiloItems.COOKED_SILKWORM.get(), 40, "cooked_silkworm");
        createCompostRecipe(consumer, Items.APPLE, 100, "apple");
        createCompostRecipe(consumer, Items.SWEET_BERRIES, 100, "sweet_berries");
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
        createCompostRecipe(consumer, EnumResource.GRASS_SEED.getRegistryObject().get(), 100, "grass_seed");
        createCompostRecipe(consumer, EnumResource.ANCIENT_SPORE.getRegistryObject().get(), 100, "ancient_spore");
        createCompostRecipe(consumer, Items.SWEET_BERRIES, 100, "sweet_berries");
    }

    private void registerCrookRecipes(Consumer<FinishedRecipe> consumer) {
        createCrookRecipes(consumer, ItemTags.LEAVES, ExNihiloItems.SILKWORM.get(), 0.1F, LEAVES);
        createCrookRecipes(consumer, ExNihiloBlocks.INFESTED_LEAVES.get(), ExNihiloItems.SILKWORM.get(), 0.2F, "infested_leaves_silkworm");
        createCrookRecipes(consumer, ExNihiloBlocks.INFESTED_LEAVES.get(), Items.STRING, 0.4F, "infested_leaves_string");
    }

    private void registerCrooks(Consumer<FinishedRecipe> consumer) {
        createCrook(consumer, EnumCrook.ANDESITE.getRegistryObject().get(), EnumPebbleType.ANDESITE.getRegistryObject().get());
        createCrook(consumer, EnumCrook.BONE.getRegistryObject().get(), Tags.Items.BONES);
        createCrook(consumer, EnumCrook.DIAMOND.getRegistryObject().get(), Tags.Items.GEMS_DIAMOND);
        createCrook(consumer, EnumCrook.DIORITE.getRegistryObject().get(), EnumPebbleType.DIORITE.getRegistryObject().get());
        createCrook(consumer, EnumCrook.GOLD.getRegistryObject().get(), Tags.Items.NUGGETS_GOLD);
        createCrook(consumer, EnumCrook.GRANITE.getRegistryObject().get(), EnumPebbleType.GRANITE.getRegistryObject().get());
        createCrook(consumer, EnumCrook.IRON.getRegistryObject().get(), Tags.Items.NUGGETS_IRON);
        createCrook(consumer, EnumCrook.STONE.getRegistryObject().get(), EnumPebbleType.STONE.getRegistryObject().get());
        createCrook(consumer, EnumCrook.WOOD.getRegistryObject().get(), Tags.Items.RODS_WOODEN);
    }

    private void registerCrucibles(Consumer<FinishedRecipe> consumer) {
        createCrucible(consumer, ExNihiloBlocks.ACACIA_CRUCIBLE, Items.ACACIA_LOG, Items.ACACIA_SLAB);
        createCrucible(consumer, ExNihiloBlocks.BIRCH_CRUCIBLE, Items.BIRCH_LOG, Items.BIRCH_SLAB);
        createCrucible(consumer, ExNihiloBlocks.DARK_OAK_CRUCIBLE, Items.DARK_OAK_LOG, Items.DARK_OAK_SLAB);
        createCrucible(consumer, ExNihiloBlocks.JUNGLE_CRUCIBLE, Items.JUNGLE_LOG, Items.JUNGLE_SLAB);
        createCrucible(consumer, ExNihiloBlocks.OAK_CRUCIBLE, Items.OAK_LOG, Items.OAK_SLAB);
        createCrucible(consumer, ExNihiloBlocks.SPRUCE_CRUCIBLE, Items.SPRUCE_LOG, Items.SPRUCE_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRIMSON_CRUCIBLE, Items.CRIMSON_STEM, Items.CRIMSON_SLAB);
        createCrucible(consumer, ExNihiloBlocks.WARPED_CRUCIBLE, Items.WARPED_STEM, Items.WARPED_SLAB);
    }

    private void registerCrucibleRecipes(Consumer<FinishedRecipe> consumer) {
        createFiredCrucibleRecipes(consumer, Blocks.COBBLESTONE, 250,  "cobblestone");
        createFiredCrucibleRecipes(consumer, Blocks.DIORITE, 250,  "diorite");
        createFiredCrucibleRecipes(consumer, Blocks.ANDESITE, 250,  "andesite");
        createFiredCrucibleRecipes(consumer, Blocks.GRANITE, 250, "granite");
        createFiredCrucibleRecipes(consumer, Blocks.STONE, 250, "stone");
        createFiredCrucibleRecipes(consumer, Blocks.GRAVEL, 200, "gravel");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_ANDESITE.get(), 200, "crushed_andesite");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_DIORITE.get(), 200, "crushed_diorite");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_END_STONE.get(), 200, "crushed_end_stone");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_GRANITE.get(), 200, "crushed_granite");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), 200, "crushed_netherrack");
        createFiredCrucibleRecipes(consumer, Blocks.SAND, 100, "sand");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.DUST.get(), 50, "dust");
        createFiredCrucibleRecipes(consumer, Blocks.NETHERRACK, 1000, "netherrack");
        createFiredCrucibleRecipes(consumer, Blocks.OBSIDIAN, 1000, "obsidian");

        createWaterCrucibleRecipes(consumer, ItemTags.SAPLINGS, 250, "saplings");
        createWaterCrucibleRecipes(consumer, ItemTags.LEAVES, 250, "leaves");
    }

    private void registerCustomRecipes(Consumer<FinishedRecipe> consumer) {
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

    private void registerDefaultOres(Consumer<FinishedRecipe> consumer, EnumOre ore) {
        createSieveRecipe(consumer, Blocks.GRAVEL, ore.getPieceItem().get(), ore.getPieceName() + GRAVEL_SUFFIX,
                flintChance(0.05F), ironChance(0.075F), diamondChance(0.1F));
    }

    private void registerDolls(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(EnumDoll.SHULKER.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_PURPLE)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.END_STONES)
                .define('b', Tags.Items.ENDER_PEARLS)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.SHULKER.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.GUARDIAN.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.GEMS_PRISMARINE)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', ItemTags.FISHES)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.GUARDIAN.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.BEE.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_YELLOW)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', ItemTags.FLOWERS)
                .define('b', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.BEE.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.BLAZE.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Items.BLAZE_POWDER)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', Tags.Items.CROPS_NETHER_WART)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.BLAZE.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.ENDERMAN.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_BLACK)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', Tags.Items.CROPS_NETHER_WART)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.ENDERMAN.getRegistryObject().getId()));
    }

    private void registerFluidItemRecipes(Consumer<FinishedRecipe> consumer) {
        createFluidItemRecipes(consumer, water, ExNihiloBlocks.DUST.get().asItem(), Blocks.CLAY, "clay");
        createFluidItemRecipes(consumer, lava, Tags.Items.DUSTS_REDSTONE, Blocks.NETHERRACK, NETHERRACK);
        createFluidItemRecipes(consumer, lava, Tags.Items.DUSTS_GLOWSTONE, Blocks.END_STONE, "end_stone");
        createFluidItemRecipes(consumer, witchwater, Tags.Items.SAND, Blocks.SOUL_SAND, "soul_sand");
        createFluidItemRecipes(consumer, witchwater, Items.COARSE_DIRT, Blocks.SOUL_SOIL, "soul_soil");
        createFluidItemRecipes(consumer, witchwater, Tags.Items.MUSHROOMS, Blocks.SLIME_BLOCK, "slime");
        createFluidItemRecipes(consumer, seawater, EnumResource.BLUE_CORAL_SEED.getRegistryObject().get(), Blocks.TUBE_CORAL_BLOCK, "tube_coral");
        createFluidItemRecipes(consumer, seawater, EnumResource.RED_CORAL_SEED.getRegistryObject().get(), Blocks.FIRE_CORAL_BLOCK, "fire_coral");
        createFluidItemRecipes(consumer, seawater, EnumResource.PINK_CORAL_SEED.getRegistryObject().get(), Blocks.BRAIN_CORAL_BLOCK, "brain_coral");
        createFluidItemRecipes(consumer, seawater, EnumResource.PURPLE_CORAL_SEED.getRegistryObject().get(), Blocks.BUBBLE_CORAL_BLOCK, "bubble_coral");
        createFluidItemRecipes(consumer, seawater, EnumResource.YELLOW_CORAL_SEED.getRegistryObject().get(), Blocks.HORN_CORAL_BLOCK, "horn_coral");
        createFluidItemRecipes(consumer, witchwater, EnumResource.ANCIENT_SPORE.getRegistryObject().get(), Blocks.BROWN_MUSHROOM_BLOCK, "brown_mushroom");
        createFluidItemRecipes(consumer, witchwater, Blocks.BROWN_MUSHROOM_BLOCK.asItem(), Blocks.RED_MUSHROOM_BLOCK, "red_mushroom");
        createFluidItemRecipes(consumer, water, Blocks.WHITE_CONCRETE_POWDER.asItem(), Blocks.WHITE_CONCRETE, Blocks.WHITE_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.ORANGE_CONCRETE_POWDER.asItem(), Blocks.ORANGE_CONCRETE, Blocks.ORANGE_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.MAGENTA_CONCRETE_POWDER.asItem(), Blocks.MAGENTA_CONCRETE, Blocks.MAGENTA_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.LIGHT_BLUE_CONCRETE_POWDER.asItem(), Blocks.LIGHT_BLUE_CONCRETE, Blocks.LIGHT_BLUE_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.YELLOW_CONCRETE_POWDER.asItem(), Blocks.YELLOW_CONCRETE, Blocks.YELLOW_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.LIME_CONCRETE_POWDER.asItem(), Blocks.LIME_CONCRETE, Blocks.LIME_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.PINK_CONCRETE_POWDER.asItem(), Blocks.PINK_CONCRETE, Blocks.PINK_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.GRAY_CONCRETE_POWDER.asItem(), Blocks.GRAY_CONCRETE, Blocks.GRAY_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.LIGHT_GRAY_CONCRETE_POWDER.asItem(), Blocks.LIGHT_GRAY_CONCRETE, Blocks.LIGHT_GRAY_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.CYAN_CONCRETE_POWDER.asItem(), Blocks.CYAN_CONCRETE, Blocks.CYAN_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.PURPLE_CONCRETE_POWDER.asItem(), Blocks.PURPLE_CONCRETE, Blocks.PURPLE_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.BLUE_CONCRETE_POWDER.asItem(), Blocks.BLUE_CONCRETE, Blocks.BLUE_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.BROWN_CONCRETE_POWDER.asItem(), Blocks.BROWN_CONCRETE, Blocks.BROWN_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.GREEN_CONCRETE_POWDER.asItem(), Blocks.GREEN_CONCRETE, Blocks.GREEN_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.RED_CONCRETE_POWDER.asItem(), Blocks.RED_CONCRETE, Blocks.RED_CONCRETE.getRegistryName().getPath());
        createFluidItemRecipes(consumer, water, Blocks.BLACK_CONCRETE_POWDER.asItem(), Blocks.BLACK_CONCRETE, Blocks.BLACK_CONCRETE.getRegistryName().getPath());
    }

    private void registerFluidOnTopRecipes(Consumer<FinishedRecipe> consumer) {
        createFluidOnTopRecipes(consumer, lava, water, Blocks.OBSIDIAN, "obsidian");
        createFluidOnTopRecipes(consumer, water, lava, Blocks.COBBLESTONE, COBBLESTONE);
    }

    private void registerFluidTransformRecipes(Consumer<FinishedRecipe> consumer) {
        createFluidTransformRecipes(consumer, water, Blocks.MYCELIUM.asItem(), witchwater, "witch_water");
        createFluidTransformRecipes(consumer, water, Tags.Items.SAND, seawater, "sea_water");
    }

    private void registerGoldOres(Consumer<FinishedRecipe> consumer, EnumOre ore) {
        createSieveRecipe(consumer, Blocks.GRAVEL, ore.getPieceItem().get(), ore.getPieceName() + GRAVEL_SUFFIX,
                flintChance(0.05F), ironChance(0.075F), diamondChance(0.15F));
        createSieveRecipe(consumer, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), ore.getPieceItem().get(), ore.getPieceName() + "_crushed_netherrack",
                flintChance(0.15F), ironChance(0.25F), diamondChance(0.4F));
    }

    private void registerHammerRecipes(Consumer<FinishedRecipe> consumer) {
        createHammerRecipes(consumer, Blocks.STONE, Blocks.COBBLESTONE, COBBLESTONE);
        createHammerRecipes(consumer, Blocks.COBBLESTONE, Blocks.GRAVEL, "gravel");
        createHammerRecipes(consumer, Blocks.GRAVEL, Blocks.SAND, "sand");
        createHammerRecipes(consumer, Blocks.SAND, ExNihiloBlocks.DUST.get(), "dust");
        createHammerRecipes(consumer, Blocks.NETHERRACK, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), NETHERRACK);
        createHammerRecipes(consumer, Blocks.ANDESITE, ExNihiloBlocks.CRUSHED_ANDESITE.get(), "andesite");
        createHammerRecipes(consumer, Blocks.DIORITE, ExNihiloBlocks.CRUSHED_DIORITE.get(), "diorite");
        createHammerRecipes(consumer, Blocks.GRANITE, ExNihiloBlocks.CRUSHED_GRANITE.get(), "granite");
        createHammerRecipes(consumer, Blocks.END_STONE, ExNihiloBlocks.CRUSHED_END_STONE.get(), "end_stone");

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

    private void registerHammers(Consumer<FinishedRecipe> consumer) {
        UpgradeRecipeBuilder
                .smithing(Ingredient.of(EnumHammer.DIAMOND.getRegistryObject().get()), Ingredient
                        .of(Tags.Items.INGOTS_NETHERITE), EnumHammer.NETHERITE.getRegistryObject().get())
                .unlocks("has_diamond_hammer", InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumHammer.DIAMOND.getRegistryObject().get()))
                .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                .save(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA,
                        EnumHammer.NETHERITE.hammerName)));
        createHammer(consumer, EnumHammer.DIAMOND.getRegistryObject().get(), Tags.Items.GEMS_DIAMOND);
        createHammer(consumer, EnumHammer.GOLD.getRegistryObject().get(), Tags.Items.INGOTS_GOLD);
        createHammer(consumer, EnumHammer.IRON.getRegistryObject().get(), Tags.Items.INGOTS_IRON);
        createHammer(consumer, EnumHammer.STONE.getRegistryObject().get(), Tags.Items.COBBLESTONE);
        createHammer(consumer, EnumHammer.WOOD.getRegistryObject().get(), ItemTags.PLANKS);
    }

    private void registerHeatRecipes(Consumer<FinishedRecipe> consumer) {
        createHeatRecipes(consumer, Blocks.LAVA, 3, "lava");
        createHeatRecipes(consumer, Blocks.FIRE, 4, "fire");
        createHeatRecipes(consumer, Blocks.TORCH, 1, "torch");
        createHeatRecipes(consumer, Blocks.WALL_TORCH, 1, "wall_torch");
        createHeatRecipes(consumer, Blocks.MAGMA_BLOCK, 2, "magma_block");
        createHeatRecipes(consumer, Blocks.GLOWSTONE, 2, "glowstone");
        createHeatRecipes(consumer, Blocks.SHROOMLIGHT, 2, "shroomlight");
        createHeatRecipes(consumer, Blocks.SOUL_FIRE, 4, "soul_fire");

        // Lit blocks
        StatePropertiesPredicate lit = StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.LIT, true).build();
        createHeatRecipes(consumer, Blocks.CAMPFIRE, 4, lit, "campfire");
        createHeatRecipes(consumer, Blocks.SOUL_CAMPFIRE, 4, lit, "soul_campfire");
        createHeatRecipes(consumer, Blocks.FURNACE, 3, lit, "furnace");
        createHeatRecipes(consumer, Blocks.REDSTONE_TORCH, 1, lit, "redstone_torch");
        createHeatRecipes(consumer, Blocks.REDSTONE_WALL_TORCH, 1, lit, "redstone_wall_torch");
    }

    private void registerVanillaOres(Consumer<FinishedRecipe> consumer, EnumOre ore) {
        createSieveRecipe(consumer, Blocks.GRAVEL, ore.getPieceItem().get(), ore.getPieceName() + GRAVEL_SUFFIX,
                flintChance(0.1F), ironChance(0.15F), diamondChance(0.25F));
        createSieveRecipe(consumer, Blocks.SAND, ore.getPieceItem().get(), ore.getPieceName() + "_sand",
                diamondChance(0.5F));
    }

    private void registerMeshes(Consumer<FinishedRecipe> consumer) {
        createMesh(consumer, EnumMesh.FLINT.getRegistryObject().get(), EnumMesh.STRING.getRegistryObject()
                .get(), Items.FLINT);
        createMesh(consumer, EnumMesh.IRON.getRegistryObject().get(), EnumMesh.FLINT.getRegistryObject()
                .get(), Tags.Items.INGOTS_IRON);
        createMesh(consumer, EnumMesh.DIAMOND.getRegistryObject().get(), EnumMesh.IRON.getRegistryObject()
                .get(), Tags.Items.GEMS_DIAMOND);
        createMesh(consumer, EnumMesh.EMERALD.getRegistryObject().get(), EnumMesh.DIAMOND.getRegistryObject()
                .get(), Tags.Items.GEMS_EMERALD);
        UpgradeRecipeBuilder
                .smithing(Ingredient.of(EnumMesh.EMERALD.getRegistryObject().get()), Ingredient
                        .of(Tags.Items.INGOTS_NETHERITE), EnumMesh.NETHERITE.getRegistryObject().get())
                .unlocks("has_emerald_mesh", InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumMesh.EMERALD.getRegistryObject().get()))
                .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                .save(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA,
                        EnumMesh.NETHERITE.getMeshName())));

        ShapedRecipeBuilder.shaped(EnumMesh.STRING.getRegistryObject().get())
                .pattern("iii")
                .pattern("iii")
                .pattern("iii")
                .define('i', Tags.Items.STRING)
                .unlockedBy("has_sieve", InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloBlocks.OAK_SIEVE.get(),
                        ExNihiloBlocks.ACACIA_SIEVE.get(), ExNihiloBlocks.BIRCH_SIEVE.get(), ExNihiloBlocks.JUNGLE_SIEVE.get(),
                        ExNihiloBlocks.DARK_OAK_SIEVE.get(), ExNihiloBlocks.SPRUCE_SIEVE.get(), ExNihiloBlocks.CRIMSON_SIEVE.get(),
                        ExNihiloBlocks.WARPED_SIEVE.get()))
                .save(consumer, createSaveLocation(EnumMesh.STRING.getRegistryObject().getId()));
    }

    private void registerMisc(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Blocks.BEEHIVE)
                .pattern("xxx")
                .pattern("fff")
                .pattern("xxx")
                .define('x', ItemTags.PLANKS)
                .define('f', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .unlockedBy("has_frame", InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.BEEHIVE_FRAME.getRegistryObject().get()))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.BEEHIVE.getRegistryName())));

        ShapedRecipeBuilder.shaped(EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .pattern("xxx")
                .pattern("xfx")
                .pattern("xxx")
                .define('x', Tags.Items.RODS_WOODEN)
                .define('f', Tags.Items.STRING)
                .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS_WOODEN).build()))
                .unlockedBy("has_string", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.STRING).build()))
                .save(consumer, createSaveLocation(EnumResource.BEEHIVE_FRAME.getRegistryObject().getId()));

        createCookingRecipe(consumer, ExNihiloItems.SILKWORM.get(), ExNihiloItems.COOKED_SILKWORM.get(),
                0.1F, 600,0.1F, 100, "has_silkworm", ExNihiloItems.COOKED_SILKWORM.getId());
        createSmeltingRecipe(consumer, ExNihiloItems.SILKWORM.get(), ExNihiloItems.COOKED_SILKWORM.get(),
                0.1F, 200,0.1F, 100, "has_silkworm", ExNihiloItems.COOKED_SILKWORM.getId());

        createSmeltingRecipe(consumer, ExNihiloBlocks.UNFIRED_CRUCIBLE.get().asItem(), ExNihiloBlocks.FIRED_CRUCIBLE.get().asItem(),
                0.7F, 200, 0.7F, 100, "has_uncooked_crucible", ExNihiloBlocks.FIRED_CRUCIBLE.getId());

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.UNFIRED_CRUCIBLE.get())
                .pattern("c c")
                .pattern("c c")
                .pattern("ccc")
                .define('c', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .save(consumer, createSaveLocation(ExNihiloBlocks.UNFIRED_CRUCIBLE.getId()));

        ShapedRecipeBuilder.shaped(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 4)
                .pattern("xex")
                .pattern(" x ")
                .pattern("x x")
                .define('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .define('e', Tags.Items.GEMS_DIAMOND)
                .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .unlockedBy("has_diamond", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.GEMS_DIAMOND).build()))
                .save(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "doll_x4")));

        ShapedRecipeBuilder.shaped(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 6)
                .pattern("xex")
                .pattern(" x ")
                .pattern("x x")
                .define('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .define('e', Tags.Items.GEMS_EMERALD)
                .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .unlockedBy("has_emerald", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.GEMS_EMERALD).build()))
                .save(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "doll_x6")));

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.END_CAKE.get())
                .pattern("ece")
                .pattern("eke")
                .pattern("ece")
                .define('e', Items.ENDER_EYE)
                .define('c', Items.END_CRYSTAL)
                .define('k', Items.CAKE)
                .unlockedBy("has_ender_pearl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENDER_PEARL))
                .save(consumer, createSaveLocation(ExNihiloBlocks.END_CAKE.getId()));

        ShapelessRecipeBuilder.shapeless(EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .requires(ExNihiloTags.CLAY)
                .requires(Items.BONE_MEAL)
                .unlockedBy("has_clay", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ExNihiloTags.CLAY).build()))
                .save(consumer, createSaveLocation(EnumResource.PORCELAIN_CLAY.getRegistryObject().getId()));

        ShapedRecipeBuilder.shaped(Blocks.GILDED_BLACKSTONE)
                .pattern("xxx")
                .pattern("xgx")
                .pattern("xxx")
                .define('x', EnumPebbleType.BLACKSTONE.getRegistryObject().get())
                .define('g', Items.RAW_GOLD)
                .unlockedBy("has_gold", InventoryChangeTrigger.TriggerInstance
                        .hasItems(Items.RAW_GOLD))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.GILDED_BLACKSTONE.getRegistryName())));
        ShapedRecipeBuilder.shaped(Blocks.CRYING_OBSIDIAN)
                .pattern(" o ")
                .pattern("obo")
                .pattern(" o ")
                .define('b', Items.WATER_BUCKET)
                .define('o', Blocks.OBSIDIAN)
                .unlockedBy("has_obsidian", InventoryChangeTrigger.TriggerInstance
                        .hasItems(Blocks.OBSIDIAN))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.CRYING_OBSIDIAN.getRegistryName())));
        ShapelessRecipeBuilder.shapeless(Blocks.ANCIENT_DEBRIS)
                .requires(Items.NETHERITE_SCRAP)
                .requires(Blocks.OBSIDIAN)
                .unlockedBy("has_scrap", InventoryChangeTrigger.TriggerInstance
                        .hasItems(Items.NETHERITE_SCRAP))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.ANCIENT_DEBRIS.getRegistryName())));
    }

    private void registerOres(Consumer<FinishedRecipe> consumer) {
        for (EnumOre ore : EnumOre.values()) {
            if (!ore.isVanilla()) {
                createSmeltingRecipe(consumer, ore.getRawOreItem().get(), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(),
                        0.7F, 200, 0.7F, 100, RAW_ORE_CONDITION,
                        new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, ore.getIngotName()));
                createRawOreRecipe(ore, consumer);
            }
            if (ore.isVanilla()) {
                if (ore == EnumOre.IRON) {
                    createSmeltingRecipe(consumer, Items.RAW_IRON, Items.IRON_INGOT,
                            0.7F, 200, 0.7F, 100, RAW_ORE_CONDITION,
                            new ResourceLocation(ModIds.MINECRAFT, "iron_ingot"));
                }
                if (ore == EnumOre.GOLD) {
                    createSmeltingRecipe(consumer, Items.RAW_GOLD, Items.GOLD_INGOT,
                            0.7F, 200, 0.7F, 100, RAW_ORE_CONDITION,
                            new ResourceLocation(ModIds.MINECRAFT, "gold_ingot"));
                }
                if (ore == EnumOre.COPPER) {
                    createSmeltingRecipe(consumer, Items.RAW_COPPER, Items.COPPER_INGOT,
                            0.7F, 200, 0.7F, 100, RAW_ORE_CONDITION,
                            new ResourceLocation(ModIds.MINECRAFT, "copper_ingot"));
                }
                createVanillaOreRecipe(ore, consumer);
            }
        }
    }

    private void registerPebbleBlocks(Consumer<FinishedRecipe> consumer) {
        createPebbleBlock(consumer, Blocks.ANDESITE, EnumPebbleType.ANDESITE.getRegistryObject().get());
        createPebbleBlock(consumer, Blocks.COBBLESTONE, EnumPebbleType.STONE.getRegistryObject().get());
        createPebbleBlock(consumer, Blocks.DIORITE, EnumPebbleType.DIORITE.getRegistryObject().get());
        createPebbleBlock(consumer, Blocks.GRANITE, EnumPebbleType.GRANITE.getRegistryObject().get());
        createPebbleBlock(consumer, Blocks.BASALT, EnumPebbleType.BASALT.getRegistryObject().get());
        createPebbleBlock(consumer, Blocks.BLACKSTONE, EnumPebbleType.BLACKSTONE.getRegistryObject().get());
    }

    private void registerSieves(Consumer<FinishedRecipe> consumer) {
        createSieve(consumer, ExNihiloBlocks.ACACIA_SIEVE, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createSieve(consumer, ExNihiloBlocks.BIRCH_SIEVE, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createSieve(consumer, ExNihiloBlocks.DARK_OAK_SIEVE, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createSieve(consumer, ExNihiloBlocks.JUNGLE_SIEVE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createSieve(consumer, ExNihiloBlocks.OAK_SIEVE, Items.OAK_PLANKS, Items.OAK_SLAB);
        createSieve(consumer, ExNihiloBlocks.SPRUCE_SIEVE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createSieve(consumer, ExNihiloBlocks.CRIMSON_SIEVE, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createSieve(consumer, ExNihiloBlocks.WARPED_SIEVE, Items.WARPED_PLANKS, Items.WARPED_SLAB);
    }

    private void registerSieveRecipes(Consumer<FinishedRecipe> consumer) {
        createSieveRecipe(consumer, Blocks.DIRT, EnumPebbleType.STONE.getRegistryObject().get(), "stone_pebble",
                stringChance(0), stringChance(0), stringChance(0.5F), stringChance(0.5F), stringChance(0.1F), stringChance(0.1F));
        createSieveRecipe(consumer, Blocks.DIRT, EnumPebbleType.ANDESITE.getRegistryObject().get(), "andesite_pebble",
                stringChance(0.5F), stringChance(0.1F));
        createSieveRecipe(consumer, Blocks.DIRT, EnumPebbleType.DIORITE.getRegistryObject().get(), "diorite_pebble",
                stringChance(0.5F), stringChance(0.1F));
        createSieveRecipe(consumer, Blocks.DIRT, EnumPebbleType.GRANITE.getRegistryObject().get(), "granite_pebble",
                stringChance(0.5F), stringChance(0.1F));
        createSieveRecipe(consumer, Blocks.DIRT, EnumPebbleType.BASALT.getRegistryObject().get(), "basalt_pebble",
                stringChance(0.5F), stringChance(0.1F));
        createSieveRecipe(consumer, Blocks.DIRT, EnumPebbleType.BLACKSTONE.getRegistryObject().get(), "blackstone_pebble",
                stringChance(0.5F), stringChance(0.1F));
        createSieveRecipe(consumer, Blocks.SAND, Items.PRISMARINE_SHARD, "prismarine_shard",
                ironChance(0.02F), diamondChance(0.04F));
        createSieveRecipe(consumer, Blocks.SAND, Items.PRISMARINE_CRYSTALS, "prismarine_crystals",
                diamondChance(0.01F));
        createSieveRecipe(consumer, Blocks.GRAVEL, Items.FLINT, "flint",
                stringChance(0.25F), flintChance(0.25F));
        createSieveRecipe(consumer, Blocks.GRAVEL, Items.COAL, "coal",
                flintChance(0.125F));
        createSieveRecipe(consumer, Blocks.GRAVEL, Items.LAPIS_LAZULI, "lapis_lazuli",
                flintChance(0.05F));
        createSieveRecipe(consumer, Blocks.GRAVEL, Items.DIAMOND, "diamond",
                ironChance(0.008F), diamondChance(0.016F));
        createSieveRecipe(consumer, Blocks.GRAVEL, Items.EMERALD, "emerald",
                ironChance(0.008F), diamondChance(0.016F));
        createSieveRecipe(consumer, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), Items.NETHERITE_SCRAP, "netherite_scrap",
                ironChance(0.001F), diamondChance(0.004F), emeraldChance(0.01F));
        createSieveRecipe(consumer, Blocks.SOUL_SAND, Items.QUARTZ, "quartz",
                flintChance(0), flintChance(0.33F), diamondChance(0), diamondChance(0.8F));
        createSieveRecipe(consumer, Blocks.SOUL_SAND, Items.NETHER_WART, "nether_wart",
                stringChance(0.1F));
        createSieveRecipe(consumer, Blocks.SOUL_SAND, Items.GHAST_TEAR, "ghast_tear",
                diamondChance(0.02F));
        createSieveRecipe(consumer, ExNihiloBlocks.DUST.get(), Items.BONE_MEAL, "bone_meal",
                stringChance(0.2F));
        createSieveRecipe(consumer, ExNihiloBlocks.DUST.get(), Items.GUNPOWDER, "gunpowder",
                stringChance(0.07F));
        createSieveRecipe(consumer, ExNihiloBlocks.DUST.get(), Items.REDSTONE, "redstone",
                ironChance(0.125F), diamondChance(0.25F));
        createSieveRecipe(consumer, ExNihiloBlocks.DUST.get(), Items.GLOWSTONE_DUST, "glowstone",
                ironChance(0.0625F));
        createSieveRecipe(consumer, ExNihiloBlocks.DUST.get(), Items.BLAZE_POWDER, "blaze_powder",
                ironChance(0.05F));
        createSieveRecipe(consumer, ExNihiloBlocks.CRUSHED_END_STONE.get(), Items.ENDER_PEARL, "ender_pearl",
                ironChance(0.005F), diamondChance(0.01F), emeraldChance(0.015F), netheriteChance(0.02F));

        for (EnumOre ore : EnumOre.values()) {
            switch (ore) {
                case IRON, COPPER -> registerVanillaOres(consumer, ore);
                case GOLD -> registerGoldOres(consumer, ore);
                default -> registerDefaultOres(consumer, ore);
            }
        }

        List<Item> seeds = new ArrayList<>();
        seeds.add(Items.PUMPKIN_SEEDS);
        seeds.add(Items.MELON_SEEDS);
        seeds.add(Items.WHEAT_SEEDS);
        seeds.add(Items.BEETROOT_SEEDS);
        seeds.add(Items.SPRUCE_SAPLING);
        seeds.add(Items.ACACIA_SAPLING);
        seeds.add(Items.BIRCH_SAPLING);
        seeds.add(Items.JUNGLE_SAPLING);
        seeds.add(Items.DARK_OAK_SAPLING);
        seeds.add(Items.OAK_SAPLING);
        seeds.add(Items.KELP);
        seeds.add(Items.CACTUS);
        seeds.add(Items.SUGAR_CANE);
        seeds.add(Items.CARROT);
        seeds.add(Items.POTATO);
        seeds.add(Items.SWEET_BERRIES);
        seeds.add(Items.SEA_PICKLE);
        seeds.add(Items.BAMBOO);
        seeds.add(Items.FERN);
        seeds.add(Items.LARGE_FERN);
        seeds.add(EnumResource.ANCIENT_SPORE.getRegistryObject().get());
        seeds.add(EnumResource.GRASS_SEED.getRegistryObject().get());
        seeds.add(Items.SEAGRASS);
        seeds.add(EnumResource.RED_CORAL_SEED.getRegistryObject().get());
        seeds.add(EnumResource.YELLOW_CORAL_SEED.getRegistryObject().get());
        seeds.add(EnumResource.PINK_CORAL_SEED.getRegistryObject().get());
        seeds.add(EnumResource.PURPLE_CORAL_SEED.getRegistryObject().get());
        seeds.add(EnumResource.BLUE_CORAL_SEED.getRegistryObject().get());
        seeds.add(Items.COCOA_BEANS);

        for (Item seed : seeds) {
            if (seed.getRegistryName() != null) {
                if (seed == Items.MELON_SEEDS || seed == Items.PUMPKIN_SEEDS || seed == Items.BEETROOT_SEEDS) {
                    createSieveRecipe(consumer, Blocks.DIRT, seed, seed.getRegistryName().getPath(), stringChance(0.35F));
                } else if (seed == Items.SEA_PICKLE || seed == Items.KELP) {
                    createSieveRecipe(consumer, Blocks.SAND, seed, seed.getRegistryName().getPath(), stringChance(0.05F));
                } else if (seed == Items.WHEAT_SEEDS) {
                    createSieveRecipe(consumer, Blocks.DIRT, seed, seed.getRegistryName().getPath(), stringChance(0.7F));
                } else if (seed == Items.SEAGRASS || seed == EnumResource.RED_CORAL_SEED.getRegistryObject().get() ||
                        seed == EnumResource.YELLOW_CORAL_SEED.getRegistryObject().get() ||
                        seed == EnumResource.PINK_CORAL_SEED.getRegistryObject().get() ||
                        seed == EnumResource.PURPLE_CORAL_SEED.getRegistryObject().get() ||
                        seed == EnumResource.BLUE_CORAL_SEED.getRegistryObject().get()) {
                    createSieveRecipe(consumer, Blocks.SAND, seed, seed.getRegistryName().getPath(),
                            ironChance(0.05F));
                } else if (seed == Items.COCOA_BEANS) {
                    createSieveRecipe(consumer, Blocks.SAND, seed, seed.getRegistryName().getPath(),
                            stringChance(0.03F));
                } else {
                    createSieveRecipe(consumer, Blocks.DIRT, seed, seed.getRegistryName().getPath(),
                            stringChance(0.05F));
                }
            }
        }

        getLeavesSaplings().forEach((input, drop) -> {
            if (Objects.equals(input.getRegistryName(), new ResourceLocation("jungle_leaves"))) {
                createSieveRecipe(consumer, input, drop, input.getRegistryName().getPath(),
                        stringChance(0.025F), flintChance(0.05F), ironChance(0.075F), diamondChance(0.1F));
            } else {
                createSieveRecipe(consumer, input, drop, Objects.requireNonNull(input.getRegistryName()).getPath(),
                        stringChance(0.05F), flintChance(0.1F), ironChance(0.15F), diamondChance(0.2F));
            }
        });
        createSieveRecipe(consumer, ItemTags.LEAVES, Items.APPLE, "apple",
                stringChance(0.05F), flintChance(0.1F), ironChance(0.15F), diamondChance(0.2F));
        createSieveRecipe(consumer, ItemTags.LEAVES, Items.GOLDEN_APPLE, "golden_apple",
                stringChance(0.001F), flintChance(0.003F), ironChance(0.005F), diamondChance(0.01F));
        createSieveRecipe(consumer, ItemTags.LEAVES, ExNihiloItems.SILKWORM.get(), "silkworm",
                stringChance(0.025F), flintChance(0.05F), ironChance(0.1F), diamondChance(0.2F));
    }
}
