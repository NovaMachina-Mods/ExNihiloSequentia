package com.novamachina.exnihilosequentia.common.jei.compost;

import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Config;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CompostTooltipCallback implements ITooltipCallback<ItemStack> {
    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {
        if(input) {
            int solidAmount = ModRegistries.COMPOST.getSolidAmount(ingredient.getItem());

            tooltip.add(String.format("Amount: %d / %d", solidAmount, Config.BARREL_MAX_SOLID_AMOUNT.get()));
        }
    }
}
