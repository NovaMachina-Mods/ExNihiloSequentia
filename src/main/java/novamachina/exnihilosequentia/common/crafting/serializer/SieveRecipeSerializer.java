package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

public class SieveRecipeSerializer extends RecipeSerializer<SieveRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.SIEVE_OAK.get());
    }

    @Override
    public SieveRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
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
    public void toNetwork(PacketBuffer buffer, SieveRecipe recipe) {
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
