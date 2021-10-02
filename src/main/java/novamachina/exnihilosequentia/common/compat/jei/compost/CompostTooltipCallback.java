package novamachina.exnihilosequentia.common.compat.jei.compost;

import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.utility.Config;

import java.util.List;



public class CompostTooltipCallback implements ITooltipCallback<ItemStack> {

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack itemStack, List<Component> list) {
        if (input) {
            int solidAmount = ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(itemStack.getItem());

            list.add(new TextComponent(String
                    .format("Amount: %d / %d", solidAmount, Config.getBarrelMaxSolidAmount())));
        }
    }


}
