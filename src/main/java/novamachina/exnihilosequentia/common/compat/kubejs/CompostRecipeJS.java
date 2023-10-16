package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface CompostRecipeJS {
  RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
  RecipeKey<Integer> AMOUNT = NumberComponent.INT.key("amount");

  RecipeSchema SCHEMA = new RecipeSchema(INPUT, AMOUNT);
}
