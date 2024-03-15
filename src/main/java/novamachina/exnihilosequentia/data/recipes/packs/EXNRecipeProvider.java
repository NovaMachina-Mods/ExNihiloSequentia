package novamachina.exnihilosequentia.data.recipes.packs;

import java.util.List;
import net.minecraft.data.PackOutput;
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
  public EXNRecipeProvider(PackOutput output) {
    super(output);
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
}
