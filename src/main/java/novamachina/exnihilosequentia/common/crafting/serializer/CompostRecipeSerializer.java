package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

import javax.annotation.Nonnull;

public class CompostRecipeSerializer extends IRecipeSerializer<CompostRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.OAK_BARREL.get());
    }

    @Override
    public CompostRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        Ingredient input = Ingredient.fromNetwork(buffer);
        int amount = buffer.readInt();
        return new CompostRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, CompostRecipe recipe) {
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
