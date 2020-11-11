package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.api.ExNihiloTags;
import com.novamachina.exnihilosequentia.common.api.crafting.compost.CompostRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.crucible.CrucibleRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidItem.FluidItemRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidontop.FluidOnTopRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidtransform.FluidTransformRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.heat.HeatRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.sieve.MeshWithChance;
import com.novamachina.exnihilosequentia.common.api.crafting.sieve.SieveRecipeBuilder;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.init.ModFluids;
import com.novamachina.exnihilosequentia.common.init.ModItems;
import com.novamachina.exnihilosequentia.common.item.dolls.DollEnum;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
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
            .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.02F))
            .build(consumer, sieveLoc("prismarine_shard"));
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
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ModBlocks.DUST.get()))
            .addResult(Items.BONE_MEAL)
            .addRoll(new MeshWithChance(EnumMesh.STRING, 0.2F))
            .build(consumer, sieveLoc("bone_meal"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ModBlocks.DUST.get()))
            .addResult(Items.GUNPOWDER)
            .addRoll(new MeshWithChance(EnumMesh.STRING, 0.07F))
            .build(consumer, sieveLoc("gunpowder"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ModBlocks.DUST.get()))
            .addResult(Items.REDSTONE)
            .addRoll(new MeshWithChance(EnumMesh.IRON, 0.125F))
            .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
            .build(consumer, sieveLoc("redstone"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ModBlocks.DUST.get()))
            .addResult(Items.GLOWSTONE_DUST)
            .addRoll(new MeshWithChance(EnumMesh.IRON, 0.0625F))
            .build(consumer, sieveLoc("glowstone"));
        SieveRecipeBuilder.builder().input(Ingredient.fromItems(ModBlocks.DUST.get()))
            .addResult(Items.BLAZE_POWDER)
            .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
            .build(consumer, sieveLoc("blaze_powder"));

        for(EnumOre ore : EnumOre.values()) {
            switch (ore) {
                case IRON: {
                    SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                        .addResult(ore.getPieceItem().get())
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
                        .build(consumer, sieveLoc(ore.getPieceName() + "_gravel"));
                    SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND))
                        .addResult(ore.getPieceItem().get())
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.5F))
                        .build(consumer, sieveLoc(ore.getPieceName() + "_sand"));
                    break;
                }
                case GOLD: {
                    SieveRecipeBuilder.builder().input(Ingredient.fromItems(ModBlocks.CRUSHED_NETHERRACK.get()))
                        .addResult(ore.getPieceItem().get())
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.25F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.4F))
                        .build(consumer, sieveLoc(ore.getPieceName() + "_crushed_netherrack"));
                    SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                        .addResult(ore.getPieceItem().get())
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.15F))
                        .build(consumer, sieveLoc(ore.getPieceName() + "_gravel"));
                    break;
                }
                default: {
                    SieveRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.GRAVEL))
                        .addResult(ore.getPieceItem().get())
                        .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                        .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                        .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                        .build(consumer, sieveLoc(ore.getPieceName() + "_gravel"));
                }
            }
        }

        for(EnumSeed seed : EnumSeed.values()) {
            if(seed != EnumSeed.SEED_PICKLE && seed != EnumSeed.SEED_KELP) {
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
            if(input.getRegistryName().equals(new ResourceLocation("jungle_leaves"))) {
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

    private ResourceLocation sieveLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "sieve/" + id);
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

    private ResourceLocation heatLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "heat/" + id);
    }

    private void registerCrucibleRecipes(Consumer<IFinishedRecipe> consumer) {
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.COBBLESTONE)).amount(250)
            .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("cobblestone"));
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
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.SAND)).amount(100).fluidResult(Fluids.LAVA)
            .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("sand"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(ModBlocks.DUST.get())).amount(50)
            .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("dust"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.NETHERRACK)).amount(1000)
            .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("netherrack"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromItems(Blocks.OBSIDIAN)).amount(1000)
            .fluidResult(Fluids.LAVA).crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc("obsidian"));

        CrucibleRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.SAPLINGS)).amount(250)
            .fluidResult(Fluids.WATER).crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc("saplings"));
        CrucibleRecipeBuilder.builder().input(Ingredient.fromTag(ItemTags.LEAVES)).amount(250).fluidResult(Fluids.WATER)
            .crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc("leaves"));
    }

    private ResourceLocation crucibleLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crucible/" + id);
    }

    private void registerFluidTransformRecipes(Consumer<IFinishedRecipe> consumer) {
        FluidTransformRecipeBuilder.builder().fluidInTank(Fluids.WATER).blockBelow(Blocks.MYCELIUM)
            .result(ModFluids.WITCH_WATER.get()).build(consumer, fluidTransformLoc("witch_water"));
        FluidTransformRecipeBuilder.builder().fluidInTank(Fluids.WATER).blockBelow(Blocks.SAND)
            .result(ModFluids.SEA_WATER.get()).build(consumer, fluidTransformLoc("sea_water"));
    }

    private ResourceLocation fluidTransformLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "fluid_transform/" + id);
    }

    private void registerFluidOnTopRecipes(Consumer<IFinishedRecipe> consumer) {
        FluidOnTopRecipeBuilder.builder().fluidInTank(Fluids.LAVA).fluidOnTop(Fluids.WATER).result(Blocks.OBSIDIAN)
            .build(consumer, fluidOnTopLoc("obsidian"));
        FluidOnTopRecipeBuilder.builder().fluidInTank(Fluids.WATER).fluidOnTop(Fluids.LAVA).result(Blocks.COBBLESTONE)
            .build(consumer, fluidOnTopLoc("cobblestone"));
    }

    private ResourceLocation fluidOnTopLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "fluid_on_top/" + id);
    }

    private void registerFluidItemRecipes(Consumer<IFinishedRecipe> consumer) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(Fluids.WATER).input(ModBlocks.DUST.get()).result(Blocks.CLAY)
            .build(consumer, fluidItemLoc("clay"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(Fluids.LAVA).input(Items.REDSTONE).result(Blocks.NETHERRACK)
            .build(consumer, fluidItemLoc("netherrack"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(Fluids.LAVA).input(Items.GLOWSTONE_DUST).result(Blocks.END_STONE)
            .build(consumer, fluidItemLoc("end_stone"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ModFluids.WITCH_WATER.get()).input(Tags.Items.SAND)
            .result(Blocks.SOUL_SAND).build(consumer, fluidItemLoc("soul_sand"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ModFluids.WITCH_WATER.get()).input(Tags.Items.MUSHROOMS)
            .result(Blocks.SLIME_BLOCK).build(consumer, fluidItemLoc("slime"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ModFluids.SEA_WATER.get())
            .input(EnumResource.BLUE_CORAL_SEED.getRegistryObject()
                .get()).result(Blocks.TUBE_CORAL_BLOCK).build(consumer, fluidItemLoc("tube_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ModFluids.SEA_WATER.get())
            .input(EnumResource.RED_CORAL_SEED.getRegistryObject()
                .get()).result(Blocks.FIRE_CORAL_BLOCK).build(consumer, fluidItemLoc("fire_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ModFluids.SEA_WATER.get())
            .input(EnumResource.PINK_CORAL_SEED.getRegistryObject()
                .get()).result(Blocks.BRAIN_CORAL_BLOCK).build(consumer, fluidItemLoc("brain_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ModFluids.SEA_WATER.get())
            .input(EnumResource.PURPLE_CORAL_SEED.getRegistryObject()
                .get()).result(Blocks.BUBBLE_CORAL_BLOCK).build(consumer, fluidItemLoc("bubble_coral"));
        FluidItemRecipeBuilder.builder().fluidInBarrel(ModFluids.SEA_WATER.get())
            .input(EnumResource.YELLOW_CORAL_SEED.getRegistryObject()
                .get()).result(Blocks.HORN_CORAL_BLOCK).build(consumer, fluidItemLoc("horn_coral"));
    }

    private ResourceLocation fluidItemLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "fluid_item/" + id);
    }

    private void registerCompostRecipes(Consumer<IFinishedRecipe> consumer) {
        CompostRecipeBuilder.builder().input(ItemTags.SAPLINGS).amount(125).build(consumer, compostLoc("saplings"));
        CompostRecipeBuilder.builder().input(ItemTags.LEAVES).amount(125).build(consumer, compostLoc("leaves"));
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
        CompostRecipeBuilder.builder().input(Items.PUMPKIN_PIE).amount(160).build(consumer, compostLoc("pumpkin_pie"));
        CompostRecipeBuilder.builder().input(EnumResource.SILKWORM.getRegistryObject().get()).amount(40)
            .build(consumer, compostLoc("silkworm"));
        CompostRecipeBuilder.builder().input(ModItems.COOKED_SILKWORM.get()).amount(40)
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
        CompostRecipeBuilder.builder().input(Items.TALL_GRASS).amount(100).build(consumer, compostLoc("tall_grass"));
        CompostRecipeBuilder.builder().input(Items.SUGAR_CANE).amount(80).build(consumer, compostLoc("sugar_cane"));
    }

    private ResourceLocation compostLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "compost/" + id);
    }

    private void registerCrookRecipes(Consumer<IFinishedRecipe> consumer) {
        CrookRecipeBuilder.builder().input(ItemTags.LEAVES)
            .addDrop(EnumResource.SILKWORM.getRegistryObject().get(), 0.1F).build(consumer, crookLoc("leaves"));
    }

    private ResourceLocation crookLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crook/" + id);
    }

    private void registerHammerRecipes(Consumer<IFinishedRecipe> consumer) {
        HammerRecipeBuilder.builder().input(Blocks.STONE).result(Blocks.COBBLESTONE)
            .build(consumer, hammerLoc("cobblestone"));
        HammerRecipeBuilder.builder().input(Blocks.COBBLESTONE).result(Blocks.GRAVEL)
            .build(consumer, hammerLoc("gravel"));
        HammerRecipeBuilder.builder().input(Blocks.GRAVEL).result(Blocks.SAND).build(consumer, hammerLoc("sand"));
        HammerRecipeBuilder.builder().input(Blocks.SAND).result(ModBlocks.DUST.get())
            .build(consumer, hammerLoc("dust"));
        HammerRecipeBuilder.builder().input(Blocks.NETHERRACK).result(ModBlocks.CRUSHED_NETHERRACK.get())
            .build(consumer, hammerLoc("netherrack"));
        HammerRecipeBuilder.builder().input(Blocks.ANDESITE).result(ModBlocks.CRUSHED_ANDESITE.get())
            .build(consumer, hammerLoc("andesite"));
        HammerRecipeBuilder.builder().input(Blocks.DIORITE).result(ModBlocks.CRUSHED_DIORITE.get())
            .build(consumer, hammerLoc("diorite"));
        HammerRecipeBuilder.builder().input(Blocks.GRANITE).result(ModBlocks.CRUSHED_GRANITE.get())
            .build(consumer, hammerLoc("granite"));
        HammerRecipeBuilder.builder().input(Blocks.END_STONE).result(ModBlocks.CRUSHED_END_STONE.get())
            .build(consumer, hammerLoc("end_stone"));

        HammerRecipeBuilder.builder().input(Blocks.TUBE_CORAL_BLOCK).result(Blocks.TUBE_CORAL)
            .build(consumer, hammerLoc("tube_coral"));
        HammerRecipeBuilder.builder().input(Blocks.BRAIN_CORAL_BLOCK).result(Blocks.BRAIN_CORAL)
            .build(consumer, hammerLoc("brain_coral"));
        HammerRecipeBuilder.builder().input(Blocks.BUBBLE_CORAL_BLOCK).result(Blocks.BUBBLE_CORAL)
            .build(consumer, hammerLoc("bubble_coral"));
        HammerRecipeBuilder.builder().input(Blocks.FIRE_CORAL_BLOCK).result(Blocks.FIRE_CORAL)
            .build(consumer, hammerLoc("fire_coral"));
        HammerRecipeBuilder.builder().input(Blocks.HORN_CORAL_BLOCK).result(Blocks.HORN_CORAL)
            .build(consumer, hammerLoc("horn_coral"));
        HammerRecipeBuilder.builder().input(Blocks.TUBE_CORAL).result(Blocks.TUBE_CORAL_FAN)
            .build(consumer, hammerLoc("tube_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.BRAIN_CORAL).result(Blocks.BRAIN_CORAL_FAN)
            .build(consumer, hammerLoc("brain_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.BUBBLE_CORAL).result(Blocks.BUBBLE_CORAL_FAN)
            .build(consumer, hammerLoc("bubble_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.FIRE_CORAL).result(Blocks.FIRE_CORAL_FAN)
            .build(consumer, hammerLoc("fire_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.HORN_CORAL).result(Blocks.HORN_CORAL_FAN)
            .build(consumer, hammerLoc("horn_coral_fan"));
    }

    private ResourceLocation hammerLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "hammer/" + id);
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
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
            .patternLine("xxx")
            .patternLine("xfx")
            .patternLine("xxx")
            .key('x', Items.STICK)
            .key('f', Items.STRING)
            .addCriterion("has_stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
            .addCriterion("has_string", InventoryChangeTrigger.Instance.forItems(Items.STRING))
            .build(consumer);

        CookingRecipeBuilder.smeltingRecipe(Ingredient
            .fromItems(EnumResource.SILKWORM.getRegistryObject().get()), ModItems.COOKED_SILKWORM.get(), 0.1F, 200)
            .addCriterion("has_silkworm", InventoryChangeTrigger.Instance
                .forItems(EnumResource.SILKWORM.getRegistryObject().get()))
            .build(consumer);

        CookingRecipeBuilder
            .smeltingRecipe(Ingredient.fromItems(ModBlocks.CRUCIBLE_UNFIRED.get()), ModBlocks.CRUCIBLE_FIRED
                .get(), 0.7F, 200)
            .addCriterion("has_uncooked_crucible", InventoryChangeTrigger.Instance
                .forItems(ModBlocks.CRUCIBLE_UNFIRED.get()))
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.CRUCIBLE_UNFIRED.get())
            .patternLine("c c")
            .patternLine("c c")
            .patternLine("ccc")
            .key('c', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .addCriterion("has_porcelain_clay", InventoryChangeTrigger.Instance
                .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.CRUCIBLE_WOOD.get())
            .patternLine("c c")
            .patternLine("clc")
            .patternLine("s s")
            .key('c', ItemTags.LOGS)
            .key('l', ItemTags.WOODEN_SLABS)
            .key('s', Items.STICK)
            .addCriterion("has_logs", hasItem(ItemTags.LOGS))
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 4)
            .patternLine("xex")
            .patternLine(" x ")
            .patternLine("x x")
            .key('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .key('e', Items.DIAMOND)
            .addCriterion("has_porcelain_clay", InventoryChangeTrigger.Instance
                .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
            .addCriterion("has_diamond", InventoryChangeTrigger.Instance.forItems(Items.DIAMOND))
            .build(consumer, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x4"));

        ShapedRecipeBuilder.shapedRecipe(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 6)
            .patternLine("xex")
            .patternLine(" x ")
            .patternLine("x x")
            .key('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .key('e', Items.EMERALD)
            .addCriterion("has_porcelain_clay", InventoryChangeTrigger.Instance
                .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
            .addCriterion("has_emerald", InventoryChangeTrigger.Instance.forItems(Items.EMERALD))
            .build(consumer, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x6"));

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.END_CAKE.get())
            .patternLine("ece")
            .patternLine("eke")
            .patternLine("ece")
            .key('e', Items.ENDER_EYE)
            .key('c', Items.END_CRYSTAL)
            .key('k', Items.CAKE)
            .addCriterion("has_ender_pearl", InventoryChangeTrigger.Instance.forItems(Items.ENDER_PEARL))
            .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .addIngredient(Items.CLAY_BALL)
            .addIngredient(Items.BONE_MEAL)
            .addCriterion("has_clay", InventoryChangeTrigger.Instance.forItems(Items.CLAY_BALL))
            .build(consumer);
    }

    private void registerMeshes(Consumer<IFinishedRecipe> consumer) {
        registerMesh(EnumMesh.FLINT.getRegistryObject().get(), EnumMesh.STRING.getRegistryObject()
            .get(), Items.FLINT, consumer);
        registerMesh(EnumMesh.IRON.getRegistryObject().get(), EnumMesh.FLINT.getRegistryObject()
            .get(), Items.IRON_INGOT, consumer);
        registerMesh(EnumMesh.DIAMOND.getRegistryObject().get(), EnumMesh.IRON.getRegistryObject()
            .get(), Items.DIAMOND, consumer);

        ShapedRecipeBuilder.shapedRecipe(EnumMesh.STRING.getRegistryObject().get())
            .patternLine("iii")
            .patternLine("iii")
            .patternLine("iii")
            .key('i', Items.STRING)
            .addCriterion("has_sieve", InventoryChangeTrigger.Instance.forItems(ModBlocks.SIEVE.get()))
            .build(consumer);
    }

    private void registerMesh(Item output, Item inputMesh, Item inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("i i")
            .patternLine("imi")
            .patternLine("i i")
            .key('i', inputItem)
            .key('m', inputMesh)
            .addCriterion("has_mesh", InventoryChangeTrigger.Instance.forItems(inputMesh))
            .build(consumer);
    }

    private void registerDolls(Consumer<IFinishedRecipe> consumer) {
        registerDoll(DollEnum.BEE.getRegistryObject()
            .get(), Items.YELLOW_DYE, Items.GLOWSTONE_DUST, ItemTags.FLOWERS, EnumResource.BEEHIVE_FRAME
            .getRegistryObject().get(), consumer);
        registerDoll(DollEnum.BLAZE.getRegistryObject()
            .get(), Items.BLAZE_POWDER, Items.GLOWSTONE_DUST, Items.REDSTONE, Items.NETHER_WART, consumer);
        registerDoll(DollEnum.ENDERMAN.getRegistryObject()
            .get(), Items.BLACK_DYE, Items.GLOWSTONE_DUST, Items.REDSTONE, Items.NETHER_WART, consumer);
        registerDoll(DollEnum.GUARDIAN.getRegistryObject()
            .get(), Items.PRISMARINE, Items.GLOWSTONE_DUST, Items.REDSTONE, ItemTags.FISHES, consumer);
        registerDoll(DollEnum.SHULKER.getRegistryObject()
            .get(), Items.PURPLE_DYE, Items.GLOWSTONE_DUST, Items.END_STONE, Items.ENDER_PEARL, consumer);
    }

    private void registerDoll(Item output, Item corners, Item sides, Item top, ITag.INamedTag<Item> bottom, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("ctc")
            .patternLine("sms")
            .patternLine("cbc")
            .key('c', corners)
            .key('t', top)
            .key('s', sides)
            .key('b', bottom)
            .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
            .addCriterion("has_doll", InventoryChangeTrigger.Instance
                .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
            .build(consumer);
    }

    private void registerDoll(Item output, Item corners, Item sides, Item top, Item bottom, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("ctc")
            .patternLine("sms")
            .patternLine("cbc")
            .key('c', corners)
            .key('t', top)
            .key('s', sides)
            .key('b', bottom)
            .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
            .addCriterion("has_doll", InventoryChangeTrigger.Instance
                .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
            .build(consumer);
    }

    private void registerDoll(Item output, Item corners, Item sides, ITag.INamedTag<Item> top, Item bottom, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("ctc")
            .patternLine("sms")
            .patternLine("cbc")
            .key('c', corners)
            .key('t', top)
            .key('s', sides)
            .key('b', bottom)
            .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
            .addCriterion("has_doll", InventoryChangeTrigger.Instance
                .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
            .build(consumer);
    }

    private void registerHammers(Consumer<IFinishedRecipe> consumer) {
        SmithingRecipeBuilder
                .smithingRecipe(Ingredient.fromItems(EnumHammer.DIAMOND.getRegistryObject().get()), Ingredient.fromTag(Tags.Items.INGOTS_NETHERITE), EnumHammer.NETHERITE.getRegistryObject().get())
                .addCriterion("has_diamond_hammer", InventoryChangeTrigger.Instance.forItems(EnumHammer.DIAMOND.getRegistryObject().get()))
                .addCriterion("has_material", hasItem(Tags.Items.INGOTS_NETHERITE))
                .build(consumer,  new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, EnumHammer.NETHERITE.name));
        registerHammer(EnumHammer.DIAMOND.getRegistryObject().get(), Items.DIAMOND, consumer);
        registerHammer(EnumHammer.GOLD.getRegistryObject().get(), Items.GOLD_INGOT, consumer);
        registerHammer(EnumHammer.IRON.getRegistryObject().get(), Items.IRON_INGOT, consumer);
        registerHammer(EnumHammer.STONE.getRegistryObject().get(), Tags.Items.COBBLESTONE, consumer);
        registerHammer(EnumHammer.WOOD.getRegistryObject().get(), ItemTags.PLANKS, consumer);
    }

    private void registerHammer(Item output, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine(" x ")
            .patternLine(" -x")
            .patternLine("-  ")
            .key('x', input)
            .key('-', Items.STICK)
            .addCriterion("has_stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
            .addCriterion("has_material", hasItem(input))
            .build(consumer);
    }

    private void registerHammer(Item output, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine(" x ")
            .patternLine(" -x")
            .patternLine("-  ")
            .key('x', input)
            .key('-', Items.STICK)
            .addCriterion("has_stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
            .addCriterion("has_material", InventoryChangeTrigger.Instance.forItems(input))
            .build(consumer);
    }

    // TODO: Vanilla Metals don't generate recipes
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
                        .addCriterion("has_chunk", InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
                        .build(consumer);
                }
                if (ore == EnumOre.GOLD) {
                    CookingRecipeBuilder
                        .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), Items.GOLD_INGOT, 0.7F, 200)
                        .addCriterion("has_chunk", InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
                        .build(consumer);
                }
            }
        }
    }

    private void registerSmelting(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder
            .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), ore.getIngotItem().get(), 0.7F, 200)
            .addCriterion("has_chunk", InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
            .build(consumer);
    }

    private void registerOre(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ore.getChunkItem().get())
            .patternLine("xx")
            .patternLine("xx")
            .key('x', ore.getPieceItem().get())
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_piece", InventoryChangeTrigger.Instance.forItems(ore.getPieceItem().get()))
            .build(consumer);
    }

    private void registerBarrels(Consumer<IFinishedRecipe> consumer) {
        registerBarrel(ModBlocks.BARREL_STONE.get(), Blocks.STONE, Blocks.STONE_SLAB, consumer);
        registerBarrel(ModBlocks.BARREL_WOOD.get(), ItemTags.PLANKS, ItemTags.WOODEN_SLABS, consumer);
    }

    private void registerBarrel(Block result, ITag.INamedTag<Item> walls, ITag.INamedTag<Item> base, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("x x")
            .patternLine("x x")
            .patternLine("x-x")
            .key('x', walls)
            .key('-', base)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_walls", hasItem(walls))
            .addCriterion("has_base", hasItem(base))
            .build(consumer);
    }

    private void registerBarrel(Block result, Block walls, Block base, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("x x")
            .patternLine("x x")
            .patternLine("x-x")
            .key('x', walls)
            .key('-', base)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_walls", InventoryChangeTrigger.Instance.forItems(walls))
            .addCriterion("has_base", InventoryChangeTrigger.Instance.forItems(base))
            .build(consumer);
    }

    private void registerPebbleBlocks(Consumer<IFinishedRecipe> consumer) {
        registerPebbleBlock(Blocks.ANDESITE, EnumPebbleType.ANDESITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.COBBLESTONE, EnumPebbleType.STONE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.DIORITE, EnumPebbleType.DIORITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.GRANITE, EnumPebbleType.GRANITE.getRegistryObject().get(), consumer);
    }

    private void registerPebbleBlock(Block result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("xx")
            .patternLine("xx")
            .key('x', input)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_pebble", InventoryChangeTrigger.Instance.forItems(input))
            .build(consumer);
    }

    private void registerCrooks(Consumer<IFinishedRecipe> consumer) {
        registerCrook(EnumCrook.ANDESITE.getRegistryObject().get(), EnumPebbleType.ANDESITE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.BONE.getRegistryObject().get(), Items.BONE, consumer);
        registerCrook(EnumCrook.DIAMOND.getRegistryObject().get(), Items.DIAMOND, consumer);
        registerCrook(EnumCrook.DIORITE.getRegistryObject().get(), EnumPebbleType.DIORITE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.GOLD.getRegistryObject().get(), Items.GOLD_NUGGET, consumer);
        registerCrook(EnumCrook.GRANITE.getRegistryObject().get(), EnumPebbleType.GRANITE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.IRON.getRegistryObject().get(), Items.IRON_NUGGET, consumer);
        registerCrook(EnumCrook.STONE.getRegistryObject().get(), EnumPebbleType.STONE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.WOOD.getRegistryObject().get(), Items.STICK, consumer);
    }

    private void registerCrook(Item result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("xx")
            .patternLine(" x")
            .patternLine(" x")
            .key('x', input)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_pebble", InventoryChangeTrigger.Instance.forItems(input))
            .build(consumer);
    }
}
