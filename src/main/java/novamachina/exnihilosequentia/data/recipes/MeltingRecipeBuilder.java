package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;
import novamachina.novacore.data.recipes.RecipeBuilder;
import novamachina.novacore.util.FluidStackUtils;
import org.jetbrains.annotations.Nullable;

public class MeltingRecipeBuilder extends RecipeBuilder<MeltingRecipeBuilder> {

  private final Ingredient input;
  private final FluidStack result;
  private final CrucibleType type;

  protected MeltingRecipeBuilder(Ingredient input, FluidStack result, CrucibleType type) {
    super(EXNRecipeSerializers.MELTING_RECIPE_SERIALIZER.recipeSerializer());
    this.input = input;
    this.result = result;
    this.type = type;
  }

  public static MeltingRecipeBuilder melting(ItemLike input, FluidStack result, CrucibleType type) {
    return melting(Ingredient.of(input), result, type);
  }

  public static MeltingRecipeBuilder melting(
      TagKey<Item> input, FluidStack result, CrucibleType type) {
    return melting(Ingredient.of(input), result, type);
  }

  public static MeltingRecipeBuilder melting(
      Ingredient input, FluidStack result, CrucibleType type) {
    return new MeltingRecipeBuilder(input, result, type);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(input, "Input cannot be null.");
    Preconditions.checkNotNull(result, "Fluid cannot be null");
    Preconditions.checkArgument(!result.isEmpty(), "Fluid amount cannot be 0");
    Preconditions.checkNotNull(type, "Crucible type cannot be null");
  }

  @Override
  protected MeltingRecipeResult getResult(ResourceLocation id) {
    return new MeltingRecipeResult(id);
  }

  public class MeltingRecipeResult extends RecipeResult {

    public MeltingRecipeResult(ResourceLocation id) {
      super(id);
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
      json.add("input", input.toJson(false));
      json.add("fluidResult", FluidStackUtils.serialize(result));
      json.addProperty("crucibleType", type.getName());
    }

    @Nullable
    @Override
    public AdvancementHolder advancement() {
      return null;
    }
  }
}
