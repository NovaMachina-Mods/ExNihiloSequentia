package com.novamachina.exnihilosequentia.common.compat.jei.crucible;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class CrucibleRecipe {
    private final List<ItemStack> inputs;
    private final FluidStack output;

    public CrucibleRecipe(List<ItemStack> inputs, FluidStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public FluidStack getOutput() {
        return output;
    }
}
