package novamachina.exnihilosequentia.common.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public abstract class ExNihiloRecipeSerializer<R extends Recipe<?>> implements
    RecipeSerializer<R> {

  public abstract ItemStack getIcon();

  @Override
  @Nonnull
  public R fromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json)
      throws NullPointerException {
    return readFromJson(recipeId, json);
  }

  protected abstract R readFromJson(@Nonnull final ResourceLocation recipeId,
      @Nonnull final JsonObject json);

  @Nonnull
  protected ItemStack readOutput(@Nonnull final JsonElement outputObject) {
    return ShapedRecipe.itemFromJson(outputObject.getAsJsonObject()).getDefaultInstance();
  }
}
