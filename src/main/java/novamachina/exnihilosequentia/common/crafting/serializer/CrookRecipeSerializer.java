package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;

import java.util.ArrayList;
import java.util.List;

public class CrookRecipeSerializer extends RecipeSerializer<CrookRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(EnumCrook.WOOD.getRegistryObject().get());
    }

    @Override
    public CrookRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        int outputCount = buffer.readInt();
        List<ItemStackWithChance> output = new ArrayList<>(outputCount);
        for (int i = 0; i < outputCount; i++) {
            output.add(ItemStackWithChance.read(buffer));
        }
        Ingredient input = Ingredient.fromNetwork(buffer);
        return new CrookRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, CrookRecipe recipe) {
        buffer.writeInt(recipe.getOutput().size());
        for (ItemStackWithChance stack : recipe.getOutput()) {
            stack.write(buffer);
        }
        recipe.getInput().toNetwork(buffer);
    }

    @Override
    protected CrookRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.fromJson(json.get("input"));
        JsonArray results = json.getAsJsonArray("results");
        List<ItemStackWithChance> output = new ArrayList<>(results.size());
        for (int i = 0; i < results.size(); i++) {
            output.add(ItemStackWithChance.deserialize(results.get(i)));
        }
        return new CrookRecipe(recipeId, input, output);
    }
}
