package com.novamachina.exnihilosequentia.common.jei.compost;

import net.minecraft.item.ItemStack;

import java.util.List;

public class CompostRecipe {
    private List<ItemStack> input;
    private ItemStack output;

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
