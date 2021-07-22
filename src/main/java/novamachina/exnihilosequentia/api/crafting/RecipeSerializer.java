package novamachina.exnihilosequentia.api.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class RecipeSerializer<R extends Recipe<?>> extends ForgeRegistryEntry<RecipeSerializer<?>> implements net.minecraft.world.item.crafting.RecipeSerializer<R> {
    public abstract ItemStack getIcon();

    @Override
    public R fromJson(ResourceLocation recipeId, JsonObject json) {
        if (CraftingHelper.processConditions(json, "conditions"))
            return readFromJson(recipeId, json);
        return null;
    }

    protected abstract R readFromJson(ResourceLocation recipeId, JsonObject json);

    protected Item readOutput(JsonElement outputObject) {
        return ShapedRecipe.itemFromJson(outputObject.getAsJsonObject());
    }
}
