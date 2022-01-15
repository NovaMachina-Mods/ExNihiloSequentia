package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

import javax.annotation.Nonnull;

public class CompostRecipeSerializer extends RecipeSerializer<CompostRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    @Nonnull
    public CompostRecipe fromNetwork(@Nonnull final ResourceLocation recipeId, @Nonnull final PacketBuffer buffer) {
        @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
        final int amount = buffer.readInt();
        return new CompostRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(@Nonnull final PacketBuffer buffer, @Nonnull final CompostRecipe recipe) {
        recipe.getInput().toNetwork(buffer);
        buffer.writeInt(recipe.getAmount());
    }

    @Override
    @Nonnull
    protected CompostRecipe readFromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
        @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
        final int amount = json.get("amount").getAsInt();
        return new CompostRecipe(recipeId, input, amount);
    }
}
