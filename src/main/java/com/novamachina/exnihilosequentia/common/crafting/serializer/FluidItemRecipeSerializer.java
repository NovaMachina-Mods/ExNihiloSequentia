package com.novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.FluidStackUtils;
import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.fluidItem.FluidItemRecipe;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class FluidItemRecipeSerializer extends RecipeSerializer<FluidItemRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.BARREL_WOOD.get());
    }

    @Override
    protected FluidItemRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluid").getAsJsonObject());
        Ingredient input = Ingredient.deserialize(json.get("input"));
        ItemStack result = readOutput(json.get("result"));
        return new FluidItemRecipe(recipeId, fluid, input, result);
    }

    @Override
    public FluidItemRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        FluidStack fluid = FluidStack.readFromPacket(buffer);
        Ingredient input = Ingredient.read(buffer);
        ItemStack output = buffer.readItemStack();
        return new FluidItemRecipe(recipeId, fluid, input, output);
    }

    @Override
    public void write(PacketBuffer buffer, FluidItemRecipe recipe) {
        recipe.getFluidInBarrel().writeToPacket(buffer);
        recipe.getInput().write(buffer);
        buffer.writeItemStack(recipe.getResult());
    }
}