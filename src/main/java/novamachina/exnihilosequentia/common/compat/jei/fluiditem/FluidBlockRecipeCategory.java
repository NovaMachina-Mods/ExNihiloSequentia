package novamachina.exnihilosequentia.common.compat.jei.fluiditem;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidBlockRecipeCategory implements IRecipeCategory<FluidItemRecipe> {
    @Nonnull public static final ResourceLocation UID = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "fluiditem");
    @Nonnull private static final ResourceLocation texture = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_block_transform.png");
    @Nonnull private final IDrawableStatic background;

    public FluidBlockRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 0, 166, 63);
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Nonnull
    @Override
    public Class<? extends FluidItemRecipe> getRecipeClass() {
        return FluidItemRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return "Fluid Item Transformation";
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(@Nonnull final FluidItemRecipe recipe, @Nonnull final IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setInput(VanillaTypes.FLUID, recipe.getFluidInBarrel());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final FluidItemRecipe recipe,
                          @Nonnull final IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 48, 37);
        recipeLayout.getItemStacks().init(0, true, 74, 9);
        recipeLayout.getItemStacks().init(1, false, 101, 36);

        recipeLayout.getFluidStacks().set(0, recipe.getFluidInBarrel());
        recipeLayout.getItemStacks().set(0, recipe.getInputs());
        recipeLayout.getItemStacks().set(1, recipe.getResultItem());
    }
}
