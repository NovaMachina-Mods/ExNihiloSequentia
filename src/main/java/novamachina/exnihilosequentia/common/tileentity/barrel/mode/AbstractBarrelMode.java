package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class AbstractBarrelMode {
    @Nonnull private final String modeName;

    protected AbstractBarrelMode(@Nonnull final String name) {
        this.modeName = name;
    }

    @Nonnull
    public String getModeName() {
        return modeName;
    }

    public abstract void tick(@Nonnull final AbstractBarrelTile barrelTile);

    @Nonnull
    public abstract ActionResultType onBlockActivated(@Nonnull final AbstractBarrelTile barrelTile,
                                                      @Nonnull final PlayerEntity player, @Nonnull final Hand handIn,
                                                      @Nonnull final IFluidHandler fluidHandler,
                                                      @Nonnull final IItemHandler itemHandler);

    public abstract boolean canFillWithFluid(@Nonnull final AbstractBarrelTile barrel);

    public abstract boolean isEmptyMode();

    protected abstract boolean isTriggerItem(@Nonnull final ItemStack stack);

    public abstract void read(@Nonnull final CompoundNBT nbt);

    @Nonnull
    public abstract CompoundNBT write();

    protected abstract void spawnParticle(@Nonnull final AbstractBarrelTile barrelTile);

    @Nonnull
    public abstract List<ITextComponent> getWailaInfo(@Nonnull final AbstractBarrelTile barrelTile);

    @Nonnull
    public abstract ItemStack handleInsert(@Nonnull final AbstractBarrelTile barrelTile, @Nonnull final ItemStack stack,
                                           final boolean simulate);
}
