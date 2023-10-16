package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface PrecipitateRecipeJS {
  RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
  RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
  RecipeKey<InputFluid> FLUID = FluidComponents.INPUT.key("fluid");

  RecipeSchema SCHEMA = new RecipeSchema(INPUT, RESULT, FLUID);
}
