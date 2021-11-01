package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SieveRecipeSerializer extends IRecipeSerializer<SieveRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.OAK_SIEVE.get());
    }

    @Override
    public SieveRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        Ingredient input = Ingredient.fromNetwork(buffer);
        ItemStack drop = buffer.readItem();
        List<MeshWithChance> rolls = new ArrayList<>();
        int count = buffer.readInt();
        for (int i = 0; i < count; i++) {
            rolls.add(MeshWithChance.read(buffer));
        }
        boolean isWaterlogged = buffer.readBoolean();
        return new SieveRecipe(recipeId, input, drop, rolls, isWaterlogged);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, SieveRecipe recipe) {
        recipe.getInput().toNetwork(buffer);
        buffer.writeItem(recipe.getDrop());
        buffer.writeInt(recipe.getRolls().size());
        for (MeshWithChance meshWithChance : recipe.getRolls()) {
            meshWithChance.write(buffer);
        }
        buffer.writeBoolean(recipe.isWaterlogged());
    }

    @Override
    protected SieveRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.fromJson(json.get("input"));
        ItemStack drop = readOutput(json.get("result"));
        List<MeshWithChance> rolls = new ArrayList<>();
        for (JsonElement element : json.get("rolls").getAsJsonArray()) {
            rolls.add(MeshWithChance.deserialize(element));
        }
        if (json.has("waterlogged")) {
            return new SieveRecipe(recipeId, input, drop, rolls, json.get("waterlogged").getAsBoolean());
        }
        return new SieveRecipe(recipeId, input, drop, rolls, false);
    }
}
