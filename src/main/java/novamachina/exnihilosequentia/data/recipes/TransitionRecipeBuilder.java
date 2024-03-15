package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;
import novamachina.novacore.data.recipes.RecipeBuilder;

public class TransitionRecipeBuilder extends RecipeBuilder<TransitionRecipe> {

  private final Ingredient catalyst;
  private final FluidStack fluidInTank;
  private final FluidStack result;

  protected TransitionRecipeBuilder(
      FluidStack fluidInTank, Ingredient catalyst, FluidStack result) {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.TRANSITION_RECIPE_SERIALIZER.recipeSerializer());
    this.fluidInTank = fluidInTank;
    this.catalyst = catalyst;
    this.result = result;
  }

  public static TransitionRecipeBuilder transition(
      FluidStack fluidInTank, ItemLike catalyst, FluidStack result) {
    return transition(fluidInTank, Ingredient.of(catalyst), result);
  }

  public static TransitionRecipeBuilder transition(
      FluidStack fluidInTank, TagKey<Item> catalyst, FluidStack result) {
    return transition(fluidInTank, Ingredient.of(catalyst), result);
  }

  public static TransitionRecipeBuilder transition(
      FluidStack fluidInTank, Ingredient catalyst, FluidStack result) {
    return new TransitionRecipeBuilder(fluidInTank, catalyst, result);
  }

  @Override
  protected TransitionRecipe getRecipe(ResourceLocation resourceLocation) {
    return new TransitionRecipe(catalyst, fluidInTank, result);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(fluidInTank, "Fluid in tank cannot be null");
    Preconditions.checkArgument(!fluidInTank.isEmpty(), "Fluid in tank amount amount cannot be 0");
    Preconditions.checkNotNull(fluidInTank, "Catalyst cannot be null.");
    Preconditions.checkNotNull(result, "Fluid result cannot be null");
    Preconditions.checkArgument(!result.isEmpty(), "Fluid result amount amount cannot be 0");
  }
}
