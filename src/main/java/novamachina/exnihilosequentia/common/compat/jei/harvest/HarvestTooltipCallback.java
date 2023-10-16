package novamachina.exnihilosequentia.common.compat.jei.harvest;

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
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.novacore.util.StringUtils;

public class HarvestTooltipCallback implements IRecipeSlotTooltipCallback {

  @Nonnull private final HarvestRecipe harvestRecipe;

  public HarvestTooltipCallback(@Nonnull final HarvestRecipe harvestRecipe) {
    this.harvestRecipe = harvestRecipe;
  }

  @Override
  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
    if (recipeSlotView.getRole() == RecipeIngredientRole.OUTPUT) {
      harvestRecipe.getDrops().stream()
          .filter(
              stack -> {
                Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
                AtomicBoolean returnValue = new AtomicBoolean(false);
                optional.ifPresent(
                    iTypedIngredient ->
                        returnValue.set(
                            ItemStack.isSameItem(
                                iTypedIngredient.getItemStack().get(), stack.getStack())));
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
