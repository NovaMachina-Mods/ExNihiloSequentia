package novamachina.exnihilosequentia.common.api.crafting;

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
    public R read(ResourceLocation recipeId, JsonObject json) {
        if(CraftingHelper.processConditions(json, "conditions"))
            return readFromJson(recipeId, json);
        return null;
    }

    protected ItemStack readOutput(JsonElement outputObject) {
        return ShapedRecipe.deserializeItem(outputObject.getAsJsonObject());
    }

    protected abstract R readFromJson(ResourceLocation recipeId, JsonObject json);
}
