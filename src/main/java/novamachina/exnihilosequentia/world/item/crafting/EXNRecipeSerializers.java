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

  public static final RecipeSerializerDefinition<?> CRUSHING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "crushing", () -> new CrushingRecipe.Serializer<>(CrushingRecipe::new));
  public static final RecipeSerializerDefinition<?> HARVEST_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "harvest", () -> new HarvestRecipe.Serializer<>(HarvestRecipe::new));
  public static final RecipeSerializerDefinition<?> COMPOST_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "compost", () -> new CompostRecipe.Serializer<>(CompostRecipe::new));
  public static final RecipeSerializerDefinition<?> PRECIPITATE_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "precipitate", () -> new PrecipitateRecipe.Serializer<>(PrecipitateRecipe::new));
  public static final RecipeSerializerDefinition<?> SOLIDIFYING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "solidifying", () -> new SolidifyingRecipe.Serializer<>(SolidifyingRecipe::new));
  public static final RecipeSerializerDefinition<?> TRANSITION_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "transition", () -> new TransitionRecipe.Serializer<>(TransitionRecipe::new));
  public static final RecipeSerializerDefinition<?> MELTING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "melting", () -> new MeltingRecipe.Serializer<>(MeltingRecipe::new));
  public static final RecipeSerializerDefinition<?> HEAT_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("heat", () -> new HeatRecipe.Serializer<>(HeatRecipe::new));
  public static final RecipeSerializerDefinition<?> SIFTING_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register(
          "sifting", () -> new SiftingRecipe.Serializer<>(SiftingRecipe::new));

  private EXNRecipeSerializers() {}
}
