package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.block.BaseBlock;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

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
        registerCrook(EnumCrook.ANDESITE.getRegistryObject().get(), EnumPebbleType.ANDESITE.getRegistryObject().get(), consumer);
        registerCrook(EnumCrook.BONE.getRegistryObject().get(), Items.BONE, consumer);
        registerCrook(EnumCrook.DIAMOND.getRegistryObject().get(), Items.DIAMOND, consumer);
        registerCrook(EnumCrook.DIORITE.getRegistryObject().get(), EnumPebbleType.DIORITE.getRegistryObject().get(), consumer);
        registerCrook(EnumCrook.GOLD.getRegistryObject().get(), Items.GOLD_NUGGET, consumer);
        registerCrook(EnumCrook.GRANITE.getRegistryObject().get(), EnumPebbleType.GRANITE.getRegistryObject().get(), consumer);
        registerCrook(EnumCrook.IRON.getRegistryObject().get(), Items.IRON_NUGGET, consumer);
        registerCrook(EnumCrook.STONE.getRegistryObject().get(), EnumPebbleType.STONE.getRegistryObject().get(), consumer);
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
