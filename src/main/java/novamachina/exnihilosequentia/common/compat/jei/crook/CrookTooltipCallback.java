package novamachina.exnihilosequentia.common.compat.jei.crook;

import java.util.List;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class CrookTooltipCallback implements ITooltipCallback<ItemStack> {
    private final CrookRecipe crookRecipe;

    public CrookTooltipCallback(CrookRecipe crookRecipe) {
        this.crookRecipe = crookRecipe;
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<ITextComponent> tooltip) {
        if (!input) {
            crookRecipe.getOutput().stream()
                    .filter(stack -> ItemStack.areItemsEqual(ingredient, stack.getStack()))
                    .forEach(stack -> tooltip.add(new StringTextComponent(String.format("%s", StringUtils.formatPercent(stack.getChance())))));
        }
    }
}
