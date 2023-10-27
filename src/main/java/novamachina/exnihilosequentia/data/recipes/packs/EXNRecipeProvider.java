package novamachina.exnihilosequentia.data.recipes.packs;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.ExNihiloSequentia;
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
import novamachina.novacore.data.recipes.ISubRecipeProvider;
import novamachina.novacore.data.recipes.RecipeProvider;

public class EXNRecipeProvider extends RecipeProvider {
  public EXNRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, existingFileHelper, ExNihiloSequentia.MOD_ID);
  }

  @Override
  protected void addRecipes(Consumer<FinishedRecipe> consumer) {}

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