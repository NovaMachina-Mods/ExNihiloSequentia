package com.novamachina.exnihilosequentia.common.compat.jei.crook;

import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.utility.StringUtils;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class CrookTooltipCallback implements ITooltipCallback<ItemStack> {
    private final CrookRecipe crookRecipe;

    public CrookTooltipCallback(CrookRecipe crookRecipe) {
        this.crookRecipe = crookRecipe;
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<ITextComponent> tooltip) {
        if (!input) {
            ExNihiloRegistries.CROOK_REGISTRY.getDrops().stream()
                .filter(reward -> ItemStack.areItemsEqual(new ItemStack(ForgeRegistries.ITEMS
                    .getValue(reward.getItem())), ingredient))
                .forEach(reward -> tooltip
                    .add(new StringTextComponent(String.format("%s", StringUtils.formatPercent(reward.getRarity())))));
        }
    }
}
