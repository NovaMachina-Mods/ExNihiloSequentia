package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.recipe.IngredientMatch;
import dev.latvian.mods.kubejs.recipe.ItemInputTransformer;
import dev.latvian.mods.kubejs.recipe.ItemOutputTransformer;
import dev.latvian.mods.kubejs.recipe.RecipeArguments;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import java.util.Locale;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;

public class CrucibleRecipeJS extends RecipeJS {

  private Ingredient input;
  private CrucibleTypeEnum crucibleType;
  private int amount;
  private FluidStackJS fluidResult;

  @Override
  public void create(RecipeArguments args) {
    this.input = this.parseItemInput(args.get(0));
    this.crucibleType = crucibleType(args.getString(1, ""));
    this.amount = args.getInt(2, 0);
    this.fluidResult = FluidStackJS.of(args.get(3));
  }

  private CrucibleTypeEnum crucibleType(String type) {
    if (!validCrucibleType(type)) {
      throw new RecipeExceptionJS("Crucible recipe must have a crucible type of 'wood' or 'fired'");
    }
    return CrucibleTypeEnum.getTypeByName(type);
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
    this.input = this.parseItemInput(this.json.get("input"));
    this.crucibleType = CrucibleTypeEnum.getTypeByName(this.json.get("crucibleType").getAsString());
    this.amount = this.json.get("amount").getAsInt();
    this.fluidResult = FluidStackJS.fromJson(this.json.get("fluidResult"));
  }

  @Override
  public void serialize() {
    if (this.serializeInputs) {
      this.json.add("input", this.input.toJson());
    }
    this.json.addProperty("crucibleType", this.crucibleType.getName());
    this.json.addProperty("amount", this.amount);
    this.json.add("fluidResult", this.fluidResult.toJson());
  }

  @Override
  public boolean hasInput(IngredientMatch ingredientMatch) {
    return ingredientMatch.contains(this.input);
  }

  @Override
  public boolean replaceInput(
      IngredientMatch ingredientMatch,
      Ingredient ingredient,
      ItemInputTransformer itemInputTransformer) {
    if (ingredientMatch.contains(this.input)) {
      this.input = itemInputTransformer.transform(this, ingredientMatch, this.input, ingredient);
      return true;
    } else {
      return false;
    }
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
