package novamachina.exnihilosequentia.common.compat.jei.precipitate;

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
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;

public class PrecipitateRecipeCategory implements IRecipeCategory<PrecipitateRecipe> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "precipitate");

  @Nonnull
  private static final ResourceLocation texture =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
          "textures/gui/jei_fluid_block_transform.png");

  @Nonnull private final IDrawableStatic background;

  public PrecipitateRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
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

  @Override
  public RecipeType<PrecipitateRecipe> getRecipeType() {
    return new RecipeType<>(UID, PrecipitateRecipe.class);
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.precipitate");
  }

  @Override
  public void setRecipe(
      IRecipeLayoutBuilder builder, PrecipitateRecipe recipe, IFocusGroup focuses) {
    builder
        .addSlot(RecipeIngredientRole.INPUT, 48, 37)
        .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluid());
    builder.addSlot(RecipeIngredientRole.INPUT, 75, 10).addIngredients(recipe.getInput());
    builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 37).addItemStack(recipe.getOutput());
  }
}
