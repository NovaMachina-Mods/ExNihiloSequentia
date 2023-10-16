package novamachina.exnihilosequentia.common.compat.jei.crushing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;

public class CrushingRecipeCategory implements IRecipeCategory<CrushingRecipe> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crushing");

  @Nonnull
  private static final ResourceLocation texture =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

  @Nonnull private final IDrawableStatic background;
  @Nonnull private final IDrawableStatic slotHighlight;

  public CrushingRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
    this.background = guiHelper.createDrawable(texture, 0, 56, 166, 58);
    this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
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
  public RecipeType<CrushingRecipe> getRecipeType() {
    return new RecipeType<>(UID, CrushingRecipe.class);
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.crushing");
  }

  @Override
  public void setRecipe(IRecipeLayoutBuilder builder, CrushingRecipe recipe, IFocusGroup focuses) {
    builder.addSlot(RecipeIngredientRole.INPUT, 11, 39).addIngredients(recipe.getInput());

    for (int i = 0; i < recipe.getOutputsWithoutChance().size(); i++) {
      final int slotX = 39 + (i % 7 * 18);
      final int slotY = 3 + i / 7 * 18;

      @Nonnull final ItemStack outputStack = recipe.getOutputsWithoutChance().get(i);
      builder
          .addSlot(RecipeIngredientRole.OUTPUT, slotX, slotY)
          .addItemStack(outputStack)
          .addTooltipCallback(new CrushingTooltipCallback(recipe));
    }
  }
}
