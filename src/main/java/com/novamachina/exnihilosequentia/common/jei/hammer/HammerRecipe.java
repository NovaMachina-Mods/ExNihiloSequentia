package com.novamachina.exnihilosequentia.common.jei.hammer;

import net.minecraft.item.ItemStack;

public class HammerRecipe {
    private ItemStack input;
    private ItemStack output;

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
