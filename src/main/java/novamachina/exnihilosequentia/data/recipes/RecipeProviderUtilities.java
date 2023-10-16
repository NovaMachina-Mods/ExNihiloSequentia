package novamachina.exnihilosequentia.data.recipes;

import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;

public class RecipeProviderUtilities {
  public static ResourceLocation createSaveLocation(@Nonnull final ResourceLocation location) {
    return new ResourceLocation(location.getNamespace(), prependRecipePrefix(location.getPath()));
  }

  public static String prependRecipePrefix(@Nonnull final String id) {
    return String.format("ens_%s", id);
  }
}
