package novamachina.exnihilosequentia.common.compat.jei.fluidtransform;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class FluidTransformCategory implements IRecipeCategory<FluidTransformRecipe> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "fluidtransform");

  @Nonnull
  private static final ResourceLocation texture =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_transform.png");

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

  @Override
  public RecipeType<FluidTransformRecipe> getRecipeType() {
    return new RecipeType<>(UID, FluidTransformRecipe.class);
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.literal("Fluid Transform");
  }

  @Override
  public void setRecipe(
      IRecipeLayoutBuilder builder, FluidTransformRecipe recipe, IFocusGroup focuses) {
    builder
        .addSlot(RecipeIngredientRole.INPUT, 48, 10)
        .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidInTank());
    builder.addSlot(RecipeIngredientRole.INPUT, 75, 37).addIngredients(recipe.getCatalyst());
    builder
        .addSlot(RecipeIngredientRole.OUTPUT, 102, 10)
        .addIngredient(ForgeTypes.FLUID_STACK, recipe.getResult());
  }
}
