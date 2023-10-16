package novamachina.exnihilosequentia.common.compat.crafttweaker.handler;

import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent;
import com.blamejared.crafttweaker.api.recipe.component.RecipeComponentEqualityCheckers;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.Map;
import java.util.Optional;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;

@IRecipeHandler.For(HeatRecipe.class)
public class HeatRecipeHandler implements IRecipeHandler<HeatRecipe> {
  @Override
  public String dumpToCommandString(IRecipeManager<? super HeatRecipe> manager, HeatRecipe recipe) {
    ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(recipe.getInputBlock());
    return String.format(
        "<recipetype:exnihilosequentia:heat>.addRecipe(%s, %d, %s, %s);",
        StringUtil.quoteAndEscape(recipe.getId()),
        recipe.getAmount(),
        String.format("<block:%s:%s>", blockId.getNamespace(), blockId.getPath()),
        encodeProperties(recipe.getProperties()));
  }

  private String encodeProperties(StatePropertiesPredicate properties) {
    JsonObject props = properties.serializeToJson().getAsJsonObject();
    StringBuilder builder = new StringBuilder("StatePropertiesPredicate.create()");
    for (Map.Entry<String, JsonElement> entry : props.entrySet()) {
      builder.append(
          String.format(".property(\"%s\", <appropriate property value>)", entry.getKey()));
    }
    builder.append(".build()");
    return builder.toString();
  }

  @Override
  public <U extends Recipe<?>> boolean doesConflict(
      IRecipeManager<? super HeatRecipe> manager, HeatRecipe firstRecipe, U secondRecipe) {
    HeatRecipe second = (HeatRecipe) secondRecipe;
    return firstRecipe.getInputBlock().defaultBlockState().is(second.getInputBlock())
        && compareProperties(firstRecipe.getProperties(), second.getProperties());
  }

  private boolean compareProperties(
      StatePropertiesPredicate first, StatePropertiesPredicate second) {
    JsonObject firstProps = first.serializeToJson().getAsJsonObject();
    JsonObject secondProps = second.serializeToJson().getAsJsonObject();
    for (Map.Entry<String, JsonElement> entry : firstProps.entrySet()) {
      if (!secondProps.has(entry.getKey())) {
        return false;
      }
      if (!secondProps.get(entry.getKey()).equals(entry.getValue())) {
        return false;
      }
    }

    for (Map.Entry<String, JsonElement> entry : secondProps.entrySet()) {
      if (!firstProps.has(entry.getKey())) {
        return false;
      }
      if (!firstProps.get(entry.getKey()).equals(entry.getValue())) {
        return false;
      }
    }
    return true;
  }

  IRecipeComponent<Block> BLOCK_INPUT =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "input/block_input"),
          new TypeToken<>() {},
          (block, block2) -> block.defaultBlockState().is(block2));

  IRecipeComponent<Integer> OUTPUT_AMOUNT =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "output/amount"),
          new TypeToken<>() {},
          RecipeComponentEqualityCheckers::areNumbersEqual);

  IRecipeComponent<StatePropertiesPredicate> PROPERTIES =
      IRecipeComponent.simple(
          new ResourceLocation(ExNihiloSequentia.MOD_ID, "meta/properties"),
          new TypeToken<>() {},
          this::compareProperties);

  @Override
  public Optional<IDecomposedRecipe> decompose(
      IRecipeManager<? super HeatRecipe> manager, HeatRecipe recipe) {
    IDecomposedRecipe decomposition =
        IDecomposedRecipe.builder()
            .with(BLOCK_INPUT, recipe.getInputBlock())
            .with(OUTPUT_AMOUNT, recipe.getAmount())
            .with(PROPERTIES, recipe.getProperties())
            .build();
    return Optional.of(decomposition);
  }

  @Override
  public Optional<HeatRecipe> recompose(
      IRecipeManager<? super HeatRecipe> manager, ResourceLocation name, IDecomposedRecipe recipe) {
    Block input = recipe.getOrThrowSingle(BLOCK_INPUT);
    int amount = recipe.getOrThrowSingle(OUTPUT_AMOUNT);
    StatePropertiesPredicate properties = recipe.getOrThrowSingle(PROPERTIES);
    if (input == Blocks.AIR) {
      throw new IllegalArgumentException("Invalid input block: is air block");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Invalid heat value: " + amount);
    }
    return Optional.of(new HeatRecipe(name, input, amount, properties));
  }
}
