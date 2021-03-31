package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
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

    @Override
    public HeatRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readUtf()));
        int amount = buffer.readInt();
        return new HeatRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(PacketBuffer buffer, HeatRecipe recipe) {
        buffer.writeUtf(recipe.getInput().getRegistryName().toString());
        buffer.writeInt(recipe.getAmount());
    }

    @Override
    protected HeatRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(json.get("block").getAsString()));
        int amount = json.get("amount").getAsInt();
        return new HeatRecipe(recipeId, input, amount);
    }
}
