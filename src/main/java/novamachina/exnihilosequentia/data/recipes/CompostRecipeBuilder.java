package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.novacore.data.recipes.RecipeBuilder;
import org.jetbrains.annotations.Nullable;

public class CompostRecipeBuilder extends RecipeBuilder<CompostRecipeBuilder> {
  private final Ingredient input;
  private final int amount;

  protected CompostRecipeBuilder(Ingredient input, int amount) {
    super(EXNRecipeSerializers.COMPOST_RECIPE_SERIALIZER.recipeSerializer());
    this.input = input;
    this.amount = amount;
  }

  public static CompostRecipeBuilder composting(ItemLike inputItem, int amount) {
    return composting(Ingredient.of(inputItem), amount);
  }

  public static CompostRecipeBuilder composting(TagKey<Item> inputTag, int amount) {
    return composting(Ingredient.of(inputTag), amount);
  }

  public static CompostRecipeBuilder composting(Ingredient input, int amount) {
    return new CompostRecipeBuilder(input, amount);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(input, "Input cannot be null.");
    Preconditions.checkArgument(amount > 0, "Amount must be greater than 0.");
  }

  @Override
  protected CompostRecipeResult getResult(ResourceLocation id) {
    return new CompostRecipeResult(id);
  }

  public class CompostRecipeResult extends RecipeResult {

    public CompostRecipeResult(ResourceLocation id) {
      super(id);
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
      json.add("input", input.toJson(false));
      json.addProperty("amount", amount);
    }

    @Nullable
    @Override
    public AdvancementHolder advancement() {
      return null;
    }
  }
}
