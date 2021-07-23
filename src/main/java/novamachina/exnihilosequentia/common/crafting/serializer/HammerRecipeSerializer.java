package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;

public class HammerRecipeSerializer extends RecipeSerializer<HammerRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(EnumHammer.DIAMOND.getRegistryObject().get());
    }

    @Override
    public HammerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        Ingredient input = Ingredient.fromNetwork(buffer);
        int outputCount = buffer.readInt();
        List<ItemStackWithChance> output = new ArrayList<>(outputCount);
        for (int i = 0; i < outputCount; i++) {
            output.add(ItemStackWithChance.read(buffer));
        }

        return new HammerRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, HammerRecipe recipe) {
        recipe.getInput().toNetwork(buffer);
        buffer.writeInt(recipe.getOutput().size());
        for (ItemStackWithChance stack : recipe.getOutput()) {
            stack.write(buffer);
        }
    }

    @Override
    protected HammerRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.fromJson(json.get("input"));
        JsonArray results = json.getAsJsonArray("results");
        List<ItemStackWithChance> output = new ArrayList<>(results.size());
        for (int i = 0; i < results.size(); i++) {
            output.add(ItemStackWithChance.deserialize(results.get(i)));
        }
        return new HammerRecipe(recipeId, input, output);
    }
}
