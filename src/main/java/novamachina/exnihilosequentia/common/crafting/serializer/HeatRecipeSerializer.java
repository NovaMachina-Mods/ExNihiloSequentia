package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeatRecipeSerializer extends RecipeSerializer<HeatRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.CRUCIBLE_FIRED.get());
    }

    private static final JsonParser PARSER = new JsonParser();

    @Override
    public HeatRecipe fromNetwork(@Nonnull final ResourceLocation recipeId, @Nonnull final PacketBuffer buffer) {
        // Packet structure is:
        // |--------------------------------------|
        // | Block Resource Location (UTF String) |
        // | Heat amount (int)                    |
        // | Flag: has properties (boolean)       |
        // | [OPTIONAL] Properties (UTF JSON)     |
        // |--------------------------------------|
        @Nullable final Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readUtf()));
        final int amount = buffer.readInt();

        final boolean hasProperties = buffer.readBoolean(); // flag showing whether recipe depends on block state
        if (hasProperties) {
            @Nonnull final StatePropertiesPredicate properties = StatePropertiesPredicate.fromJson(
                    PARSER.parse(buffer.readUtf()));
            return new HeatRecipe(recipeId, input, amount, properties);
        }
        return new HeatRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(@Nonnull final PacketBuffer buffer, @Nonnull final HeatRecipe recipe) {
        @Nullable final ResourceLocation resourceLocation = recipe.getInput().getRegistryName();
        if (resourceLocation == null)
            return;
        buffer.writeUtf(resourceLocation.toString());
        buffer.writeInt(recipe.getAmount());

        // If recipe respects block state, need to encode properties too
        StatePropertiesPredicate properties = recipe.getProperties();
        boolean hasProperties = properties != null;
        buffer.writeBoolean(hasProperties); // Add flag to packet specifying if block state is part of recipe
        if (hasProperties) {
            buffer.writeUtf(properties.serializeToJson().toString());
        }
    }

    @Override
    @Nonnull
    protected HeatRecipe readFromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
        @Nullable final Block input = ForgeRegistries.BLOCKS.getValue(
                new ResourceLocation(json.get("block").getAsString()));
        final int amount = json.get("amount").getAsInt();
        if (json.has("state")) {
            return new HeatRecipe(recipeId, input, amount, StatePropertiesPredicate.fromJson(json.get("state")));
        }
        return new HeatRecipe(recipeId, input, amount);
    }
}
