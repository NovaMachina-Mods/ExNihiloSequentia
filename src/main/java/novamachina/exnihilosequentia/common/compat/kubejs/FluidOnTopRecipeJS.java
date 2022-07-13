package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class FluidOnTopRecipeJS extends BaseRecipeJS {

  @Override
  public void create(ListJS args) {
    this.fluidInTank(FluidStackJS.of(args.get(0)));
    this.fluidOnTop(FluidStackJS.of(args.get(1)));
    this.outputItems.add(this.parseResultItem(args.get(2)));
  }

  private void fluidInTank(FluidStackJS fluidStack) {
    JsonObject object = new JsonObject();
    object.addProperty("fluid", fluidStack.getId());
    this.json.add("fluidInTank", object);
  }

  private void fluidOnTop(FluidStackJS fluidStack) {
    JsonObject object = new JsonObject();
    object.addProperty("fluid", fluidStack.getId());
    this.json.add("fluidOnTop", object);
  }

  @Override
  public void deserialize() {
    this.outputItems.add(this.parseResultItem(this.json.get("result")));
  }

  @Override
  public void serialize() {
    if(this.serializeOutputs) {
      this.json.add("result", this.outputItems.get(0).toJson());
    }
  }
}
