package novamachina.exnihilosequentia.common.compat.jei.fluidontop;

import java.util.Arrays;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidOnTopRecipeCategory implements IRecipeCategory<FluidOnTopRecipe> {
    @Nonnull public static final ResourceLocation UID =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "fluidontop");
    @Nonnull private static final ResourceLocation texture =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_on_top.png");
    @Nonnull private final IDrawableStatic background;

    public FluidOnTopRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
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
    public Class<? extends FluidOnTopRecipe> getRecipeClass() {
        return FluidOnTopRecipe.class;
    }

    @Nonnull
    @Override
    public Component getTitle() {
        return new TextComponent("Fluid On Top");
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(@Nonnull final FluidOnTopRecipe recipe, @Nonnull final IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.FLUID, Arrays.asList(recipe.getFluidInTank(), recipe.getFluidOnTop()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final FluidOnTopRecipe recipe,
                          @Nonnull final IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 48, 37);
        recipeLayout.getFluidStacks().init(1, true, 75, 10);
        recipeLayout.getItemStacks().init(0, false, 101, 36);

        recipeLayout.getFluidStacks().set(0, recipe.getFluidInTank());
        recipeLayout.getFluidStacks().set(1, recipe.getFluidOnTop());
        recipeLayout.getItemStacks().set(0, recipe.getResultItem());
    }
}
