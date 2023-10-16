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
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;

@IRecipeHandler.For(SiftingRecipe.class)
public class SiftingRecipeHandler implements IRecipeHandler<SiftingRecipe> {
  @Override
  public String dumpToCommandString(
      IRecipeManager<? super SiftingRecipe> manager, SiftingRecipe recipe) {
    StringJoiner rollJoiner = new StringJoiner(", ");
    for (MeshWithChance roll : recipe.getRolls()) {
      rollJoiner.add(
          String.format(
              "MeshWithChance.of(%s, %f)",
              String.format("MeshType.%s()", roll.getMesh().getMeshName()), roll.getChance()));
    }
    return String.format(
        "<recipetype:exnihilosequentia:sifting>.addRecipe(%s, %s, %s, %b, [%s]);",
        StringUtil.quoteAndEscape(recipe.getId()),
        IIngredient.fromIngredient(recipe.getInput()).getCommandString(),
        IItemStack.of(recipe.getDrop()).getCommandString(),
        recipe.isWaterlogged(),
        rollJoiner);
  }

  @Override
  public <U extends Recipe<?>> boolean doesConflict(
      IRecipeManager<? super SiftingRecipe> manager, SiftingRecipe firstRecipe, U secondRecipe) {
    SiftingRecipe second = (SiftingRecipe) secondRecipe;
    return IngredientUtil.canConflict(firstRecipe.getInput(), second.getInput())
        && firstRecipe.isWaterlogged() == second.isWaterlogged();
  }

  IRecipeComponent<List<MeshWithChance>> MESH_WITH_CHANCE =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "output/mesh_with_chance"),
          new TypeToken<>() {},
          List::equals);

  IRecipeComponent<Boolean> BOOL =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "bool"),
          new TypeToken<>() {},
          (aBoolean, aBoolean2) -> aBoolean == aBoolean2);

  @Override
  public Optional<IDecomposedRecipe> decompose(
      IRecipeManager<? super SiftingRecipe> manager, SiftingRecipe recipe) {
    IIngredient input = IIngredient.fromIngredient(recipe.getInput());
    IItemStack drop = IItemStack.of(recipe.getDrop());
    IDecomposedRecipe decomposition =
        IDecomposedRecipe.builder()
            .with(BuiltinRecipeComponents.Input.INGREDIENTS, input)
            .with(BuiltinRecipeComponents.Output.ITEMS, drop)
            .with(BOOL, recipe.isWaterlogged())
            .with(MESH_WITH_CHANCE, recipe.getRolls())
            .build();
    return Optional.of(decomposition);
  }

  @Override
  public Optional<SiftingRecipe> recompose(
      IRecipeManager<? super SiftingRecipe> manager,
      ResourceLocation name,
      IDecomposedRecipe recipe) {
    IIngredient input = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS);
    IItemStack drop = recipe.getOrThrowSingle(BuiltinRecipeComponents.Output.ITEMS);
    boolean isWaterlogged = recipe.getOrThrowSingle(BOOL);
    List<MeshWithChance> rolls = recipe.getOrThrowSingle(MESH_WITH_CHANCE);
    if (input.isEmpty()) {
      throw new IllegalArgumentException("Invalid input: empty ingredient");
    }
    if (drop.isEmpty()) {
      throw new IllegalArgumentException("Invalid drop: empty item stack");
    }
    if (rolls.isEmpty()) {
      throw new IllegalArgumentException("Invalid roll list: empty list");
    }
    return Optional.of(
        new SiftingRecipe(
            name,
            input.asVanillaIngredient(),
            drop.getInternal(),
            isWaterlogged,
            rolls.toArray(MeshWithChance[]::new)));
  }
}
