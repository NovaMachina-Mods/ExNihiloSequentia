package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;

public class CrookRecipeSerializer extends RecipeSerializer<CrookRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(EnumCrook.WOOD.getRegistryObject().get());
    }

    @Override
    public CrookRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
        int outputCount = buffer.readInt();
        List<ItemStackWithChance> output = new ArrayList<>(outputCount);
        for (int i = 0; i < outputCount; i++) {
            output.add(ItemStackWithChance.read(buffer));
        }
        Ingredient input = Ingredient.fromNetwork(buffer);
        return new CrookRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(PacketBuffer buffer, CrookRecipe recipe) {
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
