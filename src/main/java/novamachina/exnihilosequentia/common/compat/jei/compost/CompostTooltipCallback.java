package novamachina.exnihilosequentia.common.compat.jei.compost;

import java.util.List;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nonnull;

public class CompostTooltipCallback implements ITooltipCallback<ItemStack> {

    @Override
    public void onTooltip(final int slotIndex, final boolean input, @Nonnull final ItemStack itemStack,
                          @Nonnull final List<Component> list) {
        if (input) {
            final int solidAmount = ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(itemStack.getItem());

            list.add(new TextComponent(String
                    .format("Amount: %d / %d", solidAmount, Config.getBarrelMaxSolidAmount())));
        }
    }
}
