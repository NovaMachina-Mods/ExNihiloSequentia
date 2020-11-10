package com.novamachina.exnihilosequentia.common.compat.jei.heat;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novamachina.exnihilosequentia.common.api.crafting.heat.HeatRecipe;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.Arrays;

public class HeatRecipeCategory implements IRecipeCategory<HeatRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "heat");
    private final IDrawableStatic background;

    public HeatRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper
            .drawableBuilder(new ResourceLocation(Constants.ModIds.JEI, "textures/gui/gui_vanilla.png"),
                0, 134, 18, 34).addPadding(0, 0, 0, 80).build();
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends HeatRecipe> getRecipeClass() {
        return HeatRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Crucible Heat Sources";
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setIngredients(HeatRecipe recipe, IIngredients ingredients) {
        if(ForgeRegistries.FLUIDS.containsKey(recipe.getInput().getRegistryName())) {
            ingredients.setInput(VanillaTypes.FLUID, new FluidStack(ForgeRegistries.FLUIDS.getValue(recipe.getInput().getRegistryName()), FluidAttributes.BUCKET_VOLUME));
        } else {
            ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(Ingredient.fromItems(recipe.getInput()).getMatchingStacks()));
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, HeatRecipe recipe, IIngredients ingredients) {
        if(ForgeRegistries.FLUIDS.containsKey(recipe.getInput().getRegistryName())) {
            recipeLayout.getFluidStacks().init(0, true, 1, 17);
            recipeLayout.getFluidStacks().set(0, new FluidStack(ForgeRegistries.FLUIDS.getValue(recipe.getInput().getRegistryName()), FluidAttributes.BUCKET_VOLUME));
        } else {
            recipeLayout.getItemStacks().init(0, true, 0, 16);
            recipeLayout.getItemStacks().set(0, Arrays.asList(Ingredient.fromItems(recipe.getInput()).getMatchingStacks()));
        }
    }

    @Override
    public void draw(HeatRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        Minecraft.getInstance().fontRenderer
            .drawString(matrixStack, recipe.getAmount() + "X", 24, 12, Color.gray.getRGB());
    }
}
