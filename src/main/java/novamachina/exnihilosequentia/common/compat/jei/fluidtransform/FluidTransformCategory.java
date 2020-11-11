package novamachina.exnihilosequentia.common.compat.jei.fluidtransform;

import novamachina.exnihilosequentia.common.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;

public class FluidTransformCategory implements IRecipeCategory<FluidTransformRecipe> {
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_transform.png");
    public static ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "fluidtransform");
    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public FluidTransformCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 0, 166, 63);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends FluidTransformRecipe> getRecipeClass() {
        return FluidTransformRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Fluid Transform";
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
    public void setIngredients(FluidTransformRecipe recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, recipe.getFluidInTank());
        ingredients.setInputs(VanillaTypes.ITEM, new ArrayList<>(Arrays.asList(recipe.getBlockBelow().getMatchingStacks())));
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getResult());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FluidTransformRecipe recipe, IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 48, 10);
        recipeLayout.getItemStacks().init(0, true, 74, 36);
        recipeLayout.getFluidStacks().init(1, false, 102, 10);

        recipeLayout.getFluidStacks().set(0, recipe.getFluidInTank());
        recipeLayout.getItemStacks().set(0, new ArrayList<>(Arrays.asList(recipe.getBlockBelow().getMatchingStacks())));
        recipeLayout.getFluidStacks().set(1, recipe.getResult());
    }
}
