package com.novamachina.exnihilosequentia.common.jei.crucible;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class CrucibleRecipe {
    private List<ItemStack> inputs;
    private FluidStack output;

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
