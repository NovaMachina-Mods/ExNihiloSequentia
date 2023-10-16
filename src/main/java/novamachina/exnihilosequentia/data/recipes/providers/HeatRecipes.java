package novamachina.exnihilosequentia.data.recipes.providers;

import java.util.function.Consumer;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.HeatRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class HeatRecipes implements ISubRecipeProvider {
  @Override
  public void addRecipes(Consumer<FinishedRecipe> consumer) {
    HeatRecipeBuilder.heat(Blocks.LAVA, 3).build(consumer, heatLoc("lava"));
    HeatRecipeBuilder.heat(Blocks.FIRE, 4).build(consumer, heatLoc("fire"));
    HeatRecipeBuilder.heat(Blocks.TORCH, 1).build(consumer, heatLoc("torch"));
    HeatRecipeBuilder.heat(Blocks.WALL_TORCH, 1).build(consumer, heatLoc("wall_torch"));
    HeatRecipeBuilder.heat(Blocks.MAGMA_BLOCK, 2).build(consumer, heatLoc("magma_block"));
    HeatRecipeBuilder.heat(Blocks.GLOWSTONE, 2).build(consumer, heatLoc("glowstone"));
    HeatRecipeBuilder.heat(Blocks.SHROOMLIGHT, 2).build(consumer, heatLoc("shroomlight"));
    HeatRecipeBuilder.heat(Blocks.SOUL_FIRE, 4).build(consumer, heatLoc("soul_fire"));

    // Lit blocks
    StatePropertiesPredicate lit =
        StatePropertiesPredicate.Builder.properties()
            .hasProperty(BlockStateProperties.LIT, true)
            .build();
    HeatRecipeBuilder.heat(Blocks.CAMPFIRE, 4, lit).build(consumer, heatLoc("campfire"));
    HeatRecipeBuilder.heat(Blocks.SOUL_CAMPFIRE, 4, lit).build(consumer, heatLoc("soul_campfire"));
    HeatRecipeBuilder.heat(Blocks.FURNACE, 3, lit).build(consumer, heatLoc("furnace"));
    HeatRecipeBuilder.heat(Blocks.REDSTONE_TORCH, 1, lit)
        .build(consumer, heatLoc("redstone_torch"));
    HeatRecipeBuilder.heat(Blocks.REDSTONE_WALL_TORCH, 1, lit)
        .build(consumer, heatLoc("redstone_wall_torch"));
  }

  private ResourceLocation heatLoc(String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "heat/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}
