package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class FluidItemRecipeJS extends BaseRecipeJS {

  @Override
  public void create(ListJS args) {
    this.inputItems.add(this.parseIngredientItem(args.get(0)));
    this.outputItems.add(this.parseResultItem(args.get(1)));
    this.fluid(FluidStackJS.of(args.get(2)));
  }

  private void fluid(FluidStackJS fluidStack) {
    JsonObject object = new JsonObject();
    object.addProperty("fluid", fluidStack.getId());
    this.json.add("fluid", object);
  }
}
