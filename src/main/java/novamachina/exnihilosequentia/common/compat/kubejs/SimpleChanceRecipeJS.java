package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface SimpleChanceRecipeJS {
  RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");

  RecipeComponentBuilder DROP_WITH_CHANCE_ENTRY =
      new RecipeComponentBuilder(3)
          .add(NumberComponent.FLOAT.key("chance"))
          .add(NumberComponent.INT.key("count"))
          .add(StringComponent.ID.key("item"));

  RecipeKey<RecipeComponentBuilderMap[]> RESULTS = DROP_WITH_CHANCE_ENTRY.asArray().key("results");

  RecipeSchema SCHEMA = new RecipeSchema(INPUT, RESULTS);
}
