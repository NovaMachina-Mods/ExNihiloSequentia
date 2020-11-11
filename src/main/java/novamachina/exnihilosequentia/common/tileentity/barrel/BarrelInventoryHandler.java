package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class BarrelInventoryHandler extends ItemStackHandler {

    private final AbstractBarrelTile barrel;

    public BarrelInventoryHandler(AbstractBarrelTile abstractBarrelTile) {
        this.barrel = abstractBarrelTile;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return barrel.getMode().handleInsert(barrel, stack);
    }
}
