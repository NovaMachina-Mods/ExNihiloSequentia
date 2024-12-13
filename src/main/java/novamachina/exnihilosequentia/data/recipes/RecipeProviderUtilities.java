package novamachina.exnihilosequentia.data.recipes;

import javax.annotation.Nonnull;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

public class RecipeProviderUtilities {
  public static ResourceKey<Recipe<?>> createSaveLocation(@Nonnull final ResourceLocation location) {
    ResourceLocation rl = ResourceLocation.fromNamespaceAndPath(location.getNamespace(), prependRecipePrefix(location.getPath()));
    return ResourceKey.create(Registries.RECIPE, rl);
  }

  public static String prependRecipePrefix(@Nonnull final String id) {
    return String.format("ens_%s", id);
  }
}
