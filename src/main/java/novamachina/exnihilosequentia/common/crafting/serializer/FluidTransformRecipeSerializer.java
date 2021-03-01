package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

public class FluidTransformRecipeSerializer extends RecipeSerializer<FluidTransformRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_WOOD.get());
    }

    @Override
    public FluidTransformRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
        Ingredient catalyst = Ingredient.read(buffer);
        FluidStack result = FluidStack.readFromPacket(buffer);
        return new FluidTransformRecipe(recipeId, fluidInTank, catalyst, result);
    }

    @Override
    public void write(PacketBuffer buffer, FluidTransformRecipe recipe) {
        recipe.getFluidInTank().writeToPacket(buffer);
        recipe.getCatalyst().write(buffer);
        recipe.getResult().writeToPacket(buffer);
    }

    @Override
    protected FluidTransformRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidInTank").getAsJsonObject());
        Ingredient catalyst = Ingredient.deserialize(json.get("catalyst"));
        FluidStack result = FluidStackUtils.jsonDeserializeFluidStack(json.get("result").getAsJsonObject());
        return new FluidTransformRecipe(recipeId, fluid, catalyst, result);
    }
}
