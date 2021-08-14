package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

import javax.annotation.Nonnull;

public class FluidOnTopRecipeSerializer extends IRecipeSerializer<FluidOnTopRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    public FluidOnTopRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
        FluidStack fluidOnTop = FluidStack.readFromPacket(buffer);
        ItemStack result = buffer.readItem();
        return new FluidOnTopRecipe(recipeId, fluidInTank, fluidOnTop, result);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, FluidOnTopRecipe recipe) {
        recipe.getFluidInTank().writeToPacket(buffer);
        recipe.getFluidOnTop().writeToPacket(buffer);
        buffer.writeItem(recipe.getResultItem());
    }

    @Override
    protected FluidOnTopRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        FluidStack fluidInTank = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidInTank").getAsJsonObject());
        FluidStack fluidOnTop = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidOnTop").getAsJsonObject());
        ItemStack result = readOutput(json.get("result"));
        return new FluidOnTopRecipe(recipeId, fluidInTank, fluidOnTop, result);
    }
}
