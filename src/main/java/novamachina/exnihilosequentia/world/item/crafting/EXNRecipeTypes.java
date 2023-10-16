package novamachina.exnihilosequentia.world.item.crafting;

import java.util.List;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.novacore.core.registries.RecipeTypeRegistry;

public class EXNRecipeTypes {

  private static final RecipeTypeRegistry RECIPE_TYPES =
      new RecipeTypeRegistry(ExNihiloSequentia.MOD_ID);

  public static List<RecipeType<?>> getDefinitions() {
    return RECIPE_TYPES.getRegistry();
  }

  public static final RecipeType<CompostRecipe> COMPOST = RECIPE_TYPES.register("compost");
  public static final RecipeType<HarvestRecipe> HARVEST = RECIPE_TYPES.register("harvest");
  public static final RecipeType<MeltingRecipe> MELTING = RECIPE_TYPES.register("melting");
  public static final RecipeType<PrecipitateRecipe> PRECIPITATE =
      RECIPE_TYPES.register("precipitate");
  public static final RecipeType<SolidifyingRecipe> SOLIDIFYING =
      RECIPE_TYPES.register("solidifying");
  public static final RecipeType<TransitionRecipe> TRANSITION = RECIPE_TYPES.register("transition");
  public static final RecipeType<CrushingRecipe> CRUSHING = RECIPE_TYPES.register("crushing");
  public static final RecipeType<HeatRecipe> HEAT = RECIPE_TYPES.register("heat");
  public static final RecipeType<SiftingRecipe> SIFTING = RECIPE_TYPES.register("sifting");

  private EXNRecipeTypes() {}
}
