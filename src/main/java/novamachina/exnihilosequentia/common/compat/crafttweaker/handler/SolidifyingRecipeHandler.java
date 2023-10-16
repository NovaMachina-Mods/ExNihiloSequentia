package novamachina.exnihilosequentia.common.compat.crafttweaker.handler;

import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent;
import com.blamejared.crafttweaker.api.recipe.component.RecipeComponentEqualityCheckers;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.google.gson.reflect.TypeToken;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;

@IRecipeHandler.For(SolidifyingRecipe.class)
public class SolidifyingRecipeHandler implements IRecipeHandler<SolidifyingRecipe> {
  @Override
  public String dumpToCommandString(
      IRecipeManager<? super SolidifyingRecipe> manager, SolidifyingRecipe recipe) {
    return String.format(
        "<recipetype:exnihilosequentia:solidifying>.addRecipe(%s, %s, %s, %s);",
        StringUtil.quoteAndEscape(recipe.getId()),
        IFluidStack.of(recipe.getFluidInTank()).getCommandString(),
        IFluidStack.of(recipe.getFluidOnTop()).getCommandString(),
        IItemStack.of(recipe.getResult()).getCommandString());
  }

  @Override
  public <U extends Recipe<?>> boolean doesConflict(
      IRecipeManager<? super SolidifyingRecipe> manager,
      SolidifyingRecipe firstRecipe,
      U secondRecipe) {
    SolidifyingRecipe second = (SolidifyingRecipe) secondRecipe;
    return firstRecipe.getFluidInTank().isFluidEqual(second.getFluidInTank())
        && firstRecipe.getFluidOnTop().isFluidEqual(second.getFluidOnTop());
  }

  IRecipeComponent<IFluidStack> FLUID_INPUT_1 =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "input/fluid_input_1"),
          new TypeToken<>() {},
          RecipeComponentEqualityCheckers::areFluidStacksEqual);

  IRecipeComponent<IFluidStack> FLUID_INPUT_2 =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "input/fluid_input_2"),
          new TypeToken<>() {},
          RecipeComponentEqualityCheckers::areFluidStacksEqual);

  @Override
  public Optional<IDecomposedRecipe> decompose(
      IRecipeManager<? super SolidifyingRecipe> manager, SolidifyingRecipe recipe) {
    IFluidStack fluidInTank = IFluidStack.of(recipe.getFluidInTank());
    IFluidStack fluidOnTop = IFluidStack.of(recipe.getFluidOnTop());
    IItemStack result = IItemStack.of(recipe.getResult());
    IDecomposedRecipe decomposition =
        IDecomposedRecipe.builder()
            .with(FLUID_INPUT_1, fluidInTank)
            .with(FLUID_INPUT_2, fluidOnTop)
            .with(BuiltinRecipeComponents.Output.ITEMS, result)
            .build();
    return Optional.of(decomposition);
  }

  @Override
  public Optional<SolidifyingRecipe> recompose(
      IRecipeManager<? super SolidifyingRecipe> manager,
      ResourceLocation name,
      IDecomposedRecipe recipe) {
    IFluidStack fluidInTank = recipe.getOrThrowSingle(FLUID_INPUT_1);
    IFluidStack fluidOnTop = recipe.getOrThrowSingle(FLUID_INPUT_2);
    IItemStack result = recipe.getOrThrowSingle(BuiltinRecipeComponents.Output.ITEMS);

    if (fluidInTank.isEmpty()) {
      throw new IllegalArgumentException("Invalid fluidInTank: empty fluid");
    }
    if (fluidOnTop == null) {
      throw new IllegalArgumentException("Invalid fluidOnTop: empty fluid");
    }
    if (result.isEmpty()) {
      throw new IllegalArgumentException("Invalid result: empty item stack");
    }
    return Optional.of(
        new SolidifyingRecipe(
            name, fluidInTank.getInternal(), fluidOnTop.getInternal(), result.getInternal()));
  }
}
