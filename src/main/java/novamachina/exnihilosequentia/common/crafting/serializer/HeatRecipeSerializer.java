package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import com.google.gson.JsonParser;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

import javax.annotation.Nonnull;
import java.util.Objects;

public class HeatRecipeSerializer extends IRecipeSerializer<HeatRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.FIRED_CRUCIBLE.get());
    }

    private static final JsonParser PARSER = new JsonParser();

    @Override
    public HeatRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
        // Packet structure is:
        // |--------------------------------------|
        // | Block Resource Location (UTF String) |
        // | Heat amount (int)                    |
        // | Flag: has properties (boolean)       |
        // | [OPTIONAL] Properties (UTF JSON)     |
        // |--------------------------------------|
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readUtf()));
        int amount = buffer.readInt();

        boolean hasProperties = buffer.readBoolean(); // flag showing whether recipe depends on block state
        if (hasProperties) {
            StatePropertiesPredicate properties = StatePropertiesPredicate.fromJson(PARSER.parse(buffer.readUtf()));
            return new HeatRecipe(recipeId, input, amount, properties);
        }
        return new HeatRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, HeatRecipe recipe) {
        buffer.writeUtf(Objects.requireNonNull(recipe.getInput().getRegistryName()).toString());
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
    protected HeatRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(json.get("block").getAsString()));
        int amount = json.get("amount").getAsInt();
        if (json.has("state")) {
            return new HeatRecipe(recipeId, input, amount, StatePropertiesPredicate.fromJson(json.get("state")));
        }
        return new HeatRecipe(recipeId, input, amount);
    }
}
