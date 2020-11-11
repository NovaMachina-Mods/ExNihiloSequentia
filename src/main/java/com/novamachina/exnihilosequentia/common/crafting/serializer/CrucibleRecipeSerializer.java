package com.novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.FluidStackUtils;
import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.crucible.CrucibleRecipe;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class CrucibleRecipeSerializer extends RecipeSerializer<CrucibleRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.CRUCIBLE_FIRED.get());
    }

    @Override
    protected CrucibleRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.deserialize(json.get("input"));
        int amount = json.get("amount").getAsInt();
        FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidResult").getAsJsonObject());
        CrucilbeTypeEnum typeEnum = CrucilbeTypeEnum.getTypeByName(json.get("crucibleType").getAsString());
        return new CrucibleRecipe(recipeId, input, amount, fluid, typeEnum);
    }

    @Override
    public CrucibleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        Ingredient input = Ingredient.read(buffer);
        int amount = buffer.readInt();
        FluidStack fluid = FluidStack.readFromPacket(buffer);
        CrucilbeTypeEnum type = buffer.readEnumValue(CrucilbeTypeEnum.class);
        return new CrucibleRecipe(recipeId, input, amount, fluid, type);
    }

    @Override
    public void write(PacketBuffer buffer, CrucibleRecipe recipe) {
        recipe.getInput().write(buffer);
        buffer.writeInt(recipe.getAmount());
        recipe.getResultFluid().writeToPacket(buffer);
        buffer.writeEnumValue(recipe.getCrucibleType());
    }
}
