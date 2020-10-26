package com.novamachina.exnihilosequentia.common.api.crafting.crook;

import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.SerializableRecipe;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;

public class CrookRecipe extends SerializableRecipe {
    public static IRecipeType<CrookRecipe> TYPE = IRecipeType
        .register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":crook");
    public static RegistryObject<RecipeSerializer<CrookRecipe>> SERIALIZER;
    private final Ingredient input;

    public List<ItemStack> getOutput() {
        return output;
    }

    private final List<ItemStack> output;
    private final float chance;
    public CrookRecipe(ResourceLocation id, Ingredient input, List<ItemStack> output, float chance) {
        super(output.get(0), TYPE, id);
        this.input = input;
        this.output = output;
        this.chance = chance;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    protected RecipeSerializer getIESerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.get(0);
    }

    public float getChance() {
        return chance;
    }
}
