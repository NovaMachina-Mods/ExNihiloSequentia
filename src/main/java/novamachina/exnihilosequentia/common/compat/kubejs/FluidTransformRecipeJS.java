package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class FluidTransformRecipeJS extends BaseRecipeJS {

  @Override
  public void create(ListJS args) {
    this.fluidInTank((FluidStackJS) args.get(0));
    this.result((FluidStackJS) args.get(1));
    this.catalyst(this.parseIngredientItem(args.get(2)));
  }

  private void catalyst(IngredientJS catalyst) {
    this.json.add("catalyst", catalyst.toJson());
  }

  private void fluidInTank(FluidStackJS fluidStack) {
    JsonObject object = new JsonObject();
    object.addProperty("fluid", fluidStack.getId());
    this.json.add("fluidInTank", object);
  }

  private void result(FluidStackJS fluidStack) {
    JsonObject object = new JsonObject();
    object.addProperty("fluid", fluidStack.getId());
    this.json.add("result", object);
  }

  @Override
  public void deserialize() {

  }

  @Override
  public void serialize() {

  }
}
