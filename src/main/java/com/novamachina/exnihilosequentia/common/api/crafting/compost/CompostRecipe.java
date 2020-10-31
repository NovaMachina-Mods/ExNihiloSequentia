package com.novamachina.exnihilosequentia.common.api.crafting.compost;

import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.SerializableRecipe;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class CompostRecipe extends SerializableRecipe {
    public static IRecipeType<CompostRecipe> TYPE = IRecipeType.register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":compost");
    public static RegistryObject<RecipeSerializer<CompostRecipe>> SERIALIZER;
    private final Ingredient input;
    private final int amount;

    public CompostRecipe(ResourceLocation id, Ingredient input, int amount) {
        super(null, TYPE, id);
        this.input = input;
        this.amount = amount;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    protected RecipeSerializer getIESerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getMatchingStacks());
    }
}
