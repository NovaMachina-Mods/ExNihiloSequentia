package novamachina.exnihilosequentia.data.recipes.packs;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import novamachina.exnihilosequentia.data.recipes.providers.CompostRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.CraftingRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.CrushingRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.HarvestingRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.HeatRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.MeltingRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.PrecipitateRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.SiftingRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.SolidifyingRecipes;
import novamachina.exnihilosequentia.data.recipes.providers.TransitionRecipes;
import novamachina.novacore.data.recipes.AbstractRecipeProvider;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class EXNRecipeProvider extends AbstractRecipeProvider {
  public EXNRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
    super(provider, recipeOutput);
  }

  @Override
  protected List<ISubRecipeProvider> getSubProviders() {
    return List.of(
        new CompostRecipes(),
        new CraftingRecipes(),
        new CrushingRecipes(),
        new HarvestingRecipes(),
        new HeatRecipes(),
        new MeltingRecipes(),
        new PrecipitateRecipes(),
        new SiftingRecipes(),
        new SolidifyingRecipes(),
        new TransitionRecipes());
  }

  public static final class Runner extends RecipeProvider.Runner {
    public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
      super(output, lookupProvider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
      return new EXNRecipeProvider(lookupProvider, output);
    }

    @Override
    public String getName() {
      return "Ex Nihilo: Sequentia recipes";
    }
  }
}
