package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.MapJS;
import java.util.Arrays;
import java.util.Map;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class HeatRecipeJS extends RecipeJS {

  private Block block;
  private int amount;
  private Map<?, ?> state;

  @Override
  public void create(RecipeArguments args) {
    this.block =
        Block.byItem(
            Arrays.stream(IngredientJS.of(args.get(0)).getItems()).findFirst().get().getItem());
    this.amount = args.getInt(1, 0);
    this.state = MapJS.of(args.get(2));
  }

  @Override
  public void deserialize() {
    this.block =
        Block.byItem(
            Arrays.stream(IngredientJS.of(this.json.get("block")).getItems())
                .findFirst()
                .get()
                .getItem());
    this.amount = this.json.get("amount").getAsInt();
    this.state = MapJS.of(this.json.get("state"));
  }

  @Override
  public void serialize() {
    this.json.addProperty("block", ForgeRegistries.BLOCKS.getKey(this.block).toString());
    this.json.addProperty("amount", this.amount);
    this.json.add("state", MapJS.json(this.state));
  }

  @Override
  public boolean hasInput(IngredientMatch ingredientMatch) {
    return false;
  }

  @Override
  public boolean replaceInput(
      IngredientMatch ingredientMatch,
      Ingredient ingredient,
      ItemInputTransformer itemInputTransformer) {
    return false;
  }

  @Override
  public boolean hasOutput(IngredientMatch ingredientMatch) {
    return false;
  }

  @Override
  public boolean replaceOutput(
      IngredientMatch ingredientMatch,
      ItemStack itemStack,
      ItemOutputTransformer itemOutputTransformer) {
    return false;
  }
}
