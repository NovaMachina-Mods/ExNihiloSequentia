package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;
import novamachina.novacore.data.recipes.RecipeBuilder;

public class PrecipitateRecipeBuilder extends RecipeBuilder<PrecipitateRecipe> {
  private final Ingredient input;
  private final FluidStack fluid;
  private final ItemStack output;

  protected PrecipitateRecipeBuilder(FluidStack fluid, Ingredient input, ItemStack output) {
    super(EXNRecipeSerializers.PRECIPITATE_RECIPE_SERIALIZER.recipeSerializer());
    this.input = input;
    this.fluid = fluid;
    this.output = output;
  }

  public static PrecipitateRecipeBuilder precipitate(
      FluidStack fluid, ItemLike inputItem, ItemLike output) {
    return precipitate(fluid, Ingredient.of(inputItem), output);
  }

  public static PrecipitateRecipeBuilder precipitate(
      FluidStack fluid, TagKey<Item> inputTag, ItemLike output) {
    return precipitate(fluid, Ingredient.of(inputTag), output);
  }

  public static PrecipitateRecipeBuilder precipitate(
      FluidStack fluid, Ingredient input, ItemLike output) {
    return new PrecipitateRecipeBuilder(fluid, input, new ItemStack(output));
  }

  @Override
  protected PrecipitateRecipe getRecipe(ResourceLocation resourceLocation) {
    return new PrecipitateRecipe(fluid, input, output);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(input, "Input cannot be null.");
    Preconditions.checkNotNull(fluid, "Fluid cannot be null");
    Preconditions.checkArgument(!fluid.isEmpty(), "Fluid amount cannot be 0");
    Preconditions.checkNotNull(output, "Output cannot be null.");
  }
}
