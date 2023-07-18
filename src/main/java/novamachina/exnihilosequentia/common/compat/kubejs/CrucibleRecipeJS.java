package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;

public interface CrucibleRecipeJS {
  RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
  RecipeKey<CrucibleTypeEnum> CRUCIBLE_TYPE =
      new EnumComponent<>(
              CrucibleTypeEnum.class,
              e -> e.name().toLowerCase(),
              (c, s) -> {
                for (var e : c.getEnumConstants()) {
                  if (e.name().equalsIgnoreCase(s.replaceAll("\"", ""))) {
                    return e;
                  }
                }

                return null;
              })
          .key("crucibleType");
  RecipeKey<Integer> AMOUNT = NumberComponent.INT.key("amount");
  RecipeKey<OutputFluid> FLUID_RESULT = FluidComponents.OUTPUT.key("fluidResult");

  RecipeSchema SCHEMA = new RecipeSchema(INPUT, CRUCIBLE_TYPE, AMOUNT, FLUID_RESULT);
}
