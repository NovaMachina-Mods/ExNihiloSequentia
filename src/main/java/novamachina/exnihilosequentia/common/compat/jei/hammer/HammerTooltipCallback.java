package novamachina.exnihilosequentia.common.compat.jei.hammer;

import java.util.List;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class HammerTooltipCallback implements ITooltipCallback<ItemStack> {
    private final HammerRecipe hammerRecipe;

    public HammerTooltipCallback(HammerRecipe hammerRecipe) {
        this.hammerRecipe = hammerRecipe;
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<ITextComponent> tooltip) {
        if (!input) {
            hammerRecipe.getOutput().stream()
                    .filter(stack -> ItemStack.isSame(ingredient, stack.getStack()))
                    .forEach(stack -> tooltip.add(new StringTextComponent(String.format("%s", StringUtils.formatPercent(stack.getChance())))));
        }
    }
}
