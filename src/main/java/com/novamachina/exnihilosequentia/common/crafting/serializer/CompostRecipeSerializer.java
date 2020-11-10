package com.novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.compost.CompostRecipe;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class CompostRecipeSerializer extends RecipeSerializer<CompostRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.BARREL_WOOD.get());
    }

    @Override
    protected CompostRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.deserialize(json.get("input"));
        int amount = json.get("amount").getAsInt();
        return new CompostRecipe(recipeId, input, amount);
    }

    @Override
    public CompostRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        Ingredient input = Ingredient.read(buffer);
        int amount = buffer.readInt();
        return new CompostRecipe(recipeId, input, amount);
    }

    @Override
    public void write(PacketBuffer buffer, CompostRecipe recipe) {
        recipe.getInput().write(buffer);
        buffer.writeInt(recipe.getAmount());
    }
}
