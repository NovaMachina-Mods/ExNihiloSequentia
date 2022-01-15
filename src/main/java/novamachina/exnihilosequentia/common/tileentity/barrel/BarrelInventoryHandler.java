package novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

public class BarrelInventoryHandler extends ItemStackHandler {

    @Nonnull private final AbstractBarrelTile barrel;

    public BarrelInventoryHandler(@Nonnull final AbstractBarrelTile abstractBarrelTile) {
        this.barrel = abstractBarrelTile;
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull final ItemStack stack, boolean simulate) {
        return barrel.getMode().handleInsert(barrel, stack, simulate);
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        @Nonnull final ItemStack returnStack = super.extractItem(slot, amount, simulate);
        if(!simulate && returnStack != ItemStack.EMPTY ) {
            barrel.setMode(ExNihiloConstants.BarrelModes.EMPTY);
        }
        return returnStack;
    }
}
