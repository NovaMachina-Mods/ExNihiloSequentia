package novamachina.exnihilosequentia.init;

import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;

public class InitRecipeSerializers {
  private InitRecipeSerializers() {}

  public static void init(IForgeRegistry<RecipeSerializer<?>> registry) {
    for (Map.Entry<ResourceLocation, RecipeSerializer<?>> entry :
        EXNRecipeSerializers.getDefinitions().entrySet()) {
      registry.register(entry.getKey(), entry.getValue());
    }
  }
}
