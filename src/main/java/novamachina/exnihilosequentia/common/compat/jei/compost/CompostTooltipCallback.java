package novamachina.exnihilosequentia.common.compat.jei.compost;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class CompostTooltipCallback implements ITooltipCallback<ItemStack> {

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack itemStack, List<ITextComponent> list) {
        if (input) {
            int solidAmount = ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(itemStack.getItem());

            list.add(new StringTextComponent(String
                .format("Amount: %d / %d", solidAmount, Config.BARREL_MAX_SOLID_AMOUNT.get())));
        }
    }
}
