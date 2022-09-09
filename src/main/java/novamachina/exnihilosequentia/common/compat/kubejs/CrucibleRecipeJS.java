package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import java.util.Locale;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;

public class CrucibleRecipeJS extends BaseRecipeJS {

  @Override
  public void create(RecipeArguments args) {
    this.inputItems.add(this.parseIngredientItem(args.get(0)));
    this.crucibleType(args.get(1));
    this.amount(args.getInt(2, 0));
    this.fluidResult(FluidStackJS.of(args.get(3)));
  }

  private void fluidResult(FluidStackJS fluidStack) {
    JsonObject object = new JsonObject();
    object.addProperty("fluid", fluidStack.getId());
    this.json.add("fluidResult", object);
  }

  private void amount(int intValue) {
    this.json.addProperty("amount", intValue);
  }

  private void crucibleType(Object o) {
    if (!(o instanceof String type) || !validCrucibleType((String) o)) {
      throw new RecipeExceptionJS("Crucible recipe must have a crucible type of 'wood' or 'fired'");
    }
    this.json.addProperty("crucibleType", type.toLowerCase(Locale.ROOT));
  }

  private boolean validCrucibleType(String name) {
    for (CrucibleTypeEnum type : CrucibleTypeEnum.values()) {
      if (type.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void deserialize() {
    this.inputItems.add(this.parseIngredientItem(this.json.get("input")));
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("input", this.inputItems.get(0).toJson());
    }
  }
}
