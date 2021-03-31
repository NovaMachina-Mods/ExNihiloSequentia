package novamachina.exnihilosequentia.common.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

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
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
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

    private void registerBarrels(Consumer<IFinishedRecipe> consumer) {
        createBarrel(consumer, ExNihiloBlocks.BARREL_STONE, Tags.Items.STONE, Items.STONE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_ACACIA, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_BIRCH, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_DARK_OAK, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_JUNGLE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_SPRUCE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_OAK, Items.OAK_PLANKS, Items.OAK_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_CRIMSON, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_WARPED, Items.WARPED_PLANKS, Items.WARPED_SLAB);
    }

    private void registerCrucibles(Consumer<IFinishedRecipe> consumer) {
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_ACACIA, Items.ACACIA_LOG, Items.ACACIA_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_BIRCH, Items.BIRCH_LOG, Items.BIRCH_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_DARK_OAK, Items.DARK_OAK_LOG, Items.DARK_OAK_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_JUNGLE, Items.JUNGLE_LOG, Items.JUNGLE_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_SPRUCE, Items.SPRUCE_LOG, Items.SPRUCE_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_OAK, Items.OAK_LOG, Items.OAK_SLAB);
        }

    private void registerSieves(Consumer<IFinishedRecipe> consumer) {
        createSieve(consumer, ExNihiloBlocks.SIEVE_ACACIA, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_BIRCH, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_DARK_OAK, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_JUNGLE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_SPRUCE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_OAK, Items.OAK_PLANKS, Items.OAK_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_CRIMSON, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_WARPED, Items.WARPED_PLANKS, Items.WARPED_SLAB);
    }

    private void registerCompostRecipes(Consumer<IFinishedRecipe> consumer) {
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
        createCompostRecipe(consumer, EnumResource.SILKWORM.getRegistryObject().get(), 40, "silkworm");
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
    }

    private void registerCrookRecipes(Consumer<IFinishedRecipe> consumer) {
        createCrookRecipes(consumer, ItemTags.LEAVES,
                EnumResource.SILKWORM.getRegistryObject().get(), 0.1F, LEAVES);
    }

    private void registerCrooks(Consumer<IFinishedRecipe> consumer) {
        createCrook(EnumCrook.ANDESITE.getRegistryObject().get(),
                EnumPebbleType.ANDESITE.getRegistryObject().get(), consumer);
        createCrook(EnumCrook.BONE.getRegistryObject().get(), Tags.Items.BONES, consumer);
        createCrook(EnumCrook.DIAMOND.getRegistryObject().get(), Tags.Items.GEMS_DIAMOND, consumer);
        createCrook(EnumCrook.DIORITE.getRegistryObject().get(),
                EnumPebbleType.DIORITE.getRegistryObject().get(), consumer);
        createCrook(EnumCrook.GOLD.getRegistryObject().get(), Tags.Items.NUGGETS_GOLD, consumer);
        createCrook(EnumCrook.GRANITE.getRegistryObject().get(),
                EnumPebbleType.GRANITE.getRegistryObject().get(), consumer);
        createCrook(EnumCrook.IRON.getRegistryObject().get(), Tags.Items.NUGGETS_IRON, consumer);
        createCrook(EnumCrook.STONE.getRegistryObject().get(),
                EnumPebbleType.STONE.getRegistryObject().get(), consumer);
        createCrook(EnumCrook.WOOD.getRegistryObject().get(), Tags.Items.RODS_WOODEN, consumer);
    }

    private void registerCrucibleRecipes(Consumer<IFinishedRecipe> consumer) {
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

    private void registerCustomRecipes(Consumer<IFinishedRecipe> consumer) {
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

    private void createDefaultOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerDolls(Consumer<IFinishedRecipe> consumer) {
        //TODO Possible to simplify?
        ShapedRecipeBuilder.shaped(EnumDoll.ENDERMAN.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_BLACK)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', Tags.Items.CROPS_NETHER_WART)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.ENDERMAN.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.BLAZE.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Items.BLAZE_POWDER)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', Tags.Items.CROPS_NETHER_WART)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.BLAZE.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.BEE.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_YELLOW)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', ItemTags.FLOWERS)
                .define('b', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.BEE.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.GUARDIAN.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.GEMS_PRISMARINE)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', ItemTags.FISHES)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.GUARDIAN.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.SHULKER.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_PURPLE)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.END_STONES)
                .define('b', Tags.Items.ENDER_PEARLS)
                .define('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumDoll.SHULKER.getRegistryObject().getId()));
    }

    private void registerFluidItemRecipes(Consumer<IFinishedRecipe> consumer) {
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
    }

    private void registerFluidOnTopRecipes(Consumer<IFinishedRecipe> consumer) {
        createFluidOnTopRecipes(consumer, lava, water, Blocks.OBSIDIAN, "obsidian");
        createFluidOnTopRecipes(consumer, water, lava, Blocks.COBBLESTONE, COBBLESTONE);
    }

    private void registerFluidTransformRecipes(Consumer<IFinishedRecipe> consumer) {
        createFluidTransformRecipes(consumer, water, ItemTags.createOptional(Objects.requireNonNull(Blocks.MYCELIUM.getRegistryName())),
                witchwater, "witch_water");
        createFluidTransformRecipes(consumer, water, Tags.Items.SAND, seawater, "sea_water");
    }

    private void createGoldOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.4F))
                .build(consumer, sieveLoc(ore.getPieceName() + "_" + ExNihiloConstants.Blocks.CRUSHED_NETHERRACK));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.15F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerHammerRecipes(Consumer<IFinishedRecipe> consumer) {
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

    private void registerHammers(Consumer<IFinishedRecipe> consumer) {
        SmithingRecipeBuilder
                .smithing(Ingredient.of(EnumHammer.DIAMOND.getRegistryObject().get()), Ingredient
                        .of(Tags.Items.INGOTS_NETHERITE), EnumHammer.NETHERITE.getRegistryObject().get())
                .unlocks("has_diamond_hammer", InventoryChangeTrigger.Instance
                        .hasItems(EnumHammer.DIAMOND.getRegistryObject().get()))
                .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                .save(consumer, createSaveLocation(exNihiloLoc(EnumHammer.NETHERITE.hammerName)));
        createHammer(EnumHammer.DIAMOND.getRegistryObject().get(), Tags.Items.GEMS_DIAMOND, consumer);
        createHammer(EnumHammer.GOLD.getRegistryObject().get(), Tags.Items.INGOTS_GOLD, consumer);
        createHammer(EnumHammer.IRON.getRegistryObject().get(), Tags.Items.INGOTS_IRON, consumer);
        createHammer(EnumHammer.STONE.getRegistryObject().get(), Tags.Items.COBBLESTONE, consumer);
        createHammer(EnumHammer.WOOD.getRegistryObject().get(), ItemTags.PLANKS, consumer);
    }

    private void registerHeatRecipes(Consumer<IFinishedRecipe> consumer) {
        createHeatRecipes(consumer, Blocks.LAVA, 3, "lava");
        createHeatRecipes(consumer, Blocks.FIRE, 4, "fire");
        createHeatRecipes(consumer, Blocks.TORCH, 1, "torch");
        createHeatRecipes(consumer, Blocks.WALL_TORCH, 1, "wall_torch");
        createHeatRecipes(consumer, Blocks.MAGMA_BLOCK, 2, "magma_block");
        createHeatRecipes(consumer, Blocks.GLOWSTONE, 2, "glowstone");
        createHeatRecipes(consumer, Blocks.SHROOMLIGHT, 2, "shroomlight");
    }

    private void createIronOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.5F))
                .build(consumer, sieveLoc(ore.getPieceName() + "_sand"));
    }

    private void registerMeshes(Consumer<IFinishedRecipe> consumer) {
        createMesh(EnumMesh.FLINT.getRegistryObject().get(), EnumMesh.STRING.getRegistryObject()
                .get(), Items.FLINT, consumer);
        createMesh(EnumMesh.IRON.getRegistryObject().get(), EnumMesh.FLINT.getRegistryObject()
                .get(), Tags.Items.INGOTS_IRON, consumer);
        createMesh(EnumMesh.DIAMOND.getRegistryObject().get(), EnumMesh.IRON.getRegistryObject()
                .get(), Tags.Items.GEMS_DIAMOND, consumer);
        createMesh(EnumMesh.EMERALD.getRegistryObject().get(), EnumMesh.DIAMOND.getRegistryObject()
                .get(), Tags.Items.GEMS_EMERALD, consumer);
        SmithingRecipeBuilder
                .smithing(Ingredient.of(EnumMesh.EMERALD.getRegistryObject().get()), Ingredient
                        .of(Tags.Items.INGOTS_NETHERITE), EnumMesh.NETHERITE.getRegistryObject().get())
                .unlocks("has_emerald_mesh", InventoryChangeTrigger.Instance
                        .hasItems(EnumMesh.EMERALD.getRegistryObject().get()))
                .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                .save(consumer, createSaveLocation(exNihiloLoc(EnumMesh.NETHERITE.getMeshName())));

        ShapedRecipeBuilder.shaped(EnumMesh.STRING.getRegistryObject().get())
                .pattern("iii")
                .pattern("iii")
                .pattern("iii")
                .define('i', Tags.Items.STRING)
                .unlockedBy("has_sieve", InventoryChangeTrigger.Instance.hasItems(ExNihiloBlocks.SIEVE_OAK.get()))
                .save(consumer, createSaveLocation(EnumMesh.STRING.getRegistryObject().getId()));
    }

    private void registerMisc(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Blocks.BEEHIVE)
                .pattern("xxx")
                .pattern("fff")
                .pattern("xxx")
                .define('x', ItemTags.PLANKS)
                .define('f', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .unlockedBy("has_frame", InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.BEEHIVE_FRAME.getRegistryObject().get()))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.BEEHIVE.getRegistryName())));

        ShapedRecipeBuilder.shaped(EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .pattern("xxx")
                .pattern("xfx")
                .pattern("xxx")
                .define('x', Tags.Items.RODS_WOODEN)
                .define('f', Tags.Items.STRING)
                .unlockedBy("has_stick", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS_WOODEN).build()))
                .unlockedBy("has_string", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.STRING).build()))
                .save(consumer, createSaveLocation(EnumResource.BEEHIVE_FRAME.getRegistryObject().getId()));

        createCookingRecipe(consumer, EnumResource.SILKWORM.getRegistryObject().get(), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 600,
                0.1F, 100, "has_silkworm", ExNihiloItems.COOKED_SILKWORM.getId());
        createSmeltingRecipe(consumer, EnumResource.SILKWORM.getRegistryObject().get(), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 200,
                0.1F, 100, "has_silkworm", ExNihiloItems.COOKED_SILKWORM.getId());

        createSmeltingRecipe(consumer, ExNihiloBlocks.CRUCIBLE_UNFIRED.get().asItem(), ExNihiloBlocks.CRUCIBLE_FIRED.get().asItem(), 0.7F, 200, 0.7F, 200, "has_uncooked_crucible", ExNihiloBlocks.CRUCIBLE_FIRED.getId());

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.CRUCIBLE_UNFIRED.get())
                .pattern("c c")
                .pattern("c c")
                .pattern("ccc")
                .define('c', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .save(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_UNFIRED.getId()));

        ShapedRecipeBuilder.shaped(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 4)
                .pattern("xex")
                .pattern(" x ")
                .pattern("x x")
                .define('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .define('e', Tags.Items.GEMS_DIAMOND)
                .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .unlockedBy("has_diamond", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.GEMS_DIAMOND).build()))
                .save(consumer, createSaveLocation(exNihiloLoc("doll_x4")));

        ShapedRecipeBuilder.shaped(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 6)
                .pattern("xex")
                .pattern(" x ")
                .pattern("x x")
                .define('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .define('e', Tags.Items.GEMS_EMERALD)
                .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .unlockedBy("has_emerald", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.GEMS_EMERALD).build()))
                .save(consumer, createSaveLocation(exNihiloLoc("doll_x6")));

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.END_CAKE.get())
                .pattern("ece")
                .pattern("eke")
                .pattern("ece")
                .define('e', Items.ENDER_EYE)
                .define('c', Items.END_CRYSTAL)
                .define('k', Items.CAKE)
                .unlockedBy("has_ender_pearl", InventoryChangeTrigger.Instance.hasItems(Items.ENDER_PEARL))
                .save(consumer, createSaveLocation(ExNihiloBlocks.END_CAKE.getId()));

        ShapelessRecipeBuilder.shapeless(EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .requires(ExNihiloTags.CLAY)
                .requires(Items.BONE_MEAL)
                .unlockedBy("has_clay", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(ExNihiloTags.CLAY).build()))
                .save(consumer, createSaveLocation(EnumResource.PORCELAIN_CLAY.getRegistryObject().getId()));

        ShapedRecipeBuilder.shaped(Blocks.GILDED_BLACKSTONE)
                .pattern("xxx")
                .pattern("xgx")
                .pattern("xxx")
                .define('x', EnumPebbleType.BLACKSTONE.getRegistryObject().get())
                .define('g', EnumOre.GOLD.getChunkItem().get())
                .unlockedBy("has_gold", InventoryChangeTrigger.Instance
                        .hasItems(EnumOre.GOLD.getChunkItem().get()))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.GILDED_BLACKSTONE.getRegistryName())));
        ShapedRecipeBuilder.shaped(Blocks.CRYING_OBSIDIAN)
                .pattern(" o ")
                .pattern("obo")
                .pattern(" o ")
                .define('b', Items.WATER_BUCKET)
                .define('o', Blocks.OBSIDIAN)
                .unlockedBy("has_obsidian", InventoryChangeTrigger.Instance
                        .hasItems(Blocks.OBSIDIAN))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.CRYING_OBSIDIAN.getRegistryName())));
        ShapedRecipeBuilder.shaped(Blocks.ANCIENT_DEBRIS)
                .pattern("xxx")
                .pattern("xox")
                .pattern("xxx")
                .define('x', Items.NETHERITE_SCRAP)
                .define('o', Blocks.OBSIDIAN)
                .unlockedBy("has_obsidian", InventoryChangeTrigger.Instance
                        .hasItems(Blocks.OBSIDIAN))
                .save(consumer, createSaveLocation(Objects.requireNonNull(Blocks.ANCIENT_DEBRIS.getRegistryName())));
    }

    private void registerOres(Consumer<IFinishedRecipe> consumer) {
        for (EnumOre ore : EnumOre.values()) {
            registerOre(ore, consumer);
            if (!ore.isVanilla()) {
                createSmeltingRecipe(consumer, ore.getChunkItem().get(), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(),
                        0.7F, 200, 0.7F, 200, CHUNK_CONDITION,
                        new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, ore.getIngotName()));
            }
            if (ore.isVanilla()) {
                if (ore == EnumOre.IRON) {
                    createSmeltingRecipe(consumer, ore.getChunkItem().get(), Items.IRON_INGOT,
                            0.7F, 200, 0.7F, 200, CHUNK_CONDITION,
                            new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "ingot_iron"));
                }
                if (ore == EnumOre.GOLD) {
                    createSmeltingRecipe(consumer, ore.getChunkItem().get(), Items.GOLD_INGOT,
                            0.7F, 200, 0.7F, 200, CHUNK_CONDITION,
                            new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "ingot_gold"));
                }
            }
        }
    }

    private void registerPebbleBlocks(Consumer<IFinishedRecipe> consumer) {
        createPebbleBlock(Blocks.ANDESITE, EnumPebbleType.ANDESITE.getRegistryObject().get(), consumer);
        createPebbleBlock(Blocks.COBBLESTONE, EnumPebbleType.STONE.getRegistryObject().get(), consumer);
        createPebbleBlock(Blocks.DIORITE, EnumPebbleType.DIORITE.getRegistryObject().get(), consumer);
        createPebbleBlock(Blocks.GRANITE, EnumPebbleType.GRANITE.getRegistryObject().get(), consumer);
        createPebbleBlock(Blocks.BASALT, EnumPebbleType.BASALT.getRegistryObject().get(), consumer);
        createPebbleBlock(Blocks.BLACKSTONE, EnumPebbleType.BLACKSTONE.getRegistryObject().get(), consumer);
    }

    private void registerSieveRecipes(Consumer<IFinishedRecipe> consumer) {
        //TODO Possible to simplify?
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumPebbleType.STONE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_stone"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumPebbleType.ANDESITE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_andesite"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumPebbleType.DIORITE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_diorite"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumPebbleType.GRANITE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_granite"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumPebbleType.BASALT.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_basalt"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumPebbleType.BLACKSTONE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_blackstone"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.WHEAT_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.7F))
                .build(consumer, sieveLoc("seed_wheat"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.MELON_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_melon"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.PUMPKIN_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_pumpkin"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.BEETROOT_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_beetroot"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumResource.ANCIENT_SPORE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                .build(consumer, sieveLoc("ancient_spore"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(EnumResource.GRASS_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                .build(consumer, sieveLoc("seed_grass"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.COCOA_BEANS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.03F))
                .build(consumer, sieveLoc("cocoa_beans"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.PRISMARINE_SHARD)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.02F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.04F))
                .build(consumer, sieveLoc("prismarine_shard"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.PRISMARINE_CRYSTALS)
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .build(consumer, sieveLoc("prismarine_crystals"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.FLINT)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                .build(consumer, sieveLoc("flint"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.COAL)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.125F))
                .build(consumer, sieveLoc("coal"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.LAPIS_LAZULI)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .build(consumer, sieveLoc("lapis_lazuli"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.DIAMOND)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.008F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.016F))
                .build(consumer, sieveLoc("diamond"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
                .addResult(Items.NETHERITE_SCRAP)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.004F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.008F))
                .build(consumer, sieveLoc("netherite_scrap"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.EMERALD)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.008F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.016F))
                .build(consumer, sieveLoc("emerald"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SOUL_SAND))
                .addResult(Items.QUARTZ)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.33F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.8F))
                .build(consumer, sieveLoc("quartz"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SOUL_SAND))
                .addResult(Items.NETHER_WART)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("nether_wart"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SOUL_SAND))
                .addResult(Items.GHAST_TEAR)
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.02F))
                .build(consumer, sieveLoc("ghast_tear"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.BONE_MEAL)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.2F))
                .build(consumer, sieveLoc("bone_meal"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.GUNPOWDER)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.07F))
                .build(consumer, sieveLoc("gunpowder"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.REDSTONE)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.125F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
                .build(consumer, sieveLoc("redstone"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.GLOWSTONE_DUST)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.0625F))
                .build(consumer, sieveLoc("glowstone"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.BLAZE_POWDER)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .build(consumer, sieveLoc("blaze_powder"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_END_STONE.get()))
                .addResult(Items.ENDER_PEARL)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.005F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .addRoll(new MeshWithChance(EnumMesh.EMERALD, 0.015F))
                .addRoll(new MeshWithChance(EnumMesh.NETHERITE, 0.02F))
                .build(consumer, sieveLoc("ender_pearl"));

        for (EnumOre ore : EnumOre.values()) {
            switch (ore) {
                case IRON:
                    createIronOres(consumer, ore);
                    break;
                case GOLD:
                    createGoldOres(consumer, ore);
                    break;
                default:
                    createDefaultOres(consumer, ore);
            }
        }

        for (EnumSeed seed : EnumSeed.values()) {
            if (seed != EnumSeed.SEED_PICKLE && seed != EnumSeed.SEED_KELP) {
                SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                        .addResult(seed.getRegistryObject().get())
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .build(consumer, sieveLoc(seed.getSeedName()));
            } else {
                SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                        .addResult(seed.getRegistryObject().get())
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .isWaterlogged()
                        .build(consumer, sieveLoc(seed.getSeedName()));
            }
        }

        getLeavesSaplings().forEach((input, drop) -> {
            if (input.getRegistryName().equals(new ResourceLocation("jungle_leaves"))) {
                SieveRecipeBuilder.builder().input(Ingredient.of(input))
                        .addResult(drop)
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.025F))
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                        .build(consumer, sieveLoc(input.getRegistryName().getPath()));
            } else {
                SieveRecipeBuilder.builder().input(Ingredient.of(input))
                        .addResult(drop)
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                        .build(consumer, sieveLoc(input.getRegistryName().getPath()));
            }
        });
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.LEAVES))
                .addResult(Items.APPLE)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                .build(consumer, sieveLoc("apple"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.LEAVES))
                .addResult(Items.GOLDEN_APPLE)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.001F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.003F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.005F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .build(consumer, sieveLoc("golden_apple"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.LEAVES))
                .addResult(EnumResource.SILKWORM.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.025F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                .build(consumer, sieveLoc("silkworm"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                .addResult(EnumResource.BLUE_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_blue_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                .addResult(EnumResource.PURPLE_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_purple_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                .addResult(EnumResource.PINK_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_pink_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                .addResult(EnumResource.YELLOW_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_yellow_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                .addResult(EnumResource.RED_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_red_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.SEAGRASS)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seagrass"));
    }
}
