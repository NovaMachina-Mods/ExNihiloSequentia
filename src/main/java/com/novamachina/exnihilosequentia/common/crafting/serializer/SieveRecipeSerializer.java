package com.novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.sieve.MeshWithChance;
import com.novamachina.exnihilosequentia.common.api.crafting.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class SieveRecipeSerializer extends RecipeSerializer<SieveRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.SIEVE.get());
    }

    @Override
    protected SieveRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.deserialize(json.get("input"));
        ItemStack drop = new ItemStack(ForgeRegistries.ITEMS
            .getValue(new ResourceLocation(JSONUtils.getString(json, "result"))));
        List<MeshWithChance> meshWithChanceList = new ArrayList<>();
        for(JsonElement element : json.get("meshes").getAsJsonArray()) {
            meshWithChanceList.add(MeshWithChance.deserialize(element));
        }
        if(json.has("waterlogged")) {
            return new SieveRecipe(recipeId, input, drop, meshWithChanceList, true);
        }
        return new SieveRecipe(recipeId, input, drop, meshWithChanceList, false);
    }

    @Override
    public SieveRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        Ingredient input = Ingredient.read(buffer);
        ItemStack drop = buffer.readItemStack();
        List<MeshWithChance> meshWithChances = new ArrayList<>();
        int count = buffer.readInt();
        for(int i = 0; i < count; i++) {
            meshWithChances.add(MeshWithChance.read(buffer));
        }
        boolean isWaterlogged = buffer.readBoolean();
        return new SieveRecipe(recipeId, input, drop, meshWithChances, isWaterlogged);
    }

    @Override
    public void write(PacketBuffer buffer, SieveRecipe recipe) {
        recipe.getInput().write(buffer);
        buffer.writeItemStack(recipe.getDrop());
        buffer.writeInt(recipe.getMeshWithChances().size());
        for(MeshWithChance meshWithChance : recipe.getMeshWithChances()) {
            meshWithChance.write(buffer);
        }
        buffer.writeBoolean(recipe.isWaterlogged());
    }
}
