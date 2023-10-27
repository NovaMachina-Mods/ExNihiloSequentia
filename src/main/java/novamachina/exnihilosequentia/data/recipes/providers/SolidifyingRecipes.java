package novamachina.exnihilosequentia.data.recipes.providers;

import java.util.function.Consumer;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.data.recipes.SolidifyingRecipeBuilder;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class SolidifyingRecipes implements ISubRecipeProvider {

  private static final FluidStack lava = new FluidStack(Fluids.LAVA, FluidType.BUCKET_VOLUME);
  private static final FluidStack water = new FluidStack(Fluids.WATER, FluidType.BUCKET_VOLUME);

  @Override
  public void addRecipes(Consumer<FinishedRecipe> consumer) {
    SolidifyingRecipeBuilder.solidify(lava, water, Blocks.OBSIDIAN)
        .build(consumer, solidifyLoc("obsidian"));
    SolidifyingRecipeBuilder.solidify(water, lava, Blocks.COBBLESTONE)
        .build(consumer, solidifyLoc("cobblestone"));
  }

  private ResourceLocation solidifyLoc(String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "solidify/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}