package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;
import novamachina.novacore.data.recipes.RecipeBuilder;

public class SolidifyingRecipeBuilder extends RecipeBuilder<SolidifyingRecipe> {
  private final FluidStack fluidInTank;
  private final FluidStack fluidOnTop;
  private final ItemStack result;

  protected SolidifyingRecipeBuilder(
      FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.SOLIDIFYING_RECIPE_SERIALIZER.recipeSerializer());
    this.fluidInTank = fluidInTank;
    this.fluidOnTop = fluidOnTop;
    this.result = result;
  }

  public static SolidifyingRecipeBuilder solidify(
      FluidStack fluidInTank, FluidStack fluidOnTop, ItemLike result) {
    return new SolidifyingRecipeBuilder(fluidInTank, fluidOnTop, new ItemStack(result));
  }

  @Override
  protected SolidifyingRecipe getRecipe(ResourceLocation resourceLocation) {
    return new SolidifyingRecipe(fluidInTank, fluidOnTop, result);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(fluidInTank, "Fluid in barrel cannot be null");
    Preconditions.checkArgument(!fluidInTank.isEmpty(), "Fluid in barrel amount cannot be 0");
    Preconditions.checkNotNull(fluidOnTop, "Fluid on top cannot be null");
    Preconditions.checkArgument(!fluidOnTop.isEmpty(), "Fluid on top amount cannot be 0");
    Preconditions.checkNotNull(result, "Result cannot be null.");
  }
}
