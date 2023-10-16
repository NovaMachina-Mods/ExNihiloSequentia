package novamachina.exnihilosequentia.common.compat.crafttweaker.handler;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent;
import com.blamejared.crafttweaker.api.recipe.component.RecipeComponentEqualityCheckers;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.google.gson.reflect.TypeToken;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;

@IRecipeHandler.For(CompostRecipe.class)
public class CompostRecipeHandler implements IRecipeHandler<CompostRecipe> {
  @Override
  public String dumpToCommandString(
      IRecipeManager<? super CompostRecipe> manager, CompostRecipe recipe) {
    return String.format(
        "<recipetype:exnihilosequentia:compost>.addRecipe(%s, %s, %d);",
        StringUtil.quoteAndEscape(recipe.getId()),
        IIngredient.fromIngredient(recipe.getInput()).getCommandString(),
        recipe.getAmount());
  }

  @Override
  public <U extends Recipe<?>> boolean doesConflict(
      IRecipeManager<? super CompostRecipe> manager, CompostRecipe firstRecipe, U secondRecipe) {
    CompostRecipe second = (CompostRecipe) secondRecipe;
    return IngredientUtil.canConflict(firstRecipe.getInput(), second.getInput());
  }

  IRecipeComponent<Integer> OUTPUT_AMOUNT =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "output/amount"),
          new TypeToken<>() {},
          RecipeComponentEqualityCheckers::areNumbersEqual);

  @Override
  public Optional<IDecomposedRecipe> decompose(
      IRecipeManager<? super CompostRecipe> manager, CompostRecipe recipe) {
    IIngredient ingredient = IIngredient.fromIngredient(recipe.getInput());
    IDecomposedRecipe decomposition =
        IDecomposedRecipe.builder()
            .with(BuiltinRecipeComponents.Input.INGREDIENTS, ingredient)
            .with(OUTPUT_AMOUNT, recipe.getAmount())
            .build();
    return Optional.of(decomposition);
  }

  @Override
  public Optional<CompostRecipe> recompose(
      IRecipeManager<? super CompostRecipe> manager,
      ResourceLocation name,
      IDecomposedRecipe recipe) {
    IIngredient input = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS);
    int amount = recipe.getOrThrowSingle(OUTPUT_AMOUNT);

    if (input.isEmpty()) {
      throw new IllegalArgumentException("Invalid input: empty ingredient");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Invalid amount: less than min allowed 1: " + amount);
    }
    return Optional.of(new CompostRecipe(name, input.asVanillaIngredient(), amount));
  }
}
