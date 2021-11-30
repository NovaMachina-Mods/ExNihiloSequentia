package novamachina.exnihilosequentia.common.compat.jei.crucible;

import java.util.List;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

public class WoodCrucibleTooltipCallback implements ITooltipCallback<ItemStack> {
    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<ITextComponent> tooltip) {
        if (input) {
            CrucibleRecipe meltable = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(ingredient);
            if (meltable == null)
                return;
            tooltip.add(new StringTextComponent(String.format("Fluid Amount: %d mb", meltable.getAmount())));
        }
    }
}
