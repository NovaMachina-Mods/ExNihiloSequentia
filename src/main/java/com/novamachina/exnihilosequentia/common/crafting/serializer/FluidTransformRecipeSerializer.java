package com.novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.FluidStackUtils;
import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidtransform.FluidTransformRecipe;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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
        ItemStack block = readOutput(json.get("blockBelow"));
        FluidStack result = FluidStackUtils.jsonDeserializeFluidStack(json.get("result").getAsJsonObject());
        return new FluidTransformRecipe(recipeId, fluid, block, result);
    }

    @Override
    public FluidTransformRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
        ItemStack blockBelow = buffer.readItemStack();
        FluidStack result = FluidStack.readFromPacket(buffer);
        return new FluidTransformRecipe(recipeId, fluidInTank, blockBelow, result);
    }

    @Override
    public void write(PacketBuffer buffer, FluidTransformRecipe recipe) {
        recipe.getFluidInTank().writeToPacket(buffer);
        buffer.writeItemStack(recipe.getBlockBelow());
        recipe.getResult().writeToPacket(buffer);
    }
}
