package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.EnumComponent;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;

public interface MeltingRecipeJS {
  RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
  RecipeKey<CrucibleBlockEntity.CrucibleType> CRUCIBLE_TYPE =
      new EnumComponent<>(
              CrucibleBlockEntity.CrucibleType.class,
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
  RecipeKey<OutputFluid> FLUID_RESULT = FluidComponents.OUTPUT.key("fluidResult");

  RecipeSchema SCHEMA = new RecipeSchema(INPUT, CRUCIBLE_TYPE, FLUID_RESULT);
}
