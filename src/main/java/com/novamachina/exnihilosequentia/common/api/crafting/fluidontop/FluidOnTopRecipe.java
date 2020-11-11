package com.novamachina.exnihilosequentia.common.api.crafting.fluidontop;

import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.SerializableRecipe;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;

public class FluidOnTopRecipe extends SerializableRecipe {
    public static IRecipeType<FluidOnTopRecipe> TYPE = IRecipeType.register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_on_top");
    public static RegistryObject<RecipeSerializer<FluidOnTopRecipe>> SERIALIZER;
    private final FluidStack fluidInTank;
    private final FluidStack fluidOnTop;
    private final ItemStack result;

    public FluidOnTopRecipe(ResourceLocation id, FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
        super(result, TYPE, id);
        this.fluidInTank = fluidInTank;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public FluidStack getFluidInTank() {
        return fluidInTank;
    }

    public FluidStack getFluidOnTop() {
        return fluidOnTop;
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result;
    }

    public boolean validInputs(Fluid fluidInTank, Fluid fluidOnTop) {
        return this.fluidInTank.getFluid().isEquivalentTo(fluidInTank) && this.fluidOnTop.getFluid().isEquivalentTo(fluidOnTop);
    }
}
