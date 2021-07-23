package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

public class CompostRecipeSerializer extends RecipeSerializer<CompostRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    public CompostRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        Ingredient input = Ingredient.fromNetwork(buffer);
        int amount = buffer.readInt();
        return new CompostRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, CompostRecipe recipe) {
        recipe.getInput().toNetwork(buffer);
        buffer.writeInt(recipe.getAmount());
    }

    @Override
    protected CompostRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.fromJson(json.get("input"));
        int amount = json.get("amount").getAsInt();
        return new CompostRecipe(recipeId, input, amount);
    }
}
