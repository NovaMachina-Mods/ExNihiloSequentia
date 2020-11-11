package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import novamachina.exnihilosequentia.common.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class CrookRecipeSerializer extends RecipeSerializer<CrookRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(EnumCrook.WOOD.getRegistryObject().get());
    }

    @Override
    protected CrookRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.deserialize(json.get("input"));
        JsonArray results = json.getAsJsonArray("results");
        List<ItemStackWithChance> output = new ArrayList<>(results.size());
        for(int i = 0; i < results.size(); i++) {
            output.add(ItemStackWithChance.deserialize(results.get(i)));
        }
        return new CrookRecipe(recipeId, input, output);
    }

    @Override
    public CrookRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        int outputCount = buffer.readInt();
        List<ItemStackWithChance> output = new ArrayList<>(outputCount);
        for(int i = 0; i < outputCount; i++) {
            output.set(i,ItemStackWithChance.read(buffer));
        }
        Ingredient input = Ingredient.read(buffer);
        return new CrookRecipe(recipeId, input, output);
    }

    @Override
    public void write(PacketBuffer buffer, CrookRecipe recipe) {
        buffer.writeInt(recipe.getOutput().size());
        for(ItemStackWithChance stack : recipe.getOutput()) {
            stack.write(buffer);
        }
        recipe.getInput().write(buffer);
    }
}
