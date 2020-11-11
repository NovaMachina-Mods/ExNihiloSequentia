package novamachina.exnihilosequentia.common.compat.jei.crucible;

import novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.api.crafting.crucible.CrucibleRecipe;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class WoodCrucibleTooltipCallback implements ITooltipCallback<ItemStack> {
    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<ITextComponent> tooltip) {
        if (input) {
            CrucibleRecipe meltable = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(ingredient.getItem());
            tooltip.add(new StringTextComponent(String.format("Fluid Amount: %d mb", meltable.getAmount())));
        }
    }
}
