package novamachina.exnihilosequentia.common.compat.jei.crook;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class CrookTooltipCallback implements IRecipeSlotTooltipCallback {

  @Nonnull private final CrookRecipe crookRecipe;

  public CrookTooltipCallback(@Nonnull final CrookRecipe crookRecipe) {
    this.crookRecipe = crookRecipe;
  }

  @Override
  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
    if (recipeSlotView.getRole() == RecipeIngredientRole.OUTPUT) {
      crookRecipe.getOutput().stream()
          .filter(
              stack -> {
                Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
                AtomicBoolean returnValue = new AtomicBoolean(false);
                optional.ifPresent(
                    iTypedIngredient ->
                        returnValue.set(
                            ItemStack.isSame(
                                (ItemStack) iTypedIngredient.getIngredient(), stack.getStack())));
                return returnValue.get();
              })
          .forEach(
              stack ->
                  tooltip.add(
                      Component.literal(
                          String.format("%s", StringUtils.formatPercent(stack.getChance())))));
    }
  }
}
