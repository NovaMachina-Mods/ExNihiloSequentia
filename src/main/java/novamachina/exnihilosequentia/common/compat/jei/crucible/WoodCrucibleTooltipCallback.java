package novamachina.exnihilosequentia.common.compat.jei.crucible;
//
//import java.util.List;
//import java.util.Optional;
//import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
//import mezz.jei.api.gui.ingredient.IRecipeSlotView;
//import mezz.jei.api.ingredients.ITypedIngredient;
//import mezz.jei.api.recipe.RecipeIngredientRole;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.TextComponent;
//import net.minecraft.world.item.ItemStack;
//import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
//import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
//
//public class WoodCrucibleTooltipCallback implements IRecipeSlotTooltipCallback {
//  @Override
//  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
//    if (recipeSlotView.getRole() == RecipeIngredientRole.INPUT) {
//      Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
//      if (optional.isPresent()) {
//        Optional<CrucibleRecipe> recipeOptional =
//            ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(
//                (ItemStack) optional.get().getIngredient());
//        if (recipeOptional.isPresent()) {
//          CrucibleRecipe recipe = recipeOptional.get();
//          tooltip.add(new TextComponent(String.format("Fluid Amount: %d mb", recipe.getAmount())));
//        }
//      }
//    }
//  }
//}
