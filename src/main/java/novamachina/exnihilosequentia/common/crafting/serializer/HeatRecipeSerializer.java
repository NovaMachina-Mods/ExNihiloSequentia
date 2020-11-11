package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.common.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class HeatRecipeSerializer extends RecipeSerializer<HeatRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.CRUCIBLE_FIRED.get());
    }

    @Override
    protected HeatRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(json.get("block").getAsString()));
        int amount = json.get("amount").getAsInt();
        return new HeatRecipe(recipeId, input, amount);
    }

    @Override
    public HeatRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readString()));
        int amount = buffer.readInt();
        return new HeatRecipe(recipeId, input, amount);
    }

    @Override
    public void write(PacketBuffer buffer, HeatRecipe recipe) {
        buffer.writeString(recipe.getInput().getRegistryName().toString());
        buffer.writeInt(recipe.getAmount());
    }
}
