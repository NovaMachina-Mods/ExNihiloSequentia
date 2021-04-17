package novamachina.exnihilosequentia.common.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipeBuilder;
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
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloRecipeGenerator extends AbstractRecipeGenerator {
    private static final String COBBLESTONE = "cobblestone";
    private static final String DOLL_CONDITION = "has_doll";
    private static final String GRAVEL_SUFFIX = "_gravel";
    private static final String LEAVES = "leaves";
    private static final String MATERIAL_CONDITION = "has_material";
    private static final String NETHERRACK = "netherrack";
    private static final String PEBBLE_CONDITION = "has_pebble";
    private static final String PORCELAIN_CLAY_CONDITION = "has_porcelain_clay";

    public ExNihiloRecipeGenerator(DataGenerator generator) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        registerCrooks(consumer);
        registerPebbleBlocks(consumer);
        registerBarrels(consumer);
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
        ShapedRecipeBuilder.shaped(ExNihiloBlocks.BARREL_STONE.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("x-x")
                .define('x', Tags.Items.STONE)
                .define('-', Blocks.STONE_SLAB)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy("has_walls", has(Tags.Items.STONE))
                .unlockedBy("has_base", has(Blocks.STONE_SLAB))
                .save(consumer, createSaveLocation(ExNihiloBlocks.BARREL_STONE.getId()));
        ShapedRecipeBuilder.shaped(ExNihiloBlocks.BARREL_WOOD.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("x-x")
                .define('x', ItemTags.PLANKS)
                .define('-', ItemTags.WOODEN_SLABS)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy("has_walls", has(ItemTags.PLANKS))
                .unlockedBy("has_base", has(ItemTags.WOODEN_SLABS))
                .save(consumer, createSaveLocation(ExNihiloBlocks.BARREL_WOOD.getId()));
    }

    private void registerCompostRecipes(Consumer<IFinishedRecipe> consumer) {
        CompostRecipeBuilder.builder().input(ItemTags.SAPLINGS).amount(125).build(consumer, compostLoc("saplings"));
        CompostRecipeBuilder.builder().input(ItemTags.LEAVES).amount(125).build(consumer, compostLoc(LEAVES));
        CompostRecipeBuilder.builder().input(ItemTags.FLOWERS).amount(100).build(consumer, compostLoc("flowers"));
        CompostRecipeBuilder.builder().input(ItemTags.FISHES).amount(150).build(consumer, compostLoc("fishes"));
        CompostRecipeBuilder.builder().input(ExNihiloTags.MEAT_COOKED).amount(200)
                .build(consumer, compostLoc("meat_cooked"));
        CompostRecipeBuilder.builder().input(ExNihiloTags.MEAT_UNCOOKED).amount(200)
                .build(consumer, compostLoc("meat_uncooked"));
        CompostRecipeBuilder.builder().input(Tags.Items.SEEDS).amount(80).build(consumer, compostLoc("seeds"));
        CompostRecipeBuilder.builder().input(Tags.Items.CROPS_WHEAT).amount(80).build(consumer, compostLoc("wheat"));
        CompostRecipeBuilder.builder().input(Tags.Items.CROPS_CARROT).amount(100).build(consumer, compostLoc("carrot"));
        CompostRecipeBuilder.builder().input(Tags.Items.CROPS_BEETROOT).amount(100)
                .build(consumer, compostLoc("beetroot"));
        CompostRecipeBuilder.builder().input(Tags.Items.CROPS_POTATO).amount(100).build(consumer, compostLoc("potato"));
        CompostRecipeBuilder.builder().input(Tags.Items.CROPS_NETHER_WART).amount(100)
                .build(consumer, compostLoc("nether_wart"));
        CompostRecipeBuilder.builder().input(Tags.Items.EGGS).amount(80).build(consumer, compostLoc("eggs"));
        CompostRecipeBuilder.builder().input(Tags.Items.STRING).amount(40).build(consumer, compostLoc("string"));
        CompostRecipeBuilder.builder().input(Items.ROTTEN_FLESH).amount(100)
                .build(consumer, compostLoc("rotten_flesh"));
        CompostRecipeBuilder.builder().input(Items.SPIDER_EYE).amount(80).build(consumer, compostLoc("spider_eye"));
        CompostRecipeBuilder.builder().input(Items.BREAD).amount(160).build(consumer, compostLoc("bread"));
        CompostRecipeBuilder.builder().input(Blocks.BROWN_MUSHROOM).amount(100)
                .build(consumer, compostLoc("brown_mushroom"));
        CompostRecipeBuilder.builder().input(Blocks.RED_MUSHROOM).amount(100)
                .build(consumer, compostLoc("red_mushroom"));
        CompostRecipeBuilder.builder().input(Items.CRIMSON_FUNGUS).amount(100)
                .build(consumer, compostLoc("crimson_fungus"));
        CompostRecipeBuilder.builder().input(Items.WARPED_FUNGUS).amount(100)
                .build(consumer, compostLoc("warped_fungus"));
        CompostRecipeBuilder.builder().input(Items.PUMPKIN_PIE).amount(160).build(consumer, compostLoc("pumpkin_pie"));
        CompostRecipeBuilder.builder().input(EnumResource.SILKWORM.getRegistryObject().get()).amount(40)
                .build(consumer, compostLoc("silkworm"));
        CompostRecipeBuilder.builder().input(ExNihiloItems.COOKED_SILKWORM.get()).amount(40)
                .build(consumer, compostLoc("cooked_silkworm"));
        CompostRecipeBuilder.builder().input(Items.APPLE).amount(100).build(consumer, compostLoc("apple"));
        CompostRecipeBuilder.builder().input(Items.MELON_SLICE).amount(40).build(consumer, compostLoc("melon_slice"));
        CompostRecipeBuilder.builder().input(Items.MELON).amount(1000 / 6).build(consumer, compostLoc("melon"));
        CompostRecipeBuilder.builder().input(Items.PUMPKIN).amount(1000 / 6).build(consumer, compostLoc("pumpkin"));
        CompostRecipeBuilder.builder().input(Items.CARVED_PUMPKIN).amount(1000 / 6)
                .build(consumer, compostLoc("carved_pumpkin"));
        CompostRecipeBuilder.builder().input(Items.JACK_O_LANTERN).amount(1000 / 6)
                .build(consumer, compostLoc("jack_o_lantern"));
        CompostRecipeBuilder.builder().input(Items.CACTUS).amount(100).build(consumer, compostLoc("cactus"));
        CompostRecipeBuilder.builder().input(Items.BAKED_POTATO).amount(150)
                .build(consumer, compostLoc("baked_potato"));
        CompostRecipeBuilder.builder().input(Items.POISONOUS_POTATO).amount(200)
                .build(consumer, compostLoc("poisonous_potato"));
        CompostRecipeBuilder.builder().input(Items.LILY_PAD).amount(100).build(consumer, compostLoc("lily_pad"));
        CompostRecipeBuilder.builder().input(Items.VINE).amount(100).build(consumer, compostLoc("vine"));
        CompostRecipeBuilder.builder().input(Items.WEEPING_VINES).amount(100).build(consumer, compostLoc("weeping_vine"));
        CompostRecipeBuilder.builder().input(Items.TWISTING_VINES).amount(100).build(consumer, compostLoc("twisting_vine"));
        CompostRecipeBuilder.builder().input(Items.TALL_GRASS).amount(100).build(consumer, compostLoc("tall_grass"));
        CompostRecipeBuilder.builder().input(Items.SUGAR_CANE).amount(80).build(consumer, compostLoc("sugar_cane"));
    }

    private void registerCrook(Item result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(result.getRegistryName()));
    }

    private void registerCrook(Item result, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(input).build()))
                .save(consumer, createSaveLocation(result.getRegistryName()));
    }

    private void registerCrookRecipes(Consumer<IFinishedRecipe> consumer) {
        CrookRecipeBuilder.builder().input(ItemTags.LEAVES)
                .addDrop(EnumResource.SILKWORM.getRegistryObject().get(), 0.1F).build(consumer, crookLoc(LEAVES));
    }

    private void registerCrooks(Consumer<IFinishedRecipe> consumer) {
        registerCrook(EnumCrook.ANDESITE.getRegistryObject().get(), EnumPebbleType.ANDESITE.getRegistryObject()
                .get(), consumer);
        registerCrook(EnumCrook.BONE.getRegistryObject().get(), Tags.Items.BONES, consumer);
        registerCrook(EnumCrook.DIAMOND.getRegistryObject().get(), Tags.Items.GEMS_DIAMOND, consumer);
        registerCrook(EnumCrook.DIORITE.getRegistryObject().get(), EnumPebbleType.DIORITE.getRegistryObject()
                .get(), consumer);
        registerCrook(EnumCrook.GOLD.getRegistryObject().get(), Tags.Items.NUGGETS_GOLD, consumer);
        registerCrook(EnumCrook.GRANITE.getRegistryObject().get(), EnumPebbleType.GRANITE.getRegistryObject()
                .get(), consumer);
        registerCrook(EnumCrook.IRON.getRegistryObject().get(), Tags.Items.NUGGETS_IRON, consumer);
        registerCrook(EnumCrook.STONE.getRegistryObject().get(), ExNihiloTags.STONE_STICK, consumer);
        registerCrook(EnumCrook.WOOD.getRegistryObject().get(), Tags.Items.RODS_WOODEN, consumer);
    }

    private void registerCrucibleRecipes(Consumer<IFinishedRecipe> consumer) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.COBBLESTONE)).amount(250)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(COBBLESTONE));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.DIORITE)).amount(250).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("diorite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.ANDESITE)).amount(250)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("andesite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.GRANITE)).amount(250).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("granite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.STONE)).amount(250).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("stone"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL)).amount(200).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("gravel"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_ANDESITE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_andesite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_DIORITE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_diorite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_END_STONE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_end_stone"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_GRANITE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_granite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_netherrack"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND)).amount(100).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("sand"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get())).amount(50)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("dust"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.NETHERRACK)).amount(1000)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(NETHERRACK));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(Blocks.OBSIDIAN)).amount(1000)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("obsidian"));

        CrucibleRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAPLINGS)).amount(250)
                .fluidResult(Fluids.WATER).crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc("saplings"));
        CrucibleRecipeBuilder.builder().input(Ingredient.of(ItemTags.LEAVES)).amount(250).fluidResult(Fluids.WATER)
                .crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc(LEAVES));
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

    private void registerDefaultOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerDolls(Consumer<IFinishedRecipe> consumer) {
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
    }

    private void registerFluidItemRecipes(Consumer<IFinishedRecipe> consumer) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(Fluids.WATER).input(ExNihiloBlocks.DUST.get()).result(Blocks.CLAY)
                .build(consumer, fluidItemLoc("clay"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(Fluids.LAVA).input(Tags.Items.DUSTS_REDSTONE)
                .result(Blocks.NETHERRACK)
                .build(consumer, fluidItemLoc(NETHERRACK));
        FluidItemRecipeBuilder.builder().fluidInBarrel(Fluids.LAVA).input(Tags.Items.DUSTS_GLOWSTONE)
                .result(Blocks.END_STONE)
                .build(consumer, fluidItemLoc("end_stone"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.WITCH_WATER.get()).input(Tags.Items.SAND)
                .result(Blocks.SOUL_SAND).build(consumer, fluidItemLoc("soul_sand"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.WITCH_WATER.get()).input(Items.COARSE_DIRT)
                .result(Blocks.SOUL_SOIL).build(consumer, fluidItemLoc("soul_soil"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.WITCH_WATER.get()).input(Tags.Items.MUSHROOMS)
                .result(Blocks.SLIME_BLOCK).build(consumer, fluidItemLoc("slime"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.SEA_WATER.get())
                .input(EnumResource.BLUE_CORAL_SEED.getRegistryObject()
                        .get()).result(Blocks.TUBE_CORAL_BLOCK).build(consumer, fluidItemLoc("tube_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.SEA_WATER.get())
                .input(EnumResource.RED_CORAL_SEED.getRegistryObject()
                        .get()).result(Blocks.FIRE_CORAL_BLOCK).build(consumer, fluidItemLoc("fire_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.SEA_WATER.get())
                .input(EnumResource.PINK_CORAL_SEED.getRegistryObject()
                        .get()).result(Blocks.BRAIN_CORAL_BLOCK).build(consumer, fluidItemLoc("brain_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.SEA_WATER.get())
                .input(EnumResource.PURPLE_CORAL_SEED.getRegistryObject()
                        .get()).result(Blocks.BUBBLE_CORAL_BLOCK).build(consumer, fluidItemLoc("bubble_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.SEA_WATER.get())
                .input(EnumResource.YELLOW_CORAL_SEED.getRegistryObject()
                        .get()).result(Blocks.HORN_CORAL_BLOCK).build(consumer, fluidItemLoc("horn_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.WITCH_WATER.get())
                .input(EnumResource.ANCIENT_SPORE.getRegistryObject().get())
                .result(Blocks.BROWN_MUSHROOM_BLOCK).build(consumer, fluidItemLoc("brown_mushroom"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.WITCH_WATER.get())
                .input(Blocks.BROWN_MUSHROOM_BLOCK)
                .result(Blocks.RED_MUSHROOM_BLOCK).build(consumer, fluidItemLoc("red_mushroom"));
    }

    private void registerFluidOnTopRecipes(Consumer<IFinishedRecipe> consumer) {
        FluidOnTopRecipeBuilder.builder().fluidInTank(Fluids.LAVA).fluidOnTop(Fluids.WATER).result(Blocks.OBSIDIAN)
                .build(consumer, fluidOnTopLoc("obsidian"));
        FluidOnTopRecipeBuilder.builder().fluidInTank(Fluids.WATER).fluidOnTop(Fluids.LAVA).result(Blocks.COBBLESTONE)
                .build(consumer, fluidOnTopLoc(COBBLESTONE));
    }

    private void registerFluidTransformRecipes(Consumer<IFinishedRecipe> consumer) {
        FluidTransformRecipeBuilder.builder().fluidInTank(Fluids.WATER).catalyst(Ingredient.of(Blocks.MYCELIUM))
                .result(ExNihiloFluids.WITCH_WATER.get()).build(consumer, fluidTransformLoc("witch_water"));
        FluidTransformRecipeBuilder.builder().fluidInTank(Fluids.WATER).catalyst(Ingredient.of(Tags.Items.SAND))
                .result(ExNihiloFluids.SEA_WATER.get()).build(consumer, fluidTransformLoc("sea_water"));
    }

    private void registerGoldOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.4F))
                .build(consumer, sieveLoc(ore.getPieceName() + "_crushed_netherrack"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.15F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerHammer(Item output, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern(" x ")
                .pattern(" -x")
                .pattern("-  ")
                .define('x', input)
                .define('-', Tags.Items.RODS)
                .unlockedBy("has_stick", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS).build()))
                .unlockedBy(MATERIAL_CONDITION, has(input))
                .save(consumer, createSaveLocation(output.getRegistryName()));
    }

    private void registerHammerRecipes(Consumer<IFinishedRecipe> consumer) {
        HammerRecipeBuilder.builder().input(Blocks.STONE).addDrop(Blocks.COBBLESTONE)
                .build(consumer, hammerLoc(COBBLESTONE));
        HammerRecipeBuilder.builder().input(Blocks.COBBLESTONE).addDrop(Blocks.GRAVEL)
                .build(consumer, hammerLoc("gravel"));
        HammerRecipeBuilder.builder().input(Blocks.GRAVEL).addDrop(Blocks.SAND).build(consumer, hammerLoc("sand"));
        HammerRecipeBuilder.builder().input(Blocks.SAND).addDrop(ExNihiloBlocks.DUST.get())
                .build(consumer, hammerLoc("dust"));
        HammerRecipeBuilder.builder().input(Blocks.NETHERRACK).addDrop(ExNihiloBlocks.CRUSHED_NETHERRACK.get())
                .build(consumer, hammerLoc(NETHERRACK));
        HammerRecipeBuilder.builder().input(Blocks.ANDESITE).addDrop(ExNihiloBlocks.CRUSHED_ANDESITE.get())
                .build(consumer, hammerLoc("andesite"));
        HammerRecipeBuilder.builder().input(Blocks.DIORITE).addDrop(ExNihiloBlocks.CRUSHED_DIORITE.get())
                .build(consumer, hammerLoc("diorite"));
        HammerRecipeBuilder.builder().input(Blocks.GRANITE).addDrop(ExNihiloBlocks.CRUSHED_GRANITE.get())
                .build(consumer, hammerLoc("granite"));
        HammerRecipeBuilder.builder().input(Blocks.END_STONE).addDrop(ExNihiloBlocks.CRUSHED_END_STONE.get())
                .build(consumer, hammerLoc("end_stone"));

        HammerRecipeBuilder.builder().input(Blocks.TUBE_CORAL_BLOCK).addDrop(Blocks.TUBE_CORAL)
                .build(consumer, hammerLoc("tube_coral"));
        HammerRecipeBuilder.builder().input(Blocks.BRAIN_CORAL_BLOCK).addDrop(Blocks.BRAIN_CORAL)
                .build(consumer, hammerLoc("brain_coral"));
        HammerRecipeBuilder.builder().input(Blocks.BUBBLE_CORAL_BLOCK).addDrop(Blocks.BUBBLE_CORAL)
                .build(consumer, hammerLoc("bubble_coral"));
        HammerRecipeBuilder.builder().input(Blocks.FIRE_CORAL_BLOCK).addDrop(Blocks.FIRE_CORAL)
                .build(consumer, hammerLoc("fire_coral"));
        HammerRecipeBuilder.builder().input(Blocks.HORN_CORAL_BLOCK).addDrop(Blocks.HORN_CORAL)
                .build(consumer, hammerLoc("horn_coral"));
        HammerRecipeBuilder.builder().input(Blocks.TUBE_CORAL).addDrop(Blocks.TUBE_CORAL_FAN)
                .build(consumer, hammerLoc("tube_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.BRAIN_CORAL).addDrop(Blocks.BRAIN_CORAL_FAN)
                .build(consumer, hammerLoc("brain_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.BUBBLE_CORAL).addDrop(Blocks.BUBBLE_CORAL_FAN)
                .build(consumer, hammerLoc("bubble_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.FIRE_CORAL).addDrop(Blocks.FIRE_CORAL_FAN)
                .build(consumer, hammerLoc("fire_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.HORN_CORAL).addDrop(Blocks.HORN_CORAL_FAN)
                .build(consumer, hammerLoc("horn_coral_fan"));
    }

    private void registerHammers(Consumer<IFinishedRecipe> consumer) {
        SmithingRecipeBuilder
                .smithing(Ingredient.of(EnumHammer.DIAMOND.getRegistryObject().get()), Ingredient
                        .of(Tags.Items.INGOTS_NETHERITE), EnumHammer.NETHERITE.getRegistryObject().get())
                .unlocks("has_diamond_hammer", InventoryChangeTrigger.Instance
                        .hasItems(EnumHammer.DIAMOND.getRegistryObject().get()))
                .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EnumHammer.NETHERITE.hammerName)));
        registerHammer(EnumHammer.DIAMOND.getRegistryObject().get(), Tags.Items.GEMS_DIAMOND, consumer);
        registerHammer(EnumHammer.GOLD.getRegistryObject().get(), Tags.Items.INGOTS_GOLD, consumer);
        registerHammer(EnumHammer.IRON.getRegistryObject().get(), Tags.Items.INGOTS_IRON, consumer);
        registerHammer(EnumHammer.STONE.getRegistryObject().get(), Tags.Items.COBBLESTONE, consumer);
        registerHammer(EnumHammer.WOOD.getRegistryObject().get(), ItemTags.PLANKS, consumer);
    }

    private void registerHeatRecipes(Consumer<IFinishedRecipe> consumer) {
        HeatRecipeBuilder.builder().input(Blocks.LAVA).amount(3).build(consumer, heatLoc("lava"));
        HeatRecipeBuilder.builder().input(Blocks.FIRE).amount(4).build(consumer, heatLoc("fire"));
        HeatRecipeBuilder.builder().input(Blocks.TORCH).amount(1)
                .build(consumer, heatLoc("torch"));
        HeatRecipeBuilder.builder().input(Blocks.WALL_TORCH).amount(1)
                .build(consumer, heatLoc("wall_torch"));
        HeatRecipeBuilder.builder().input(Blocks.MAGMA_BLOCK).amount(2)
                .build(consumer, heatLoc("magma_block"));
        HeatRecipeBuilder.builder().input(Blocks.GLOWSTONE).amount(2)
                .build(consumer, heatLoc("glowstone"));
        HeatRecipeBuilder.builder().input(Blocks.SHROOMLIGHT).amount(2)
                .build(consumer, heatLoc("shroomlight"));
		HeatRecipeBuilder.builder().input(Blocks.SOUL_FIRE).amount(4)
                .build(consumer, heatLoc("soul_fire"));
    }

    private void registerIronOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
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

    private void registerMesh(Item output, Item inputMesh, ITag.INamedTag<Item> inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.Instance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(output.getRegistryName()));
    }

    private void registerMesh(Item output, Item inputMesh, Item inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.Instance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(output.getRegistryName()));
    }

    private void registerMeshes(Consumer<IFinishedRecipe> consumer) {
        registerMesh(EnumMesh.FLINT.getRegistryObject().get(), EnumMesh.STRING.getRegistryObject()
                .get(), Items.FLINT, consumer);
        registerMesh(EnumMesh.IRON.getRegistryObject().get(), EnumMesh.FLINT.getRegistryObject()
                .get(), Tags.Items.INGOTS_IRON, consumer);
        registerMesh(EnumMesh.DIAMOND.getRegistryObject().get(), EnumMesh.IRON.getRegistryObject()
                .get(), Tags.Items.GEMS_DIAMOND, consumer);
        registerMesh(EnumMesh.EMERALD.getRegistryObject().get(), EnumMesh.DIAMOND.getRegistryObject()
                .get(), Tags.Items.GEMS_EMERALD, consumer);
        SmithingRecipeBuilder
                .smithing(Ingredient.of(EnumMesh.EMERALD.getRegistryObject().get()), Ingredient
                        .of(Tags.Items.INGOTS_NETHERITE), EnumMesh.NETHERITE.getRegistryObject().get())
                .unlocks("has_emerald_mesh", InventoryChangeTrigger.Instance
                        .hasItems(EnumMesh.EMERALD.getRegistryObject().get()))
                .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EnumMesh.NETHERITE
                        .getMeshName())));

        ShapedRecipeBuilder.shaped(EnumMesh.STRING.getRegistryObject().get())
                .pattern("iii")
                .pattern("iii")
                .pattern("iii")
                .define('i', Tags.Items.STRING)
                .unlockedBy("has_sieve", InventoryChangeTrigger.Instance.hasItems(ExNihiloBlocks.SIEVE.get()))
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
                .save(consumer, createSaveLocation(Blocks.BEEHIVE.getRegistryName()));

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

        CookingRecipeBuilder.smelting(Ingredient
                .of(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 200)
                .unlockedBy("has_silkworm", InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "cooked_silkworm")));
        CookingRecipeBuilder.cooking(Ingredient
                .of(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 600, IRecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy("has_silkworm", InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "cooked_silkworm_from_campfilre")));
        CookingRecipeBuilder.cooking(Ingredient
                .of(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 100, IRecipeSerializer.SMOKING_RECIPE)
                .unlockedBy("has_silkworm", InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "cooked_silkworm_from_smoker")));
        CookingRecipeBuilder.blasting(Ingredient
                .of(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 100)
                .unlockedBy("has_silkworm", InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "blast_cooked_silkworm")));

        CookingRecipeBuilder
                .smelting(Ingredient.of(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()), ExNihiloBlocks.CRUCIBLE_FIRED
                        .get(), 0.7F, 200)
                .unlockedBy("has_uncooked_crucible", InventoryChangeTrigger.Instance
                        .hasItems(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()))
                .save(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_FIRED.getId()));
        CookingRecipeBuilder
                .blasting(Ingredient.of(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()), ExNihiloBlocks.CRUCIBLE_FIRED
                        .get(), 0.7F, 100)
                .unlockedBy("has_uncooked_crucible", InventoryChangeTrigger.Instance
                        .hasItems(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "blast_crucible_fired")));

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.CRUCIBLE_UNFIRED.get())
                .pattern("c c")
                .pattern("c c")
                .pattern("ccc")
                .define('c', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .save(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_UNFIRED.getId()));

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.CRUCIBLE_WOOD.get())
                .pattern("c c")
                .pattern("clc")
                .pattern("s s")
                .define('c', ItemTags.LOGS)
                .define('l', ItemTags.WOODEN_SLABS)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_logs", has(ItemTags.LOGS))
                .save(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_WOOD.getId()));

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
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x4")));

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
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x6")));

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.END_CAKE.get())
                .pattern("ece")
                .pattern("eke")
                .pattern("ece")
                .define('e', Items.ENDER_EYE)
                .define('c', Items.END_CRYSTAL)
                .define('k', Items.CAKE)
                .unlockedBy("has_ender_pearl", InventoryChangeTrigger.Instance.hasItems(Items.ENDER_PEARL))
                .save(consumer, createSaveLocation(ExNihiloBlocks.END_CAKE.getId()));
        ShapedRecipeBuilder.shaped(ExNihiloBlocks.SIEVE.get())
                .pattern("p p")
                .pattern("plp")
                .pattern("s s")
                .define('p', ItemTags.PLANKS)
                .define('l', ItemTags.WOODEN_SLABS)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_plank", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(ItemTags.PLANKS).build()))
                .save(consumer, createSaveLocation(ExNihiloBlocks.SIEVE.getId()));

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
                .save(consumer, createSaveLocation(Blocks.GILDED_BLACKSTONE.getRegistryName()));
        ShapedRecipeBuilder.shaped(Blocks.CRYING_OBSIDIAN)
                .pattern(" o ")
                .pattern("obo")
                .pattern(" o ")
                .define('b', Items.WATER_BUCKET)
                .define('o', Blocks.OBSIDIAN)
                .unlockedBy("has_obsidian", InventoryChangeTrigger.Instance
                        .hasItems(Blocks.OBSIDIAN))
                .save(consumer, createSaveLocation(Blocks.CRYING_OBSIDIAN.getRegistryName()));
        ShapedRecipeBuilder.shaped(Blocks.ANCIENT_DEBRIS)
                .pattern("xxx")
                .pattern("xox")
                .pattern("xxx")
                .define('x', Items.NETHERITE_SCRAP)
                .define('o', Blocks.OBSIDIAN)
                .unlockedBy("has_obsidian", InventoryChangeTrigger.Instance
                        .hasItems(Blocks.OBSIDIAN))
                .save(consumer, createSaveLocation(Blocks.ANCIENT_DEBRIS.getRegistryName()));
		ShapedRecipeBuilder.shaped(EnumResource.STONE_STICK.getRegistryObject().get())
                .pattern("x")
                .pattern("x")
                .pattern("x")
				.define('x', EnumPebbleType.STONE.getRegistryObject().get())
                .unlockedBy("has_stone_pebble", InventoryChangeTrigger.Instance
                    .hasItems(EnumPebbleType.STONE.getRegistryObject().get()))
                .save(consumer, createSaveLocation(EnumResource.STONE_STICK.getRegistryObject().getId()));
    }

    private void registerOres(Consumer<IFinishedRecipe> consumer) {
        for (EnumOre ore : EnumOre.values()) {
            registerOre(ore, consumer);
            if (!ore.isVanilla()) {
                registerSmelting(ore, consumer);
            }
            if (ore.isVanilla()) {
                if (ore == EnumOre.IRON) {
                    CookingRecipeBuilder
                            .smelting(Ingredient.of(ore.getChunkItem().get()), Items.IRON_INGOT, 0.7F, 200)
                            .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .hasItems(ore.getChunkItem().get()))
                            .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "ingot_iron")));
                    CookingRecipeBuilder
                            .blasting(Ingredient.of(ore.getChunkItem().get()), Items.IRON_INGOT, 0.7F, 100)
                            .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .hasItems(ore.getChunkItem().get()))
                            .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "blast_ingot_iron")));
                }
                if (ore == EnumOre.GOLD) {
                    CookingRecipeBuilder
                            .smelting(Ingredient.of(ore.getChunkItem().get()), Items.GOLD_INGOT, 0.7F, 200)
                            .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .hasItems(ore.getChunkItem().get()))
                            .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "ingot_gold")));
                    CookingRecipeBuilder
                            .blasting(Ingredient.of(ore.getChunkItem().get()), Items.GOLD_INGOT, 0.7F, 100)
                            .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .hasItems(ore.getChunkItem().get()))
                            .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "blast_ingot_gold")));
                }
            }
        }
    }

    private void registerPebbleBlock(Block result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern("xx")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(result.getRegistryName()));
    }

    private void registerPebbleBlocks(Consumer<IFinishedRecipe> consumer) {
        registerPebbleBlock(Blocks.ANDESITE, EnumPebbleType.ANDESITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.COBBLESTONE, EnumPebbleType.STONE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.DIORITE, EnumPebbleType.DIORITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.GRANITE, EnumPebbleType.GRANITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.BASALT, EnumPebbleType.BASALT.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.BLACKSTONE, EnumPebbleType.BLACKSTONE.getRegistryObject().get(), consumer);
    }

    private void registerSieveRecipes(Consumer<IFinishedRecipe> consumer) {
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
                    registerIronOres(consumer, ore);
                    break;
                case GOLD:
                    registerGoldOres(consumer, ore);
                    break;
                default:
                    registerDefaultOres(consumer, ore);
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
