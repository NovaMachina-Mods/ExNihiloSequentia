package novamachina.exnihilosequentia.data.recipes.providers;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.data.recipes.TransitionRecipeBuilder;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class TransitionRecipes implements ISubRecipeProvider {
  private static final FluidStack seawater =
      new FluidStack(EXNFluids.SEA_WATER.getStillFluid(), FluidType.BUCKET_VOLUME);
  private static final FluidStack water = new FluidStack(Fluids.WATER, FluidType.BUCKET_VOLUME);
  private static final FluidStack witchwater =
      new FluidStack(EXNFluids.WITCH_WATER.getStillFluid(), FluidType.BUCKET_VOLUME);

  @Override
  public void addRecipes(RecipeOutput consumer) {
    TransitionRecipeBuilder.transition(water, Blocks.MYCELIUM, witchwater)
        .build(consumer, transitionLoc("witch_water"));
    TransitionRecipeBuilder.transition(water, Tags.Items.SAND, seawater)
        .build(consumer, transitionLoc("sea_water"));
  }

  private ResourceLocation transitionLoc(String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "transition/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}
