package novamachina.exnihilosequentia.common.compat.jei.crook;

import java.util.List;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import javax.annotation.Nonnull;

public class CrookTooltipCallback implements ITooltipCallback<ItemStack> {
    @Nonnull private final CrookRecipe crookRecipe;

    public CrookTooltipCallback(@Nonnull final CrookRecipe crookRecipe) {
        this.crookRecipe = crookRecipe;
    }

    @Override
    public void onTooltip(final int slotIndex, final boolean input, @Nonnull final ItemStack ingredient,
                          @Nonnull final List<Component> tooltip) {
        if (!input) {
            crookRecipe.getOutput().stream()
                    .filter(stack -> ItemStack.isSame(ingredient, stack.getStack()))
                    .forEach(stack -> tooltip.add(new TextComponent(String.format("%s", StringUtils.formatPercent(stack.getChance())))));
        }
    }
}
