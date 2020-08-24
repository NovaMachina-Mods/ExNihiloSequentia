package com.novamachina.exnihilosequentia.common.compat.jei.fluidontop;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class FluidOnTopJEIRecipe {
    private final FluidStack fluidInBarrel;
    private final FluidStack fluidOnTop;
    private final ItemStack result;

    public FluidOnTopJEIRecipe(FluidStack fluidInBarrel, FluidStack fluidOnTop, ItemStack result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public FluidStack getFluidInBarrel() {
        return fluidInBarrel;
    }

    public FluidStack getFluidOnTop() {
        return fluidOnTop;
    }

    public ItemStack getResult() {
        return result;
    }

    public List<FluidStack> getInputs() {
        return ImmutableList.of(fluidInBarrel, fluidOnTop);
    }

    public List<ItemStack> getOutputs() {
        return ImmutableList.of(result);
    }
}
