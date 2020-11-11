package com.novamachina.exnihilosequentia.common.api.crafting.fluidtransform;

import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.SerializableRecipe;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;

public class FluidTransformRecipe extends SerializableRecipe {
    public static IRecipeType<FluidTransformRecipe> TYPE = IRecipeType
        .register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_transform");
    public static RegistryObject<RecipeSerializer<FluidTransformRecipe>> SERIALIZER;
    private final FluidStack fluidInTank;
    private final Ingredient blockBelow;
    private final FluidStack result;

    public FluidTransformRecipe(ResourceLocation id, FluidStack fluidInTank, Ingredient blockBelow, FluidStack result) {
        super(null, TYPE, id);
        this.fluidInTank = fluidInTank;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    public FluidStack getFluidInTank() {
        return fluidInTank;
    }

    public Ingredient getBlockBelow() {
        return blockBelow;
    }

    public FluidStack getResult() {
        return result;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
