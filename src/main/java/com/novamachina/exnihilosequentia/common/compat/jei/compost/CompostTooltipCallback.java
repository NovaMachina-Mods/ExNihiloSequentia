package com.novamachina.exnihilosequentia.common.compat.jei.compost;

import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.utility.Config;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CompostTooltipCallback implements ITooltipCallback<ItemStack> {
    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {
        if (input) {
            int solidAmount = ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(ingredient.getItem());

            tooltip.add(String.format("Amount: %d / %d", solidAmount, Config.BARREL_MAX_SOLID_AMOUNT.get()));
        }
    }
}
