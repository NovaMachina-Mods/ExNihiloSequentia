package novamachina.exnihilosequentia.common.compat.jei.crucible;

import java.util.List;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

public class FiredCrucibleTooltipCallback implements ITooltipCallback<ItemStack> {
    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<MutableComponent> tooltip) {
        if (input) {
            CrucibleRecipe meltable = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(ingredient.getItem());
            tooltip.add(new TextComponent(String.format("Fluid Amount: %d mb", meltable.getAmount())));
        }
    }
}
