package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface FluidOnTopRecipeJS {
  RecipeKey<InputFluid> FLUID_IN_TANK = FluidComponents.INPUT.key("fluidInTank");
  RecipeKey<InputFluid> FLUID_ON_TOP = FluidComponents.INPUT.key("fluidOnTop");
  RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");

  RecipeSchema SCHEMA = new RecipeSchema(FLUID_IN_TANK, FLUID_ON_TOP, RESULT);
}
