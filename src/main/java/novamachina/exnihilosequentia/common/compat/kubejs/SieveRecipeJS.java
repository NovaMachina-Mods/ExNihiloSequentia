package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;

public interface SieveRecipeJS {
  RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
  RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");

  RecipeComponentBuilder ROLL_ENTRY =
      new RecipeComponentBuilder(2)
          .add(NumberComponent.FLOAT.key("chance"))
          .add(
              new EnumComponent<>(
                      MeshType.class,
                      e -> e.name().toLowerCase(),
                      (c, s) -> {
                        for (var e : c.getEnumConstants()) {
                          if (e.name().equalsIgnoreCase(s.replaceAll("\"", ""))) {
                            return e;
                          }
                        }

                        return null;
                      })
                  .key("mesh"));

  RecipeKey<RecipeComponentBuilderMap[]> ROLLS = ROLL_ENTRY.asArray().key("rolls");

  RecipeKey<Boolean> IS_WATERLOGGED = BooleanComponent.BOOLEAN.key("waterlogged").defaultOptional();

  RecipeSchema SCHEMA = new RecipeSchema(INPUT, RESULT, ROLLS, IS_WATERLOGGED);
}
