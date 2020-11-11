package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class HammerRecipeSerializer extends RecipeSerializer<HammerRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(EnumHammer.DIAMOND.getRegistryObject().get());
    }

    @Override
    protected HammerRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        ItemStack input = readOutput(json.get("input"));
        ItemStack output = readOutput(json.get("result"));
        return new HammerRecipe(recipeId, input, output);
    }

    @Override
    public HammerRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack input = buffer.readItemStack();
        ItemStack output = buffer.readItemStack();

        return new HammerRecipe(recipeId, input, output);
    }

    @Override
    public void write(PacketBuffer buffer, HammerRecipe recipe) {
        buffer.writeItemStack(recipe.getInput());
        buffer.writeItemStack(recipe.getOutput());
    }
}
