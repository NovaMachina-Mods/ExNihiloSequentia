package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidItemRecipeSerializer extends RecipeSerializer<FluidItemRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    @Nonnull
    public FluidItemRecipe fromNetwork(@Nonnull final ResourceLocation recipeId, @Nonnull final PacketBuffer buffer) {
        @Nonnull final FluidStack fluid = FluidStack.readFromPacket(buffer);
        @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
        @Nonnull final ItemStack output = buffer.readItem();
        return new FluidItemRecipe(recipeId, fluid, input, output);
    }

    @Override
    public void toNetwork(@Nonnull final PacketBuffer buffer, @Nonnull final FluidItemRecipe recipe) {
        recipe.getFluidInBarrel().writeToPacket(buffer);
        recipe.getInput().toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem());
    }

    @Override
    @Nonnull
    protected FluidItemRecipe readFromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
        @Nullable final FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(
                json.get("fluid").getAsJsonObject());
        @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
        @Nonnull final ItemStack result = readOutput(json.get("result"));
        return new FluidItemRecipe(recipeId, fluid, input, result);
    }
}
