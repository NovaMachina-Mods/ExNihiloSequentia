package novamachina.exnihilosequentia.world.item.capability;

import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;

public class MeltableItemHandler extends ItemStackHandler {

  private static final Map<BlockEntity, MeltableItemHandler> BLOCK_TO_MELTABLE =
      new IdentityHashMap<>();

  public static MeltableItemHandler getHandler(CrucibleBlockEntity entity) {
    return BLOCK_TO_MELTABLE.computeIfAbsent(
        entity, (block) -> new MeltableItemHandler(entity.getCrucibleType()));
  }

  private boolean crucibleHasRoom = true;
  @Nullable private CrucibleType type;

  public MeltableItemHandler(@Nonnull final CrucibleType crucibleType) {
    super(1);
    type = crucibleType;
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
    if (type == null) {
      return false;
    }
    return ExNihiloRegistries.CRUCIBLE_REGISTRY.isMeltable(stack.getItem(), type.getLevel());
  }

  @Override
  protected int getStackLimit(final int slot, @Nonnull final ItemStack stack) {
    return 3;
  }

  @Override
  @Nonnull
  public CompoundTag serializeNBT() {
    @Nonnull final CompoundTag nbt = super.serializeNBT();
    nbt.putBoolean("hasRoom", crucibleHasRoom);
    if (type != null) {
      nbt.putString("type", type.getName());
    }
    return nbt;
  }

  @Override
  public void deserializeNBT(CompoundTag nbt) {
    super.deserializeNBT(nbt);
    crucibleHasRoom = nbt.getBoolean("hasRoom");
    type = CrucibleType.getTypeByName(nbt.getString("type"));
  }

  @Nonnull
  @Override
  public ItemStack extractItem(int slot, int amount, boolean simulate) {
    return ItemStack.EMPTY;
  }
}
