package com.novamachina.exnihilosequentia.common.jei.crucible;

import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.Meltable;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;

import java.util.List;

public class WoodCrucibleTooltipCallback implements ITooltipCallback<ItemStack> {
    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {
        if (input) {
            Meltable meltable = ModRegistries.WOODEN_CRUCIBLE.getMeltable(ingredient.getItem());
            tooltip.add(String.format("Fluid Amount: %d mb", meltable.getAmount()));
        }
    }
}
