package novamachina.exnihilosequentia.common.compat.jei.crook;

import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import java.util.List;



public class CrookTooltipCallback implements ITooltipCallback<ItemStack> {
    private final CrookRecipe crookRecipe;

    public CrookTooltipCallback(CrookRecipe crookRecipe) {
        this.crookRecipe = crookRecipe;
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<Component> tooltip) {
        if (!input) {
            crookRecipe.getOutput().stream()
                    .filter(stack -> ItemStack.isSame(ingredient, stack.getStack()))
                    .forEach(stack -> tooltip.add(new TextComponent(String.format("%s", StringUtils.formatPercent(stack.getChance())))));
        }
    }

}
