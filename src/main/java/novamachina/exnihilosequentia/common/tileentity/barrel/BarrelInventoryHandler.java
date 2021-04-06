package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

public class BarrelInventoryHandler extends ItemStackHandler {

    private final AbstractBarrelTile barrel;

    public BarrelInventoryHandler(AbstractBarrelTile abstractBarrelTile) {
        this.barrel = abstractBarrelTile;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return barrel.getMode().handleInsert(barrel, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack returnStack = super.extractItem(slot, amount, simulate);
        if(!simulate && returnStack != ItemStack.EMPTY ) {
            barrel.setMode(ExNihiloConstants.BarrelModes.EMPTY);
        }
        return returnStack;
    }
}
