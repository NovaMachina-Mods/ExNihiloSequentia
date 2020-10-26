package com.novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.crafting.ItemStackWithChance;
import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class CrookRecipeSerializer extends RecipeSerializer<CrookRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(EnumCrook.WOOD.getRegistryObject().get());
    }

    @Override
    protected CrookRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.deserialize(json);
        JsonArray results = json.getAsJsonArray("results");
        List<ItemStackWithChance> output = new ArrayList<>(results.size());
        for(int i = 0; i < results.size(); i++) {
            output.set(i, readOutput(results.get(i)));
        }
        float chance = json.get("chance").getAsFloat();
        return new CrookRecipe(recipeId, input, output, chance);
    }

    @Override
    public CrookRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        int outputCount = buffer.readInt();
        List<ItemStack> output = new ArrayList<>(outputCount);
        for(int i = 0; i < outputCount; i++) {
            output.set(i, buffer.readItemStack());
        }
        Ingredient input = Ingredient.read(buffer);
        float chance = buffer.readFloat();
        return new CrookRecipe(recipeId, input, output, chance);
    }

    @Override
    public void write(PacketBuffer buffer, CrookRecipe recipe) {
        buffer.writeInt(recipe.getOutput().size());
        for(ItemStack stack : recipe.getOutput()) {
            buffer.writeItemStack(stack);
        }
        recipe.getInput().write(buffer);
        buffer.writeFloat(recipe.getChance());
    }
}
