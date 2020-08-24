package com.novamachina.exnihilosequentia.common.compat.jei.hammer;

import net.minecraft.item.ItemStack;

public class HammerRecipe {
    private final ItemStack input;
    private final ItemStack output;

    public HammerRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }
}
