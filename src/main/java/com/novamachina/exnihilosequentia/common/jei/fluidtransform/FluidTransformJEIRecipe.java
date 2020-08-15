package com.novamachina.exnihilosequentia.common.jei.fluidtransform;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class FluidTransformJEIRecipe {
    private FluidStack fluidInBarrel;
    private ItemStack blockBelow;
    private FluidStack result;

    public FluidTransformJEIRecipe(FluidStack fluidInBarrel, ItemStack blockBelow, FluidStack result) {
        this.fluidInBarrel = fluidInBarrel;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    public FluidStack getFluidInBarrel() {
        return fluidInBarrel;
    }

    public ItemStack getBlockBelow() {
        return blockBelow;
    }

    public FluidStack getResult() {
        return result;
    }


}
