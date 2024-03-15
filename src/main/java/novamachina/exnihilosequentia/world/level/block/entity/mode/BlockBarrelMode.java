package novamachina.exnihilosequentia.world.level.block.entity.mode;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;

public class BlockBarrelMode extends AbstractBarrelMode {

  public BlockBarrelMode(@Nonnull final String name) {
    super(name);
  }

  @Override
  public void tick(@Nonnull final BarrelBlockEntity barrelTile) {
    // NOOP
  }

  @Override
  @Nonnull
  public InteractionResult onBlockActivated(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler fluidHandler,
      @Nonnull final IItemHandler itemHandler) {
    @Nullable final Level world = barrelTile.getLevel();
    if (world != null) {
      world.addFreshEntity(
          new ItemEntity(
              barrelTile.getLevel(),
              barrelTile.getBlockPos().getX() + 0.5F,
              barrelTile.getBlockPos().getY() + 0.5F,
              barrelTile.getBlockPos().getZ() + 0.5F,
              new ItemStack(barrelTile.getInventory().getStackInSlot(0).getItem())));
    }
    barrelTile.getInventory().setStackInSlot(0, ItemStack.EMPTY);
    barrelTile.setMode(ExNihiloConstants.BarrelModes.EMPTY);
    return InteractionResult.SUCCESS;
  }

  @Override
  public boolean canFillWithFluid(@Nonnull final BarrelBlockEntity barrel) {
    return false;
  }

  @Override
  public boolean isEmptyMode() {
    return false;
  }

  @Override
  protected boolean isTriggerItem(@Nonnull final ItemStack stack) {
    return false;
  }

  @Override
  public void read(@Nonnull final CompoundTag nbt) {
    // NOOP
  }

  @Override
  @Nonnull
  public CompoundTag write() {
    return new CompoundTag();
  }

  @Override
  protected void spawnParticle(@Nonnull final BarrelBlockEntity barrelTile) {
    // NOOP
  }

  @Override
  @Nonnull
  public List<Component> getWailaInfo(@Nonnull final BarrelBlockEntity barrelTile) {
    @Nullable final List<Component> info = new ArrayList<>();

    info.add(
        Component.translatable(
            "waila.barrel.block",
            Component.translatable(
                barrelTile.getInventory().getStackInSlot(0).getDescriptionId())));

    return info;
  }

  @Override
  @Nonnull
  public ItemStack handleInsert(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final ItemStack stack,
      final boolean simulate) {
    return stack;
  }
}
