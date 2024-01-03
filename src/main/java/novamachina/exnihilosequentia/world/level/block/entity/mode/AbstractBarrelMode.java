package novamachina.exnihilosequentia.world.level.block.entity.mode;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;

public abstract class AbstractBarrelMode {

  @Nonnull private final String modeName;

  protected AbstractBarrelMode(@Nonnull final String name) {
    this.modeName = name;
  }

  @Nonnull
  public String getModeName() {
    return modeName;
  }

  public abstract void tick(@Nonnull final BarrelBlockEntity barrelTile);

  @Nonnull
  public abstract InteractionResult onBlockActivated(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler fluidHandler,
      @Nonnull final IItemHandler itemHandler);

  public abstract boolean canFillWithFluid(@Nonnull final BarrelBlockEntity barrel);

  public abstract boolean isEmptyMode();

  protected abstract boolean isTriggerItem(@Nonnull final ItemStack stack);

  public abstract void read(@Nonnull final CompoundTag nbt);

  @Nonnull
  public abstract CompoundTag write();

  protected abstract void spawnParticle(@Nonnull final BarrelBlockEntity barrelTile);

  @Nonnull
  public abstract List<Component> getWailaInfo(@Nonnull final BarrelBlockEntity barrelTile);

  @Nonnull
  public abstract ItemStack handleInsert(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final ItemStack stack,
      final boolean simulate);
}
