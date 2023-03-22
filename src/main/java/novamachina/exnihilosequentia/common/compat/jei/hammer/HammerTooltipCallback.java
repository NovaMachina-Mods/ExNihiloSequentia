package novamachina.exnihilosequentia.common.compat.jei.hammer;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.utility.StringUtils;

 public class HammerTooltipCallback implements IRecipeSlotTooltipCallback {

  @Nonnull private final HammerRecipe hammerRecipe;

  public HammerTooltipCallback(@Nonnull final HammerRecipe hammerRecipe) {
    this.hammerRecipe = hammerRecipe;
  }

  @Override
  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
    if (recipeSlotView.getRole() == RecipeIngredientRole.OUTPUT) {
      Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
      optional.ifPresent(
          iTypedIngredient ->
              hammerRecipe.getOutput().stream()
                  .filter(
                      stack ->
                          ItemStack.isSame(
                              (ItemStack) iTypedIngredient.getIngredient(), stack.getStack()))
                  .forEach(
                      stack ->
                          tooltip.add(
                              Component.literal(
                                  String.format(
                                      "%s", StringUtils.formatPercent(stack.getChance()))))));
    }
  }
 }
