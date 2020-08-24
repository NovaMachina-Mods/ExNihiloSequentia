package com.novamachina.exnihilosequentia.common.compat.jei.compost;

import net.minecraft.item.ItemStack;

import java.util.List;

public class CompostRecipe {
    private final List<ItemStack> input;
    private final ItemStack output;

    public CompostRecipe(List<ItemStack> inputs, ItemStack output) {
        this.input = inputs;
        this.output = output;
    }

    public List<ItemStack> getInputs() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }
}
