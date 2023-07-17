package novamachina.exnihilosequentia.world.item.crafting;

import java.util.List;
import javax.annotation.Nonnull;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.crafting.serializer.*;
import novamachina.novacore.registries.RecipeSerializerRegistry;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;

public class EXNRecipeSerializers {

  @Nonnull
  private static final RecipeSerializerRegistry RECIPE_SERIALIZERS =
      new RecipeSerializerRegistry(ExNihiloSequentia.MOD_ID);

  public static List<RecipeSerializerDefinition<?>> getDefinitions() {
    return RECIPE_SERIALIZERS.getRegistry();
  }

  public static final RecipeSerializerDefinition<?> HAMMER_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("hammer", HammerRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> CROOK_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("crook", CrookRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> COMPOST_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("compost", CompostRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> FLUID_ITEM_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("fluid_item", FluidItemRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> FLUID_ON_TOP_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("fluid_on_top", FluidOnTopRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> FLUID_TRANSFORM_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("fluid_transform", FluidTransformRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> CRUCIBLE_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("crucible", CrucibleRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> HEAT_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("heat", HeatRecipeSerializer::new);
  public static final RecipeSerializerDefinition<?> SIEVE_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("sieve", SieveRecipeSerializer::new);

  private EXNRecipeSerializers() {}
}
