package novamachina.exnihilosequentia.api.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class RecipeSerializer<R extends IRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<R> {
    public abstract ItemStack getIcon();

    @Override
    public R fromJson(ResourceLocation recipeId, JsonObject json) {
        if (CraftingHelper.processConditions(json, "conditions"))
            return readFromJson(recipeId, json);
        return null;
    }

    protected abstract R readFromJson(ResourceLocation recipeId, JsonObject json);

    protected ItemStack readOutput(JsonElement outputObject) {
        return ShapedRecipe.itemFromJson(outputObject.getAsJsonObject());
    }
}
