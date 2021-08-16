package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

import javax.annotation.Nonnull;

public class FluidItemRecipeSerializer extends IRecipeSerializer<FluidItemRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    public FluidItemRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        FluidStack fluid = FluidStack.readFromPacket(buffer);
        Ingredient input = Ingredient.fromNetwork(buffer);
        ItemStack output = buffer.readItem();
        return new FluidItemRecipe(recipeId, fluid, input, output);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, FluidItemRecipe recipe) {
        recipe.getFluidInBarrel().writeToPacket(buffer);
        recipe.getInput().toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem());
    }

    @Override
    protected FluidItemRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluid").getAsJsonObject());
        Ingredient input = Ingredient.fromJson(json.get("input"));
        ItemStack result = readOutput(json.get("result"));
        return new FluidItemRecipe(recipeId, fluid, input, result);
    }
}
