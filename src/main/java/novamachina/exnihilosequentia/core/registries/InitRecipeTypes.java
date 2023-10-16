package novamachina.exnihilosequentia.core.registries;

import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.novacore.core.IRegistry;

public class InitRecipeTypes {
  private InitRecipeTypes() {}

  public static void init(IRegistry<RecipeType<?>> registry) {
    for (RecipeType<?> definition : EXNRecipeTypes.getDefinitions()) {
      registry.register(definition);
    }
  }
}
