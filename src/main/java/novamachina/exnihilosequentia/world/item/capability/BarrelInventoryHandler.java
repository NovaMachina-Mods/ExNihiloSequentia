package novamachina.exnihilosequentia.world.item.capability;

import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;

public class BarrelInventoryHandler extends ItemStackHandler {

  @Nonnull private final BarrelBlockEntity barrel;

  public BarrelInventoryHandler(@Nonnull final BarrelBlockEntity barrelBlockEntity) {
    this.barrel = barrelBlockEntity;
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
    if (!simulate && returnStack != ItemStack.EMPTY) {
      barrel.setMode(ExNihiloConstants.BarrelModes.EMPTY);
    }
    return returnStack;
  }
}
