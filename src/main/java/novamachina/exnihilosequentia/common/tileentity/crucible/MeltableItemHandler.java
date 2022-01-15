package novamachina.exnihilosequentia.common.tileentity.crucible;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MeltableItemHandler extends ItemStackHandler {

    private boolean crucibleHasRoom = true;
    @Nullable private CrucibleTypeEnum type;

    public MeltableItemHandler(@Nonnull final CrucibleTypeEnum crucibleType) {
        super(1);
        type = crucibleType;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(final int slot, @Nonnull final ItemStack stack, final boolean simulate) {
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
        return ExNihiloRegistries.CRUCIBLE_REGISTRY.isMeltableByItemStack(stack, type.getLevel());
    }

    @Override
    protected int getStackLimit(final int slot, @Nonnull final ItemStack stack) {
        return 3;
    }

    @Override
    @Nonnull
    public CompoundNBT serializeNBT() {
        @Nonnull final CompoundNBT nbt = super.serializeNBT();
        nbt.putBoolean("hasRoom", crucibleHasRoom);
        if (type != null) {
            nbt.putString("type", type.getName());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        crucibleHasRoom = nbt.getBoolean("hasRoom");
        type = CrucibleTypeEnum.getTypeByName(nbt.getString("type"));
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return ItemStack.EMPTY;
    }
}
