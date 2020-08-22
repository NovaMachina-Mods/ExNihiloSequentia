package com.novamachina.exnihilosequentia.common.jei.heat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class HeatRecipe {
    private List<ItemStack> itemInputs;
    private List<FluidStack> fluidInputs;
    private String heatAmountString;

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
