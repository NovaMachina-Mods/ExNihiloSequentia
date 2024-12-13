package novamachina.exnihilosequentia.world.item.crafting;

import java.util.List;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.novacore.core.registries.RecipeSerializerRegistry;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;

public class EXNRecipeSerializers {
  private static final RecipeSerializerRegistry RECIPE_SERIALIZERS =
      new RecipeSerializerRegistry(ExNihiloSequentia.MOD_ID);

  public static List<RecipeSerializerDefinition<?>> getDefinitions() {
    return RECIPE_SERIALIZERS.getRegistry();
  }

  public static final RecipeSerializerDefinition<CrushingRecipe> CRUSHING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("crushing", CrushingRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<HarvestRecipe> HARVEST_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("harvest", HarvestRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<CompostRecipe> COMPOST_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("compost", CompostRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<PrecipitateRecipe> PRECIPITATE_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("precipitate", PrecipitateRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<SolidifyingRecipe> SOLIDIFYING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("solidifying", SolidifyingRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<TransitionRecipe> TRANSITION_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("transition", TransitionRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<MeltingRecipe> MELTING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("melting", MeltingRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<HeatRecipe> HEAT_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("heat", HeatRecipe.Serializer::new);
  public static final RecipeSerializerDefinition<SiftingRecipe> SIFTING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("sifting", SiftingRecipe.Serializer::new);

  private EXNRecipeSerializers() {}
}
