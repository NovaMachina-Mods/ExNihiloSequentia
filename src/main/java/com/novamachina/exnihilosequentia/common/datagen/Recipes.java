package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        registerCrooks(consumer);
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
