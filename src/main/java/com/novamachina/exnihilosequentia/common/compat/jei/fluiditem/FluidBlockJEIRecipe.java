package com.novamachina.exnihilosequentia.common.compat.jei.fluiditem;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FluidBlockJEIRecipe {
    private final FluidStack fluidInBarrel;
    private final ItemStack input;
    private final ItemStack result;

    public FluidBlockJEIRecipe(FluidStack fluidInBarrel, ItemStack input, ItemStack result) {
        this.fluidInBarrel = fluidInBarrel;
        this.input = input;
        this.result = result;
    }

    public FluidStack getFluidInBarrel() {
        return fluidInBarrel;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getResult() {
        return result;
    }
}
