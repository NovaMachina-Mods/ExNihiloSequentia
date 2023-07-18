package novamachina.exnihilosequentia.common.compat.kubejs.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.rhino.NativeObject;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.util.GsonHelper;

public class StatePropertiesPredicateComponent
    implements RecipeComponent<StatePropertiesPredicate> {
  @Override
  public Class<?> componentClass() {
    return StatePropertiesPredicate.class;
  }

  @Override
  public JsonElement write(RecipeJS recipeJS, StatePropertiesPredicate statePropertiesPredicate) {
    return statePropertiesPredicate.serializeToJson();
  }

  @Override
  public StatePropertiesPredicate read(RecipeJS recipeJS, Object from) {
    if (from instanceof StatePropertiesPredicate b) {
      return b;
    } else if (from instanceof JsonObject json) {
      return StatePropertiesPredicate.fromJson(json);
    } else if (from instanceof NativeObject json) {
      return StatePropertiesPredicate.fromJson(GsonHelper.parse(json.toString(), true));
    } else {
      return null;
    }
  }
}
