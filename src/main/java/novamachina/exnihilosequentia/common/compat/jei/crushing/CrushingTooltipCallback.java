package novamachina.exnihilosequentia.common.compat.jei.crushing;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.novacore.util.StringUtils;

public class CrushingTooltipCallback implements IRecipeSlotTooltipCallback {

  @Nonnull private final CrushingRecipe crushingRecipe;

  public CrushingTooltipCallback(@Nonnull final CrushingRecipe crushingRecipe) {
    this.crushingRecipe = crushingRecipe;
  }

  @Override
  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
    if (recipeSlotView.getRole() == RecipeIngredientRole.OUTPUT) {
      Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
      optional.ifPresent(
          iTypedIngredient ->
              crushingRecipe.getDrops().stream()
                  .filter(
                      stack ->
                          ItemStack.isSameItem(
                              iTypedIngredient.getItemStack().get(), stack.getStack()))
                  .forEach(
                      stack ->
                          tooltip.add(
                              Component.literal(
                                  String.format(
                                      "%s", StringUtils.formatPercent(stack.getChance()))))));
    }
  }
}
