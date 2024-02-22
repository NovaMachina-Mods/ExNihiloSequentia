package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import java.util.Optional;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import novamachina.novacore.data.recipes.RecipeBuilder;

public class HeatRecipeBuilder extends RecipeBuilder<HeatRecipe> {

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
  protected HeatRecipe getRecipe(ResourceLocation resourceLocation) {
    return new HeatRecipe(inputBlock, amount, properties);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkArgument(inputBlock != null, "Input cannot be null.");
    Preconditions.checkArgument(amount > 0, "Heat amount must be greater than 0.");
    Preconditions.checkNotNull(properties, "Properties cannot be null.");
  }
}
