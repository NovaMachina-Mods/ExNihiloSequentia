package novamachina.exnihilosequentia.common.compat.jei.melting;

import java.util.List;
import java.util.Optional;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;

public class FiredCrucibleTooltipCallback implements IRecipeSlotTooltipCallback {

  @Override
  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
    if (recipeSlotView.getRole() == RecipeIngredientRole.INPUT) {
      Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
      if (optional.isPresent()) {
        Optional<MeltingRecipe> recipeOptional =
            ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(
                optional.get().getItemStack().get().getItem());
        if (recipeOptional.isPresent()) {
          MeltingRecipe recipe = recipeOptional.get();
          tooltip.add(
              Component.literal(
                  String.format("Fluid Amount: %d mb", recipe.getResultFluid().getAmount())));
        }
      }
    }
  }
}
