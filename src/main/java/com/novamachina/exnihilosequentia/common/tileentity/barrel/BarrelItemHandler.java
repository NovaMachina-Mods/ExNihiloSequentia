package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class BarrelItemHandler extends ItemStackHandler {

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return BarrelSolids.containsSolid(stack.getItem().getRegistryName().toString());
    }
}
