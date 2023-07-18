package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.BlockComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.compat.kubejs.component.StatePropertiesPredicateComponent;

public interface HeatRecipeJS {
  RecipeKey<Block> BLOCK = BlockComponent.BLOCK.key("block");
  RecipeKey<Integer> AMOUNT = NumberComponent.INT.key("amount");
  RecipeKey<StatePropertiesPredicate> STATE =
      new StatePropertiesPredicateComponent().key("state").optional(StatePropertiesPredicate.ANY);
  RecipeSchema SCHEMA = new RecipeSchema(BLOCK, AMOUNT, STATE);
}
