package novamachina.exnihilosequentia.core.registries;

import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;

public class InitRecipeSerializers {
  private InitRecipeSerializers() {}

  public static void init(IRegistry<RecipeSerializerDefinition<?>> registry) {
    for (RecipeSerializerDefinition<?> entry : EXNRecipeSerializers.getDefinitions()) {
      registry.register(entry);
    }
  }
}
