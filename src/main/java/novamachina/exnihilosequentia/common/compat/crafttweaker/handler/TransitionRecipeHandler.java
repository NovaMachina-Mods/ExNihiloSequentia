package novamachina.exnihilosequentia.common.compat.crafttweaker.handler;

import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
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
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;

@IRecipeHandler.For(TransitionRecipe.class)
public class TransitionRecipeHandler implements IRecipeHandler<TransitionRecipe> {
  @Override
  public String dumpToCommandString(
      IRecipeManager<? super TransitionRecipe> manager, TransitionRecipe recipe) {
    return String.format(
        "<recipetype:exnihilosequentia:transition>.addRecipe(%s, %s, %s, %s);",
        StringUtil.quoteAndEscape(recipe.getId()),
        IIngredient.fromIngredient(recipe.getCatalyst()).getCommandString(),
        IFluidStack.of(recipe.getFluidInTank()).getCommandString(),
        IFluidStack.of(recipe.getResult()).getCommandString());
  }

  @Override
  public <U extends Recipe<?>> boolean doesConflict(
      IRecipeManager<? super TransitionRecipe> manager,
      TransitionRecipe firstRecipe,
      U secondRecipe) {
    TransitionRecipe second = (TransitionRecipe) secondRecipe;
    return IngredientUtil.canConflict(firstRecipe.getCatalyst(), second.getCatalyst())
        && firstRecipe.getFluidInTank().isFluidEqual(second.getFluidInTank());
  }

  IRecipeComponent<IFluidStack> FLUID_INPUT =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "input/fluid_input"),
          new TypeToken<>() {},
          RecipeComponentEqualityCheckers::areFluidStacksEqual);

  @Override
  public Optional<IDecomposedRecipe> decompose(
      IRecipeManager<? super TransitionRecipe> manager, TransitionRecipe recipe) {
    IIngredient catalyst = IIngredient.fromIngredient(recipe.getCatalyst());
    IFluidStack fluidInTank = IFluidStack.of(recipe.getFluidInTank());
    IFluidStack result = IFluidStack.of(recipe.getResult());
    IDecomposedRecipe decomposition =
        IDecomposedRecipe.builder()
            .with(BuiltinRecipeComponents.Input.INGREDIENTS, catalyst)
            .with(FLUID_INPUT, fluidInTank)
            .with(BuiltinRecipeComponents.Output.FLUIDS, result)
            .build();
    return Optional.of(decomposition);
  }

  @Override
  public Optional<TransitionRecipe> recompose(
      IRecipeManager<? super TransitionRecipe> manager,
      ResourceLocation name,
      IDecomposedRecipe recipe) {
    IIngredient catalyst = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS);
    IFluidStack fluidInTank = recipe.getOrThrowSingle(FLUID_INPUT);
    IFluidStack result = recipe.getOrThrowSingle(BuiltinRecipeComponents.Output.FLUIDS);

    if (catalyst.isEmpty()) {
      throw new IllegalArgumentException("Invalid catalyst: empty ingredient");
    }
    if (fluidInTank.isEmpty()) {
      throw new IllegalArgumentException("Invalid fluidInTank: empty fluid");
    }
    if (result == null) {
      throw new IllegalArgumentException("Invalid result: empty fluid");
    }

    return Optional.of(
        new TransitionRecipe(
            name, catalyst.asVanillaIngredient(), fluidInTank.getInternal(), result.getInternal()));
  }
}
