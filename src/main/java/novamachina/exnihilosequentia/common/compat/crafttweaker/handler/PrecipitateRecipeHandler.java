package novamachina.exnihilosequentia.common.compat.crafttweaker.handler;

import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent;
import com.blamejared.crafttweaker.api.recipe.component.RecipeComponentEqualityCheckers;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.google.gson.reflect.TypeToken;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;

@IRecipeHandler.For(PrecipitateRecipe.class)
public class PrecipitateRecipeHandler implements IRecipeHandler<PrecipitateRecipe> {
  @Override
  public String dumpToCommandString(
      IRecipeManager<? super PrecipitateRecipe> manager, PrecipitateRecipe recipe) {
    return String.format(
        "<recipetype:exnihilosequentia:precipitate>.addRecipe(%s, %s, %s, %s);",
        StringUtil.quoteAndEscape(recipe.getId()),
        IFluidStack.of(recipe.getFluid()).getCommandString(),
        IIngredient.fromIngredient(recipe.getInput()).getCommandString(),
        IItemStack.of(recipe.getOutput()).getCommandString());
  }

  @Override
  public <U extends Recipe<?>> boolean doesConflict(
      IRecipeManager<? super PrecipitateRecipe> manager,
      PrecipitateRecipe firstRecipe,
      U secondRecipe) {
    PrecipitateRecipe second = (PrecipitateRecipe) secondRecipe;
    return firstRecipe.getFluid().isFluidEqual(second.getFluid())
        && IngredientUtil.canConflict(firstRecipe.getInput(), second.getInput());
  }

  IRecipeComponent<IFluidStack> FLUID_INPUT =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "input/fluid_input"),
          new TypeToken<>() {},
          RecipeComponentEqualityCheckers::areFluidStacksEqual);

  @Override
  public Optional<IDecomposedRecipe> decompose(
      IRecipeManager<? super PrecipitateRecipe> manager, PrecipitateRecipe recipe) {
    IFluidStack fluid = IFluidStack.of(recipe.getFluid());
    IIngredient input = IIngredient.fromIngredient(recipe.getInput());
    IItemStack output = IItemStack.of(recipe.getOutput());

    IDecomposedRecipe decomposition =
        IDecomposedRecipe.builder()
            .with(FLUID_INPUT, fluid)
            .with(BuiltinRecipeComponents.Input.INGREDIENTS, input)
            .with(BuiltinRecipeComponents.Output.ITEMS, output)
            .build();

    return Optional.of(decomposition);
  }

  @Override
  public Optional<PrecipitateRecipe> recompose(
      IRecipeManager<? super PrecipitateRecipe> manager,
      ResourceLocation name,
      IDecomposedRecipe recipe) {
    IFluidStack fluid = recipe.getOrThrowSingle(FLUID_INPUT);
    IIngredient input = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS);
    IItemStack output = recipe.getOrThrowSingle(BuiltinRecipeComponents.Output.ITEMS);

    if (fluid.isEmpty()) {
      throw new IllegalArgumentException("Invalid fluidInTank: empty fluid");
    }
    if (input.isEmpty()) {
      throw new IllegalArgumentException("Invalid input: empty ingredient");
    }
    if (output.isEmpty()) {
      throw new IllegalArgumentException("Invalid result: empty item stack");
    }
    return Optional.of(
        new PrecipitateRecipe(
            name, fluid.getInternal(), input.asVanillaIngredient(), output.getInternal()));
  }
}
