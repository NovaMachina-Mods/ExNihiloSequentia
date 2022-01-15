package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidOnTopRecipeSerializer extends RecipeSerializer<FluidOnTopRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    @Nonnull
    public FluidOnTopRecipe fromNetwork(@Nonnull final ResourceLocation recipeId, @Nonnull final PacketBuffer buffer) {
        @Nonnull final FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
        @Nonnull final FluidStack fluidOnTop = FluidStack.readFromPacket(buffer);
        @Nonnull final ItemStack result = buffer.readItem();
        return new FluidOnTopRecipe(recipeId, fluidInTank, fluidOnTop, result);
    }

    @Override
    public void toNetwork(@Nonnull final PacketBuffer buffer, @Nonnull final FluidOnTopRecipe recipe) {
        recipe.getFluidInTank().writeToPacket(buffer);
        recipe.getFluidOnTop().writeToPacket(buffer);
        buffer.writeItem(recipe.getResultItem());
    }

    @Override
    @Nonnull
    protected FluidOnTopRecipe readFromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
        @Nullable final FluidStack fluidInTank = FluidStackUtils.jsonDeserializeFluidStack(
                json.get("fluidInTank").getAsJsonObject());
        @Nullable final FluidStack fluidOnTop = FluidStackUtils.jsonDeserializeFluidStack(
                json.get("fluidOnTop").getAsJsonObject());
        @Nonnull final ItemStack result = readOutput(json.get("result"));
        return new FluidOnTopRecipe(recipeId, fluidInTank, fluidOnTop, result);
    }
}
