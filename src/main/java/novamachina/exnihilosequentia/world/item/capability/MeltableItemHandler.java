package novamachina.exnihilosequentia.world.item.capability;

import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;

public class MeltableItemHandler extends ItemStackHandler {

  private static final Map<BlockEntity, MeltableItemHandler> BLOCK_TO_MELTABLE =
      new IdentityHashMap<>();

  public static MeltableItemHandler getHandler(CrucibleBlockEntity entity) {
    return BLOCK_TO_MELTABLE.computeIfAbsent(entity, block -> new MeltableItemHandler(entity));
  }

  private boolean crucibleHasRoom = true;
  @Nullable private CrucibleBlockEntity crucibleBlockEntity;

  public MeltableItemHandler(@Nonnull final CrucibleBlockEntity crucibleEntity) {
    super(1);
    crucibleBlockEntity = crucibleEntity;
  }

  @Nonnull
  @Override
  public ItemStack insertItem(
      final int slot, @Nonnull final ItemStack stack, final boolean simulate) {
    if (crucibleHasRoom) {
      return super.insertItem(slot, stack, simulate);
    }
    return stack;
  }

  public void setCrucibleHasRoom(final boolean crucibleHasRoom) {
    this.crucibleHasRoom = crucibleHasRoom;
  }

  @Override
  public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
    if (crucibleBlockEntity == null) {
      return false;
    }
    return ExNihiloRegistries.CRUCIBLE_REGISTRY.isMeltable(
        stack.getItem(), crucibleBlockEntity.getCrucibleType().getLevel());
  }

  @Override
  protected int getStackLimit(final int slot, @Nonnull final ItemStack stack) {
    return 3;
  }

  @Override
  @Nonnull
  public CompoundTag serializeNBT(HolderLookup.Provider provider) {
    @Nonnull final CompoundTag nbt = super.serializeNBT(provider);
    nbt.putBoolean("hasRoom", crucibleHasRoom);
    return nbt;
  }

  @Override
  public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
    super.deserializeNBT(provider, nbt);
    crucibleHasRoom = nbt.getBoolean("hasRoom");
  }

  @Nonnull
  @Override
  public ItemStack extractItem(int slot, int amount, boolean simulate) {
    return ItemStack.EMPTY;
  }
}
