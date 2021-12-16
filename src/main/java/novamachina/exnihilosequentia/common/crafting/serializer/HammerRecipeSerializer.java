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
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;

import javax.annotation.Nonnull;

public class HammerRecipeSerializer extends RecipeSerializer<HammerRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(EnumHammer.DIAMOND.getRegistryObject().get());
    }

    @Override
    @Nonnull
    public HammerRecipe fromNetwork(@Nonnull final ResourceLocation recipeId, @Nonnull final PacketBuffer buffer) {
        @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
        final int outputCount = buffer.readInt();
        @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(outputCount);
        for (int i = 0; i < outputCount; i++) {
            output.add(ItemStackWithChance.read(buffer));
        }

        return new HammerRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(@Nonnull final PacketBuffer buffer, @Nonnull final HammerRecipe recipe) {
        recipe.getInput().toNetwork(buffer);
        buffer.writeInt(recipe.getOutput().size());
        for (@Nonnull final ItemStackWithChance stack : recipe.getOutput()) {
            stack.write(buffer);
        }
    }

    @Override
    @Nonnull
    protected HammerRecipe readFromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
        @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
        @Nonnull final JsonArray results = json.getAsJsonArray("results");
        @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(results.size());
        for (int i = 0; i < results.size(); i++) {
            output.add(ItemStackWithChance.deserialize(results.get(i)));
        }
        return new HammerRecipe(recipeId, input, output);
    }
}
