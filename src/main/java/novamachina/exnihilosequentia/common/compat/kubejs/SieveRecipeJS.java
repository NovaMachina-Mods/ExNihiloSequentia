package novamachina.exnihilosequentia.common.compat.kubejs;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import java.util.Locale;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;

public class SieveRecipeJS extends BaseRecipeJS {

  @Override
  public void create(RecipeArguments args) {
    this.inputItems.add(this.parseIngredientItem(args.get(0)));
    this.outputItems.add(this.parseResultItem(args.get(1)));
  }

  public SieveRecipeJS addRoll(float chance, String mesh) {
    if (!isValidMesh(mesh)) {
      throw new RecipeExceptionJS("Mesh type must be " + MeshType.printList());
    }
    if (chance < 0.0F || chance > 1.0F) {
      throw new RecipeExceptionJS("Chance must be in range of 0.0 - 1.0");
    }
    if (this.json.has("rolls")) {
      JsonObject object = new JsonObject();
      object.addProperty("chance", chance);
      object.addProperty("mesh", mesh.toLowerCase(Locale.ROOT));
      this.json.get("rolls").getAsJsonArray().add(object);
    } else {
      JsonArray array = new JsonArray();
      JsonObject object = new JsonObject();
      object.addProperty("chance", chance);
      object.addProperty("mesh", mesh.toLowerCase(Locale.ROOT));
      array.add(object);
      this.json.add("rolls", array);
    }
    return this;
  }

  public SieveRecipeJS setWaterlogged() {
    this.json.addProperty("waterlogged", true);
    return this;
  }

  private boolean isValidMesh(String mesh) {
    for (MeshType type : MeshType.values()) {
      if (type.name().toLowerCase(Locale.ROOT).equals(mesh.toLowerCase(Locale.ROOT))) {
        return true;
      }
    }
    return false;
  }
}
