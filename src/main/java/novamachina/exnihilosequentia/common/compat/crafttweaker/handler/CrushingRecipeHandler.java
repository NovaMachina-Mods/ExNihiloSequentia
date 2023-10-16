package novamachina.exnihilosequentia.common.compat.crafttweaker.handler;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;

@IRecipeHandler.For(CrushingRecipe.class)
public class CrushingRecipeHandler implements IRecipeHandler<CrushingRecipe> {
  @Override
  public String dumpToCommandString(
      IRecipeManager<? super CrushingRecipe> manager, CrushingRecipe recipe) {
    StringJoiner dropJoiner = new StringJoiner(", ");
    for (ItemStackWithChance drop : recipe.getDrops()) {
      dropJoiner.add(
          String.format(
              "ItemStackWithChance.of(%s, %f)",
              IItemStack.of(drop.getStack()).getCommandString(), drop.getChance()));
    }
    return String.format(
        "<recipetype:exnihilosequentia:crushing>.addRecipe(%s, [%s]);",
        StringUtil.quoteAndEscape(recipe.getId()), dropJoiner);
  }

  @Override
  public <U extends Recipe<?>> boolean doesConflict(
      IRecipeManager<? super CrushingRecipe> manager, CrushingRecipe firstRecipe, U secondRecipe) {
    CrushingRecipe second = (CrushingRecipe) secondRecipe;
    return IngredientUtil.canConflict(firstRecipe.getInput(), second.getInput());
  }

  IRecipeComponent<List<ItemStackWithChance>> STACK_WITH_CHANCE =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "output/stack_with_chance"),
          new TypeToken<>() {},
          List::equals);

  @Override
  public Optional<IDecomposedRecipe> decompose(
      IRecipeManager<? super CrushingRecipe> manager, CrushingRecipe recipe) {
    IIngredient ingredient = IIngredient.fromIngredient(recipe.getInput());
    IDecomposedRecipe decomposition =
        IDecomposedRecipe.builder()
            .with(BuiltinRecipeComponents.Input.INGREDIENTS, ingredient)
            .with(
                BuiltinRecipeComponents.Output.ITEMS,
                recipe.getOutputsWithoutChance().stream().map(IItemStack::of).toList())
            .with(STACK_WITH_CHANCE, recipe.getDrops())
            .build();
    return Optional.of(decomposition);
  }

  @Override
  public Optional<CrushingRecipe> recompose(
      IRecipeManager<? super CrushingRecipe> manager,
      ResourceLocation name,
      IDecomposedRecipe recipe) {
    IIngredient input = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS);
    List<ItemStackWithChance> drops = recipe.getOrThrowSingle(STACK_WITH_CHANCE);
    if (input.isEmpty()) {
      throw new IllegalArgumentException("Invalid input: empty ingredient");
    }
    if (drops.isEmpty()) {
      throw new IllegalArgumentException("Invalid drop list: empty list");
    }
    return Optional.of(
        new CrushingRecipe(
            name, input.asVanillaIngredient(), drops.toArray(ItemStackWithChance[]::new)));
  }
}
