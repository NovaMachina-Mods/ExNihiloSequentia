package com.novamachina.exnihilosequentia.common.compat.jei.fluidtransform;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FluidTransformJEIRecipe {
    private final FluidStack fluidInBarrel;
    private final ItemStack blockBelow;
    private final FluidStack result;

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
