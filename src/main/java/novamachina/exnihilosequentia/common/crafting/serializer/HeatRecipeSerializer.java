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

public class HeatRecipeSerializer extends RecipeSerializer<HeatRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.CRUCIBLE_FIRED.get());
    }

    private static final JsonParser PARSER = new JsonParser();

    @Override
    public HeatRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readUtf()));
        int amount = buffer.readInt();
        if (buffer.readBoolean()) {
            return  new HeatRecipe(recipeId, input, amount, StatePropertiesPredicate.fromJson(PARSER.parse(buffer.readUtf())));
        }
        return new HeatRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(PacketBuffer buffer, HeatRecipe recipe) {
        buffer.writeUtf(recipe.getInput().getRegistryName().toString());
        buffer.writeInt(recipe.getAmount());
        StatePropertiesPredicate properties = recipe.getProperties();
        if (properties != null) {
            buffer.writeBoolean(true);
            buffer.writeUtf(recipe.getProperties().serializeToJson().toString());
        } else {
            buffer.writeBoolean(false);
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
