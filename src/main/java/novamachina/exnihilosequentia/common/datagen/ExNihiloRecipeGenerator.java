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
    private static final String PEBBLE_CONDITION = "has_pebble";
    private static final String PORCELAIN_CLAY_CONDITION = "has_porcelain_clay";

    public ExNihiloRecipeGenerator(DataGenerator generator) {
        super(generator, ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        registerCrooks(consumer);
        registerPebbleBlocks(consumer);
        registerBarrels(consumer);
        registerWoodCrucibles(consumer);
        registerWoodSieves(consumer);
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
        //TODO Simplify this one
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.BARREL_STONE.get())
                .patternLine("x x")
                .patternLine("x x")
                .patternLine("x-x")
                .key('x', Tags.Items.STONE)
                .key('-', Blocks.STONE_SLAB)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_walls", hasItem(Tags.Items.STONE))
                .addCriterion("has_base", hasItem(Blocks.STONE_SLAB))
                .build(consumer, createSaveLocation(ExNihiloBlocks.BARREL_STONE.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.BARREL_ACACIA.get())
                .patternLine("x x")
                .patternLine("x x")
                .patternLine("x-x")
                .key('x', Items.ACACIA_PLANKS)
                .key('-', Items.ACACIA_SLAB)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_walls", hasItem(Items.ACACIA_PLANKS))
                .addCriterion("has_base", hasItem(Items.ACACIA_SLAB))
                .build(consumer, createSaveLocation(ExNihiloBlocks.BARREL_ACACIA.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.BARREL_BIRCH.get())
                .patternLine("x x")
                .patternLine("x x")
                .patternLine("x-x")
                .key('x', Items.BIRCH_PLANKS)
                .key('-', Items.BIRCH_SLAB)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_walls", hasItem(Items.BIRCH_PLANKS))
                .addCriterion("has_base", hasItem(Items.BIRCH_SLAB))
                .build(consumer, createSaveLocation(ExNihiloBlocks.BARREL_BIRCH.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.BARREL_DARK_OAK.get())
                .patternLine("x x")
                .patternLine("x x")
                .patternLine("x-x")
                .key('x', Items.DARK_OAK_PLANKS)
                .key('-', Items.DARK_OAK_SLAB)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_walls", hasItem(Items.DARK_OAK_PLANKS))
                .addCriterion("has_base", hasItem(Items.DARK_OAK_SLAB))
                .build(consumer, createSaveLocation(ExNihiloBlocks.BARREL_DARK_OAK.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.BARREL_JUNGLE.get())
                .patternLine("x x")
                .patternLine("x x")
                .patternLine("x-x")
                .key('x', Items.JUNGLE_PLANKS)
                .key('-', Items.JUNGLE_SLAB)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_walls", hasItem(Items.JUNGLE_PLANKS))
                .addCriterion("has_base", hasItem(Items.JUNGLE_SLAB))
                .build(consumer, createSaveLocation(ExNihiloBlocks.BARREL_JUNGLE.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.BARREL_SPRUCE.get())
                .patternLine("x x")
                .patternLine("x x")
                .patternLine("x-x")
                .key('x', Items.SPRUCE_PLANKS)
                .key('-', Items.SPRUCE_SLAB)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_walls", hasItem(Items.SPRUCE_PLANKS))
                .addCriterion("has_base", hasItem(Items.SPRUCE_SLAB))
                .build(consumer, createSaveLocation(ExNihiloBlocks.BARREL_SPRUCE.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.BARREL_WOOD.get())
                .patternLine("x x")
                .patternLine("x x")
                .patternLine("x-x")
                .key('x', Items.OAK_PLANKS)
                .key('-', Items.OAK_SLAB)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_walls", hasItem(Items.OAK_PLANKS))
                .addCriterion("has_base", hasItem(Items.OAK_SLAB))
                .build(consumer, createSaveLocation(ExNihiloBlocks.BARREL_WOOD.getId()));
    }

    private void registerWoodCrucibles(Consumer<IFinishedRecipe> consumer) {
        //TODO Simplify this one
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.CRUCIBLE_ACACIA.get())
                .patternLine("c c")
                .patternLine("clc")
                .patternLine("s s")
                .key('c', Items.ACACIA_LOG)
                .key('l', Items.ACACIA_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_logs", hasItem(Items.ACACIA_LOG))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_ACACIA.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.CRUCIBLE_BIRCH.get())
                .patternLine("c c")
                .patternLine("clc")
                .patternLine("s s")
                .key('c', Items.BIRCH_LOG)
                .key('l', Items.BIRCH_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_logs", hasItem(Items.BIRCH_LOG))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_BIRCH.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get())
                .patternLine("c c")
                .patternLine("clc")
                .patternLine("s s")
                .key('c', Items.DARK_OAK_LOG)
                .key('l', Items.DARK_OAK_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_logs", hasItem(Items.DARK_OAK_LOG))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_DARK_OAK.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.CRUCIBLE_JUNGLE.get())
                .patternLine("c c")
                .patternLine("clc")
                .patternLine("s s")
                .key('c', Items.JUNGLE_LOG)
                .key('l', Items.JUNGLE_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_logs", hasItem(Items.JUNGLE_LOG))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_JUNGLE.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.CRUCIBLE_SPRUCE.get())
                .patternLine("c c")
                .patternLine("clc")
                .patternLine("s s")
                .key('c', Items.SPRUCE_LOG)
                .key('l', Items.SPRUCE_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_logs", hasItem(Items.SPRUCE_LOG))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_SPRUCE.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.CRUCIBLE_WOOD.get())
                .patternLine("c c")
                .patternLine("clc")
                .patternLine("s s")
                .key('c', Items.OAK_LOG)
                .key('l', Items.OAK_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion("has_logs", hasItem(Items.OAK_LOG))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_WOOD.getId()));
        }

    private void registerWoodSieves(Consumer<IFinishedRecipe> consumer) {
        //TODO Simplify this one
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.SIEVE_ACACIA.get())
                .patternLine("p p")
                .patternLine("plp")
                .patternLine("s s")
                .key('p', Items.ACACIA_PLANKS)
                .key('l', Items.ACACIA_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_plank", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().item(Items.ACACIA_PLANKS).build()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.SIEVE_ACACIA.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.SIEVE_BIRCH.get())
                .patternLine("p p")
                .patternLine("plp")
                .patternLine("s s")
                .key('p', Items.BIRCH_PLANKS)
                .key('l', Items.BIRCH_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_plank", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().item(Items.BIRCH_PLANKS).build()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.SIEVE_BIRCH.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.SIEVE_DARK_OAK.get())
                .patternLine("p p")
                .patternLine("plp")
                .patternLine("s s")
                .key('p', Items.DARK_OAK_PLANKS)
                .key('l', Items.DARK_OAK_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_plank", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().item(Items.DARK_OAK_PLANKS).build()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.SIEVE_DARK_OAK.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.SIEVE_JUNGLE.get())
                .patternLine("p p")
                .patternLine("plp")
                .patternLine("s s")
                .key('p', Items.JUNGLE_PLANKS)
                .key('l', Items.JUNGLE_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_plank", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().item(Items.JUNGLE_PLANKS).build()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.SIEVE_JUNGLE.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.SIEVE_SPRUCE.get())
                .patternLine("p p")
                .patternLine("plp")
                .patternLine("s s")
                .key('p', Items.SPRUCE_PLANKS)
                .key('l', Items.SPRUCE_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_plank", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().item(Items.SPRUCE_PLANKS).build()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.SIEVE_SPRUCE.getId()));
        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.SIEVE_WOOD.get())
                .patternLine("p p")
                .patternLine("plp")
                .patternLine("s s")
                .key('p', Items.OAK_PLANKS)
                .key('l', Items.OAK_SLAB)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_plank", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().item(Items.OAK_PLANKS).build()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.SIEVE_WOOD.getId()));
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
        ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("xx")
                .patternLine(" x")
                .patternLine(" x")
                .key('x', input)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.forItems(input))
                .build(consumer, createSaveLocation(result.getRegistryName()));
    }

    private void registerCrook(Item result, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("xx")
                .patternLine(" x")
                .patternLine(" x")
                .key('x', input)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion(PEBBLE_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().tag(input).build()))
                .build(consumer, createSaveLocation(result.getRegistryName()));
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
        registerCrook(EnumCrook.STONE.getRegistryObject().get(), EnumPebbleType.STONE.getRegistryObject()
                .get(), consumer);
        registerCrook(EnumCrook.WOOD.getRegistryObject().get(), Tags.Items.RODS_WOODEN, consumer);
    }

    private void registerCrucibleRecipes(Consumer<IFinishedRecipe> consumer) {
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.COBBLESTONE)).amount(250)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(COBBLESTONE));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIORITE)).amount(250).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("diorite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.ANDESITE)).amount(250)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("andesite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRANITE)).amount(250).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("granite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.STONE)).amount(250).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("stone"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL)).amount(200).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("gravel"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.CRUSHED_ANDESITE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_andesite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.CRUSHED_DIORITE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_diorite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.CRUSHED_END_STONE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_end_stone"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.CRUSHED_GRANITE.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_granite"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.CRUSHED_NETHERRACK.get())).amount(200)
                .fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("crushed_netherrack"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND)).amount(100).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("sand"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.DUST.get())).amount(50)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("dust"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.NETHERRACK)).amount(1000)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(NETHERRACK));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.OBSIDIAN)).amount(1000)
                .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("obsidian"));

        CrucibleRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.SAPLINGS)).amount(250)
                .fluidResult(Fluids.WATER).crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc("saplings"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.LEAVES)).amount(250).fluidResult(Fluids.WATER)
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
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerDolls(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(EnumDoll.SHULKER.getRegistryObject().get())
                .patternLine("ctc")
                .patternLine("sms")
                .patternLine("cbc")
                .key('c', Tags.Items.DYES_PURPLE)
                .key('s', Tags.Items.DUSTS_GLOWSTONE)
                .key('t', Tags.Items.END_STONES)
                .key('b', Tags.Items.ENDER_PEARLS)
                .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .addCriterion(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .build(consumer, createSaveLocation(EnumDoll.SHULKER.getRegistryObject().getId()));
        ShapedRecipeBuilder.shapedRecipe(EnumDoll.GUARDIAN.getRegistryObject().get())
                .patternLine("ctc")
                .patternLine("sms")
                .patternLine("cbc")
                .key('c', Tags.Items.GEMS_PRISMARINE)
                .key('s', Tags.Items.DUSTS_GLOWSTONE)
                .key('t', Tags.Items.DUSTS_REDSTONE)
                .key('b', ItemTags.FISHES)
                .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .addCriterion(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .build(consumer, createSaveLocation(EnumDoll.GUARDIAN.getRegistryObject().getId()));
        ShapedRecipeBuilder.shapedRecipe(EnumDoll.BEE.getRegistryObject().get())
                .patternLine("ctc")
                .patternLine("sms")
                .patternLine("cbc")
                .key('c', Tags.Items.DYES_YELLOW)
                .key('s', Tags.Items.DUSTS_GLOWSTONE)
                .key('t', ItemTags.FLOWERS)
                .key('b', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .addCriterion(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .build(consumer, createSaveLocation(EnumDoll.BEE.getRegistryObject().getId()));
        ShapedRecipeBuilder.shapedRecipe(EnumDoll.BLAZE.getRegistryObject().get())
                .patternLine("ctc")
                .patternLine("sms")
                .patternLine("cbc")
                .key('c', Items.BLAZE_POWDER)
                .key('s', Tags.Items.DUSTS_GLOWSTONE)
                .key('t', Tags.Items.DUSTS_REDSTONE)
                .key('b', Tags.Items.CROPS_NETHER_WART)
                .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .addCriterion(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .build(consumer, createSaveLocation(EnumDoll.BLAZE.getRegistryObject().getId()));
        ShapedRecipeBuilder.shapedRecipe(EnumDoll.ENDERMAN.getRegistryObject().get())
                .patternLine("ctc")
                .patternLine("sms")
                .patternLine("cbc")
                .key('c', Tags.Items.DYES_BLACK)
                .key('s', Tags.Items.DUSTS_GLOWSTONE)
                .key('t', Tags.Items.DUSTS_REDSTONE)
                .key('b', Tags.Items.CROPS_NETHER_WART)
                .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
                .addCriterion(DOLL_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
                .build(consumer, createSaveLocation(EnumDoll.ENDERMAN.getRegistryObject().getId()));
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
        FluidTransformRecipeBuilder.builder().fluidInTank(Fluids.WATER).catalyst(Ingredient.fromItems(Blocks.MYCELIUM))
                .result(ExNihiloFluids.WITCH_WATER.get()).build(consumer, fluidTransformLoc("witch_water"));
        FluidTransformRecipeBuilder.builder().fluidInTank(Fluids.WATER).catalyst(Ingredient.fromTag(Tags.Items.SAND))
                .result(ExNihiloFluids.SEA_WATER.get()).build(consumer, fluidTransformLoc("sea_water"));
    }

    private void registerGoldOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.4F))
                .build(consumer, sieveLoc(ore.getPieceName() + "_" + ExNihiloConstants.Blocks.CRUSHED_NETHERRACK));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.15F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerHammer(Item output, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
                .patternLine(" x ")
                .patternLine(" -x")
                .patternLine("-  ")
                .key('x', input)
                .key('-', Tags.Items.RODS)
                .addCriterion("has_stick", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().tag(Tags.Items.RODS).build()))
                .addCriterion(MATERIAL_CONDITION, hasItem(input))
                .build(consumer, createSaveLocation(output.getRegistryName()));
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
                .smithingRecipe(Ingredient.fromItems(EnumHammer.DIAMOND.getRegistryObject().get()), Ingredient
                        .fromTag(Tags.Items.INGOTS_NETHERITE), EnumHammer.NETHERITE.getRegistryObject().get())
                .addCriterion("has_diamond_hammer", InventoryChangeTrigger.Instance
                        .forItems(EnumHammer.DIAMOND.getRegistryObject().get()))
                .addCriterion(MATERIAL_CONDITION, hasItem(Tags.Items.INGOTS_NETHERITE))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, EnumHammer.NETHERITE.hammerName)));
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
    }

    private void registerIronOres(Consumer<IFinishedRecipe> consumer, EnumOre ore) {
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND))
                .addResult(ore.getPieceItem().get())
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.5F))
                .build(consumer, sieveLoc(ore.getPieceName() + "_sand"));
    }

    private void registerMesh(Item output, Item inputMesh, ITag.INamedTag<Item> inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
                .patternLine("i i")
                .patternLine("imi")
                .patternLine("i i")
                .key('i', inputItem)
                .key('m', inputMesh)
                .addCriterion("has_mesh", InventoryChangeTrigger.Instance.forItems(inputMesh))
                .build(consumer, createSaveLocation(output.getRegistryName()));
    }

    private void registerMesh(Item output, Item inputMesh, Item inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
                .patternLine("i i")
                .patternLine("imi")
                .patternLine("i i")
                .key('i', inputItem)
                .key('m', inputMesh)
                .addCriterion("has_mesh", InventoryChangeTrigger.Instance.forItems(inputMesh))
                .build(consumer, createSaveLocation(output.getRegistryName()));
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
                .smithingRecipe(Ingredient.fromItems(EnumMesh.EMERALD.getRegistryObject().get()), Ingredient
                        .fromTag(Tags.Items.INGOTS_NETHERITE), EnumMesh.NETHERITE.getRegistryObject().get())
                .addCriterion("has_emerald_mesh", InventoryChangeTrigger.Instance
                        .forItems(EnumMesh.EMERALD.getRegistryObject().get()))
                .addCriterion(MATERIAL_CONDITION, hasItem(Tags.Items.INGOTS_NETHERITE))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, EnumMesh.NETHERITE
                        .getMeshName())));

        ShapedRecipeBuilder.shapedRecipe(EnumMesh.STRING.getRegistryObject().get())
                .patternLine("iii")
                .patternLine("iii")
                .patternLine("iii")
                .key('i', Tags.Items.STRING)
                .addCriterion("has_sieve", InventoryChangeTrigger.Instance.forItems(ExNihiloBlocks.SIEVE_WOOD.get()))
                .build(consumer, createSaveLocation(EnumMesh.STRING.getRegistryObject().getId()));
    }

    private void registerMisc(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(Blocks.BEEHIVE)
                .patternLine("xxx")
                .patternLine("fff")
                .patternLine("xxx")
                .key('x', ItemTags.PLANKS)
                .key('f', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .addCriterion("has_frame", InventoryChangeTrigger.Instance
                        .forItems(EnumResource.BEEHIVE_FRAME.getRegistryObject().get()))
                .build(consumer, createSaveLocation(Blocks.BEEHIVE.getRegistryName()));

        ShapedRecipeBuilder.shapedRecipe(EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                .patternLine("xxx")
                .patternLine("xfx")
                .patternLine("xxx")
                .key('x', Tags.Items.RODS_WOODEN)
                .key('f', Tags.Items.STRING)
                .addCriterion("has_stick", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().tag(Tags.Items.RODS_WOODEN).build()))
                .addCriterion("has_string", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().tag(Tags.Items.STRING).build()))
                .build(consumer, createSaveLocation(EnumResource.BEEHIVE_FRAME.getRegistryObject().getId()));

        CookingRecipeBuilder.smeltingRecipe(Ingredient
                .fromItems(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 200)
                .addCriterion("has_silkworm", InventoryChangeTrigger.Instance
                        .forItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "cooked_silkworm")));
        CookingRecipeBuilder.cookingRecipe(Ingredient
                .fromItems(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 600, IRecipeSerializer.CAMPFIRE_COOKING)
                .addCriterion("has_silkworm", InventoryChangeTrigger.Instance
                        .forItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "cooked_silkworm_from_campfilre")));
        CookingRecipeBuilder.cookingRecipe(Ingredient
                .fromItems(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 100, IRecipeSerializer.SMOKING)
                .addCriterion("has_silkworm", InventoryChangeTrigger.Instance
                        .forItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "cooked_silkworm_from_smoker")));
        CookingRecipeBuilder.blastingRecipe(Ingredient
                .fromItems(EnumResource.SILKWORM.getRegistryObject().get()), ExNihiloItems.COOKED_SILKWORM.get(), 0.1F, 100)
                .addCriterion("has_silkworm", InventoryChangeTrigger.Instance
                        .forItems(EnumResource.SILKWORM.getRegistryObject().get()))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "blast_cooked_silkworm")));

        CookingRecipeBuilder.smeltingRecipe(Ingredient
                .fromItems(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()), ExNihiloBlocks.CRUCIBLE_FIRED.get(), 0.7F, 200)
                .addCriterion("has_uncooked_crucible", InventoryChangeTrigger.Instance
                        .forItems(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_FIRED.getId()));
        CookingRecipeBuilder
                .blastingRecipe(Ingredient.fromItems(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()), ExNihiloBlocks.CRUCIBLE_FIRED
                        .get(), 0.7F, 200)
                .addCriterion("has_uncooked_crucible", InventoryChangeTrigger.Instance
                        .forItems(ExNihiloBlocks.CRUCIBLE_UNFIRED.get()))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "blast_crucible_fired")));

        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.CRUCIBLE_UNFIRED.get())
                .patternLine("c c")
                .patternLine("c c")
                .patternLine("ccc")
                .key('c', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .addCriterion(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .build(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_UNFIRED.getId()));

        ShapedRecipeBuilder.shapedRecipe(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 4)
                .patternLine("xex")
                .patternLine(" x ")
                .patternLine("x x")
                .key('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .key('e', Tags.Items.GEMS_DIAMOND)
                .addCriterion(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .addCriterion("has_diamond", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().tag(Tags.Items.GEMS_DIAMOND).build()))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "doll_x4")));

        ShapedRecipeBuilder.shapedRecipe(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 6)
                .patternLine("xex")
                .patternLine(" x ")
                .patternLine("x x")
                .key('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .key('e', Tags.Items.GEMS_EMERALD)
                .addCriterion(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                        .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                .addCriterion("has_emerald", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().tag(Tags.Items.GEMS_EMERALD).build()))
                .build(consumer, createSaveLocation(new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "doll_x6")));

        ShapedRecipeBuilder.shapedRecipe(ExNihiloBlocks.END_CAKE.get())
                .patternLine("ece")
                .patternLine("eke")
                .patternLine("ece")
                .key('e', Items.ENDER_EYE)
                .key('c', Items.END_CRYSTAL)
                .key('k', Items.CAKE)
                .addCriterion("has_ender_pearl", InventoryChangeTrigger.Instance.forItems(Items.ENDER_PEARL))
                .build(consumer, createSaveLocation(ExNihiloBlocks.END_CAKE.getId()));

        ShapelessRecipeBuilder.shapelessRecipe(EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .addIngredient(ExNihiloTags.CLAY)
                .addIngredient(Items.BONE_MEAL)
                .addCriterion("has_clay", InventoryChangeTrigger.Instance
                        .forItems(ItemPredicate.Builder.create().tag(ExNihiloTags.CLAY).build()))
                .build(consumer, createSaveLocation(EnumResource.PORCELAIN_CLAY.getRegistryObject().getId()));
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
                            .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), Items.IRON_INGOT, 0.7F, 200)
                            .addCriterion(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .forItems(ore.getChunkItem().get()))
                            .build(consumer, createSaveLocation(new ResourceLocation(ModIds.MINECRAFT, "ingot_iron")));
                    CookingRecipeBuilder
                            .blastingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), Items.IRON_INGOT, 0.7F, 200)
                            .addCriterion(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .forItems(ore.getChunkItem().get()))
                            .build(consumer, createSaveLocation(new ResourceLocation(ModIds.MINECRAFT, "blast_ingot_iron")));
                }
                if (ore == EnumOre.GOLD) {
                    CookingRecipeBuilder
                            .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), Items.GOLD_INGOT, 0.7F, 200)
                            .addCriterion(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .forItems(ore.getChunkItem().get()))
                            .build(consumer, createSaveLocation(new ResourceLocation(ModIds.MINECRAFT, "ingot_gold")));
                    CookingRecipeBuilder
                            .blastingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), Items.GOLD_INGOT, 0.7F, 200)
                            .addCriterion(CHUNK_CONDITION, InventoryChangeTrigger.Instance
                                    .forItems(ore.getChunkItem().get()))
                            .build(consumer, createSaveLocation(new ResourceLocation(ModIds.MINECRAFT, "blast_ingot_gold")));
                }
            }
        }
    }

    private void registerPebbleBlock(Block result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("xx")
                .patternLine("xx")
                .key('x', input)
                .setGroup(ModIds.EX_NIHILO_SEQUENTIA)
                .addCriterion(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.forItems(input))
                .build(consumer, createSaveLocation(result.getRegistryName()));
    }

    private void registerPebbleBlocks(Consumer<IFinishedRecipe> consumer) {
        registerPebbleBlock(Blocks.ANDESITE, EnumPebbleType.ANDESITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.COBBLESTONE, EnumPebbleType.STONE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.DIORITE, EnumPebbleType.DIORITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.GRANITE, EnumPebbleType.GRANITE.getRegistryObject().get(), consumer);
    }

    private void registerSieveRecipes(Consumer<IFinishedRecipe> consumer) {
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumPebbleType.STONE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_stone"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumPebbleType.ANDESITE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_andesite"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumPebbleType.DIORITE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_diorite"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumPebbleType.GRANITE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_granite"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumPebbleType.BASALT.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_basalt"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumPebbleType.BLACKSTONE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("pebble_blackstone"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(Items.WHEAT_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.7F))
                .build(consumer, sieveLoc("seed_wheat"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(Items.MELON_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_melon"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(Items.PUMPKIN_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_pumpkin"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(Items.BEETROOT_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_beetroot"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumResource.ANCIENT_SPORE.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                .build(consumer, sieveLoc("ancient_spore"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                .addResult(EnumResource.GRASS_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                .build(consumer, sieveLoc("seed_grass"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND))
                .addResult(Items.COCOA_BEANS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.03F))
                .build(consumer, sieveLoc("cocoa_beans"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND))
                .addResult(Items.PRISMARINE_SHARD)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.02F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.04F))
                .build(consumer, sieveLoc("prismarine_shard"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND))
                .addResult(Items.PRISMARINE_CRYSTALS)
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .build(consumer, sieveLoc("prismarine_crystals"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(Items.FLINT)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                .build(consumer, sieveLoc("flint"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(Items.COAL)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.125F))
                .build(consumer, sieveLoc("coal"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(Items.LAPIS_LAZULI)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .build(consumer, sieveLoc("lapis_lazuli"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(Items.DIAMOND)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.008F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.016F))
                .build(consumer, sieveLoc("diamond"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                .addResult(Items.EMERALD)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.008F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.016F))
                .build(consumer, sieveLoc("emerald"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SOUL_SAND))
                .addResult(Items.QUARTZ)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.33F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.8F))
                .build(consumer, sieveLoc("quartz"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SOUL_SAND))
                .addResult(Items.NETHER_WART)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("nether_wart"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SOUL_SAND))
                .addResult(Items.GHAST_TEAR)
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.02F))
                .build(consumer, sieveLoc("ghast_tear"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.DUST.get()))
                .addResult(Items.BONE_MEAL)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.2F))
                .build(consumer, sieveLoc("bone_meal"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.DUST.get()))
                .addResult(Items.GUNPOWDER)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.07F))
                .build(consumer, sieveLoc("gunpowder"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.DUST.get()))
                .addResult(Items.REDSTONE)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.125F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
                .build(consumer, sieveLoc("redstone"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.DUST.get()))
                .addResult(Items.GLOWSTONE_DUST)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.0625F))
                .build(consumer, sieveLoc("glowstone"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.DUST.get()))
                .addResult(Items.BLAZE_POWDER)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .build(consumer, sieveLoc("blaze_powder"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ExNihiloBlocks.CRUSHED_END_STONE.get()))
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
                SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.DIRT))
                        .addResult(seed.getRegistryObject().get())
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .build(consumer, sieveLoc(seed.getSeedName()));
            } else {
                SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND))
                        .addResult(seed.getRegistryObject().get())
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .isWaterlogged()
                        .build(consumer, sieveLoc(seed.getSeedName()));
            }
        }

        getLeavesSaplings().forEach((input, drop) -> {
            if (input.getRegistryName().equals(new ResourceLocation("jungle_leaves"))) {
                SieveRecipeBuilder.builder().input(Ingredient.fromItems(input))
                        .addResult(drop)
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.025F))
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                        .build(consumer, sieveLoc(input.getRegistryName().getPath()));
            } else {
                SieveRecipeBuilder.builder().input(Ingredient.fromItems(input))
                        .addResult(drop)
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                        .build(consumer, sieveLoc(input.getRegistryName().getPath()));
            }
        });
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.LEAVES))
                .addResult(Items.APPLE)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                .build(consumer, sieveLoc("apple"));
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.LEAVES))
                .addResult(Items.GOLDEN_APPLE)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.001F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.003F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.005F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .build(consumer, sieveLoc("golden_apple"));
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.LEAVES))
                .addResult(EnumResource.SILKWORM.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.025F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                .build(consumer, sieveLoc("silkworm"));
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.SAND))
                .addResult(EnumResource.BLUE_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_blue_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.SAND))
                .addResult(EnumResource.PURPLE_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_purple_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.SAND))
                .addResult(EnumResource.PINK_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_pink_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.SAND))
                .addResult(EnumResource.YELLOW_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_yellow_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.SAND))
                .addResult(EnumResource.RED_CORAL_SEED.getRegistryObject().get())
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seed_red_coral"));
    }
}
