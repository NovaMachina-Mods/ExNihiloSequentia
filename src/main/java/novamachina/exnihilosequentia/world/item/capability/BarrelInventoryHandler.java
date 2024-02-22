package novamachina.exnihilosequentia.world.item.capability;

import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;

public class BarrelInventoryHandler extends ItemStackHandler {

  private static final Map<BlockEntity, BarrelInventoryHandler> BLOCK_TO_HANDLER =
      new IdentityHashMap<>();

  public static BarrelInventoryHandler getHandler(BarrelBlockEntity entity) {
    return BLOCK_TO_HANDLER.computeIfAbsent(entity, (block) -> new BarrelInventoryHandler(entity));
  }

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
