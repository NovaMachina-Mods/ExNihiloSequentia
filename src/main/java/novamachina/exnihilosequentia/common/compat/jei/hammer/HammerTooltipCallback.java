package novamachina.exnihilosequentia.common.compat.jei.hammer;

import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import java.util.List;

public class HammerTooltipCallback implements ITooltipCallback<ItemStack> {
    private final HammerRecipe hammerRecipe;

    public HammerTooltipCallback(HammerRecipe hammerRecipe) {
        this.hammerRecipe = hammerRecipe;
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<Component> tooltip) {
        if (!input) {
            hammerRecipe.getOutput().stream()
                    .filter(stack -> ItemStack.isSame(ingredient, stack.getStack()))
                    .forEach(stack -> tooltip.add(new TextComponent(String.format("%s", StringUtils.formatPercent(stack.getChance())))));
        }
    }
}
