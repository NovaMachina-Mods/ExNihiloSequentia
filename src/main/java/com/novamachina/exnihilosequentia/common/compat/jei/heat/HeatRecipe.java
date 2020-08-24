package com.novamachina.exnihilosequentia.common.compat.jei.heat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class HeatRecipe {
    private final List<ItemStack> itemInputs;
    private final List<FluidStack> fluidInputs;
    private final String heatAmountString;

    public HeatRecipe(List<ItemStack> itemInputs, List<FluidStack> fluidInputs, int heatAmountString) {
        this.itemInputs = itemInputs;
        this.fluidInputs = fluidInputs;
        this.heatAmountString = heatAmountString + "x";
    }

    public String getHeatAmountString() {
        return heatAmountString;
    }

    public List<FluidStack> getFluidInputs() {
        return fluidInputs;
    }

    public List<ItemStack> getItemInputs() {
        return itemInputs;
    }
}
