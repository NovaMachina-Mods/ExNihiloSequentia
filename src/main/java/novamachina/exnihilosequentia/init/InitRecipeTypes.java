package novamachina.exnihilosequentia.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;

public class InitRecipeTypes {
  private InitRecipeTypes() {}

  public static void init(IForgeRegistry<RecipeType<?>> registry) {
    for (RecipeType<?> definition : EXNRecipeTypes.getDefinitions()) {
      registry.register(new ResourceLocation(definition.toString()), definition);
    }
  }
}
