package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

public class FluidTransformRecipeSerializer extends IRecipeSerializer<FluidTransformRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    public FluidTransformRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
        Ingredient catalyst = Ingredient.fromNetwork(buffer);
        FluidStack result = FluidStack.readFromPacket(buffer);
        return new FluidTransformRecipe(recipeId, fluidInTank, catalyst, result);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, FluidTransformRecipe recipe) {
        recipe.getFluidInTank().writeToPacket(buffer);
        recipe.getCatalyst().toNetwork(buffer);
        recipe.getResult().writeToPacket(buffer);
    }

    @Override
    protected FluidTransformRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidInTank").getAsJsonObject());
        Ingredient catalyst = Ingredient.fromJson(json.get("catalyst"));
        FluidStack result = FluidStackUtils.jsonDeserializeFluidStack(json.get("result").getAsJsonObject());
        return new FluidTransformRecipe(recipeId, fluid, catalyst, result);
    }
}
