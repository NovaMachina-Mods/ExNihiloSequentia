package com.novamachina.exnihilosequentia.common.jei.fluidontop;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class FluidOnTopRecipeCategory implements IRecipeCategory<FluidOnTopJEIRecipe> {
    public static ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "fluidontop");
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_on_top.png");

    private final IDrawableStatic background;

    public FluidOnTopRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 0, 166, 63);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends FluidOnTopJEIRecipe> getRecipeClass() {
        return FluidOnTopJEIRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Fluid On Top";
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
    public void setIngredients(FluidOnTopJEIRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.FLUID, recipe.getInputs());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FluidOnTopJEIRecipe recipe, IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 48,37);
        recipeLayout.getFluidStacks().init(1, true, 75,10);
        recipeLayout.getItemStacks().init(0, false, 101,36);

        recipeLayout.getFluidStacks().set(0, recipe.getInputs().get(0));
        recipeLayout.getFluidStacks().set(1, recipe.getInputs().get(1));
        recipeLayout.getItemStacks().set(0, recipe.getOutputs().get(0));
    }
}
