package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.util.MapJS;
import java.util.Map;

public class HeatRecipeJS extends BaseRecipeJS {

  @Override
  public void create(RecipeArguments args) {
    this.source(ItemStackJS.of(args.get(0)));
    this.amount(((Number) args.get(1)).intValue());
    this.properties(MapJS.of(args.get(2)));
  }

  private void properties(Map<?, ?> jsonObject) {
    this.json.add("state", MapJS.json(jsonObject));
  }

  private void source(ItemStackJS block) {
    this.json.addProperty("block", block.getId());
  }

  private void amount(int amount) {
    this.json.addProperty("amount", amount);
  }

  @Override
  public void deserialize() {}

  @Override
  public void serialize() {}
}
