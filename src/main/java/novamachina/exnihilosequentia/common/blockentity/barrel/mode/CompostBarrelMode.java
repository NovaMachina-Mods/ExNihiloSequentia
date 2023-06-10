package novamachina.exnihilosequentia.common.blockentity.barrel.mode;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class CompostBarrelMode extends AbstractBarrelMode {

  private int currentProgress;

  public CompostBarrelMode(@Nonnull final String name) {
    super(name);
    currentProgress = 0;
  }

  @Override
  public void tick(@Nonnull final AbstractBarrelEntity barrelTile) {
    if (barrelTile.getSolidAmount() >= AbstractBarrelEntity.MAX_SOLID_AMOUNT
        && barrelTile.getInventory().getStackInSlot(0).isEmpty()) {
      currentProgress++;
      spawnParticle(barrelTile);
      if (currentProgress >= Config.getSecondsToCompost() * 20) {
        currentProgress = 0;
        barrelTile.getInventory().setStackInSlot(0, new ItemStack(Blocks.DIRT));
        barrelTile.removeSolid(barrelTile.getSolidAmount());
        barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
      }
    }
  }

  @Override
  @Nonnull
  public InteractionResult onBlockActivated(
      @Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler fluidHandler,
      @Nonnull final IItemHandler itemHandler) {
    if (ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(player.getItemInHand(handIn).getItem())
        && barrelTile.addSolid(
            ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(
                player.getItemInHand(handIn).getItem()),
            false)) {
      player.getItemInHand(handIn).shrink(1);
    }

    return InteractionResult.SUCCESS;
  }

  @Override
  public boolean canFillWithFluid(@Nonnull final AbstractBarrelEntity barrel) {
    return false;
  }

  @Override
  public boolean isEmptyMode() {
    return false;
  }

  @Override
  protected boolean isTriggerItem(@Nonnull final ItemStack stack) {
    return ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(stack.getItem());
  }

  @Override
  public void read(@Nonnull final CompoundTag nbt) {
    this.currentProgress = nbt.getInt("currentProgress");
  }

  @Override
  @Nonnull
  public CompoundTag write() {
    @Nonnull final CompoundTag modeInfo = new CompoundTag();
    modeInfo.putInt("currentProgress", currentProgress);
    return modeInfo;
  }

  @Override
  protected void spawnParticle(@Nonnull final AbstractBarrelEntity barrelTile)
      throws NullPointerException {
    if (Config.getShowParticles()) {
      @Nullable final ServerLevel level = (ServerLevel) barrelTile.getLevel();
      Preconditions.checkNotNull(level, "Level is null.");
      level.sendParticles(
          ParticleTypes.ASH,
          barrelTile.getBlockPos().getX() + (.2d + (.8d - .2d) * level.random.nextDouble()),
          barrelTile.getBlockPos().getY() + 1.2d,
          barrelTile.getBlockPos().getZ() + (.2d + (.8d - .2d) * level.random.nextDouble()),
          1,
          0,
          0,
          0,
          0.01);
    }
  }

  @Override
  @Nonnull
  public List<Component> getWailaInfo(@Nonnull final AbstractBarrelEntity barrelTile) {
    @Nonnull final List<Component> info = new ArrayList<>();
    if (currentProgress <= 0) {
      info.add(
          Component.translatable(
              "waila.barrel.compost",
              barrelTile.getSolidAmount(),
              AbstractBarrelEntity.MAX_SOLID_AMOUNT));
    } else {
      info.add(
          Component.translatable(
              "waila.progress",
              StringUtils.formatPercent(
                  (float) currentProgress / (Config.getSecondsToCompost() * 20))));
    }
    return info;
  }

  @Override
  @Nonnull
  public ItemStack handleInsert(
      @Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final ItemStack stack,
      final boolean simulate) {
    if (barrelTile.addSolid(
        ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(stack.getItem()), simulate)) {
      @Nonnull final ItemStack returnStack = stack.copy();
      returnStack.shrink(1);
      return returnStack;
    }
    return stack;
  }
}
