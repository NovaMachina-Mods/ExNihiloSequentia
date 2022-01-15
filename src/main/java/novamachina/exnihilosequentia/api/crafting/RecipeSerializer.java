package novamachina.exnihilosequentia.api.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;

public abstract class RecipeSerializer<R extends IRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<R> {
    public abstract ItemStack getIcon();

    @Override
    @Nonnull
    public R fromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) throws NullPointerException {
        return readFromJson(recipeId, json);
    }

    protected abstract R readFromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json);

    @Nonnull
    protected ItemStack readOutput(@Nonnull final JsonElement outputObject) {
        return ShapedRecipe.itemFromJson(outputObject.getAsJsonObject());
    }
}
