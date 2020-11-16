package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.init.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class FluidTransformRecipeSerializer extends RecipeSerializer<FluidTransformRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.BARREL_WOOD.get());
    }

    @Override
    protected FluidTransformRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidInTank").getAsJsonObject());
        Ingredient block = Ingredient.deserialize(json.get("blockBelow"));
        FluidStack result = FluidStackUtils.jsonDeserializeFluidStack(json.get("result").getAsJsonObject());
        return new FluidTransformRecipe(recipeId, fluid, block, result);
    }

    @Override
    public FluidTransformRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
        Ingredient blockBelow = Ingredient.read(buffer);
        FluidStack result = FluidStack.readFromPacket(buffer);
        return new FluidTransformRecipe(recipeId, fluidInTank, blockBelow, result);
    }

    @Override
    public void write(PacketBuffer buffer, FluidTransformRecipe recipe) {
        recipe.getFluidInTank().writeToPacket(buffer);
        recipe.getBlockBelow().write(buffer);
        recipe.getResult().writeToPacket(buffer);
    }
}
