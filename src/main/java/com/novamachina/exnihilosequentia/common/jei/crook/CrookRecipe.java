package com.novamachina.exnihilosequentia.common.jei.crook;

import net.minecraft.item.ItemStack;

import java.util.List;

public class CrookRecipe {
    private final List<ItemStack> inputs;
    private final List<ItemStack> outputs;

    public CrookRecipe(List<ItemStack> inputs, List<ItemStack> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<ItemStack> getInputs() {
        return inputs;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }
}
