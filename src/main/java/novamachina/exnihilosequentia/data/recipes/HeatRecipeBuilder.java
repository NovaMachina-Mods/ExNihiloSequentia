package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import java.util.Optional;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.novacore.data.recipes.RecipeBuilder;
import org.jetbrains.annotations.Nullable;

public class HeatRecipeBuilder extends RecipeBuilder<HeatRecipeBuilder> {

  private final int amount;
  private final Block inputBlock;
  private final Optional<StatePropertiesPredicate> properties;

  protected HeatRecipeBuilder(
      Block inputBlock, int amount, Optional<StatePropertiesPredicate> properties) {
    super(EXNRecipeSerializers.HEAT_RECIPE_SERIALIZER.recipeSerializer());
    this.inputBlock = inputBlock;
    this.amount = amount;
    this.properties = properties;
  }

  public static HeatRecipeBuilder heat(Block inputBlock, int amount) {
    return heat(inputBlock, amount, Optional.empty());
  }

  public static HeatRecipeBuilder heat(
      Block inputBlock, int amount, Optional<StatePropertiesPredicate> properties) {
    return new HeatRecipeBuilder(inputBlock, amount, properties);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkArgument(inputBlock != null, "Input cannot be null.");
    Preconditions.checkArgument(amount > 0, "Heat amount must be greater than 0.");
    Preconditions.checkNotNull(properties, "Properties cannot be null.");
  }

  @Override
  protected HeatRecipeResult getResult(ResourceLocation id) {
    return new HeatRecipeResult(id);
  }

  public class HeatRecipeResult extends RecipeResult {

    public HeatRecipeResult(ResourceLocation id) {
      super(id);
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
      json.add(
          "block",
          BuiltInRegistries.BLOCK
              .byNameCodec()
              .encodeStart(JsonOps.INSTANCE, inputBlock)
              .result()
              .get());
      json.addProperty("amount", amount);
      if (properties.isPresent()) {
        json.add("state", properties.get().serializeToJson());
      }
    }

    @Nullable
    @Override
    public AdvancementHolder advancement() {
      return null;
    }
  }
}
