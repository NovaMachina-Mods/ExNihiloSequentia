package novamachina.exnihilosequentia.common.compat.jei.fluidtransform;

import java.util.ArrayList;
import java.util.Arrays;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidTransformCategory implements IRecipeCategory<FluidTransformRecipe> {
    @Nonnull public static final ResourceLocation UID =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "fluidtransform");
    @Nonnull private static final ResourceLocation texture =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_transform.png");
    @Nonnull private final IDrawableStatic background;

    public FluidTransformCategory(@Nonnull final IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 0, 166, 63);
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    @Nullable
    public IDrawable getIcon() {
        return null;
    }

    @Nonnull
    @Override
    public Class<? extends FluidTransformRecipe> getRecipeClass() {
        return FluidTransformRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return "Fluid Transform";
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(@Nonnull final FluidTransformRecipe recipe, @Nonnull final IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, recipe.getFluidInTank());
        ingredients.setInputs(VanillaTypes.ITEM, new ArrayList<>(Arrays.asList(recipe.getCatalyst().getItems())));
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getResult());
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final FluidTransformRecipe recipe,
                          @Nonnull final IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 48, 10);
        recipeLayout.getItemStacks().init(0, true, 74, 36);
        recipeLayout.getFluidStacks().init(1, false, 102, 10);

        recipeLayout.getFluidStacks().set(0, recipe.getFluidInTank());
        recipeLayout.getItemStacks().set(0, new ArrayList<>(Arrays.asList(recipe.getCatalyst().getItems())));
        recipeLayout.getFluidStacks().set(1, recipe.getResult());
    }
}
