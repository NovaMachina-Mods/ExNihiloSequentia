package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface TransitionRecipeJS {
  RecipeKey<InputItem> CATALYST = ItemComponents.INPUT.key("catalyst");
  RecipeKey<InputFluid> FLUID_IN_TANK = FluidComponents.INPUT.key("fluidInTank");
  RecipeKey<OutputFluid> RESULT = FluidComponents.OUTPUT.key("result");

  RecipeSchema SCHEMA = new RecipeSchema(FLUID_IN_TANK, RESULT, CATALYST);
}
