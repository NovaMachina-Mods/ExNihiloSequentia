package novamachina.exnihilosequentia.common.blockentity.barrel.mode;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;

public abstract class AbstractBarrelMode {

  @Nonnull private final String modeName;

  protected AbstractBarrelMode(@Nonnull final String name) {
    this.modeName = name;
  }

  @Nonnull
  public String getModeName() {
    return modeName;
  }

  public abstract void tick(@Nonnull final AbstractBarrelEntity barrelTile);

  @Nonnull
  public abstract InteractionResult onBlockActivated(
      @Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler fluidHandler,
      @Nonnull final IItemHandler itemHandler);

  public abstract boolean canFillWithFluid(@Nonnull final AbstractBarrelEntity barrel);

  public abstract boolean isEmptyMode();

  protected abstract boolean isTriggerItem(@Nonnull final ItemStack stack);

  public abstract void read(@Nonnull final CompoundTag nbt);

  @Nonnull
  public abstract CompoundTag write();

  protected abstract void spawnParticle(@Nonnull final AbstractBarrelEntity barrelTile);

  @Nonnull
  public abstract List<Component> getWailaInfo(@Nonnull final AbstractBarrelEntity barrelTile);

  @Nonnull
  public abstract ItemStack handleInsert(
      @Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final ItemStack stack,
      final boolean simulate);
}
