package com.novamachina.exnihilosequentia.common.compat.jei.sieve;

import net.minecraft.item.ItemStack;

import java.util.List;

public class SieveRecipe {
    private final List<List<ItemStack>> inputs;
    private final List<ItemStack> outputs;

    public SieveRecipe(List<List<ItemStack>> inputs, List<ItemStack> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<List<ItemStack>> getInputs() {
        return inputs;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    public ItemStack getMesh() {
        return inputs.get(0).get(0);
    }

    public List<ItemStack> getSieveables() {
        return inputs.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SieveRecipe recipe = (SieveRecipe) o;
        return inputs.equals(recipe.inputs) &&
            outputs.equals(recipe.outputs);
    }
}
