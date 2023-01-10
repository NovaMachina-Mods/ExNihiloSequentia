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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class FluidTransformBarrelMode extends AbstractBarrelMode {

  private int currentProgress;
  @Nullable private ItemLike catalyst;

  public FluidTransformBarrelMode(String name) {
    super(name);
    currentProgress = 0;
  }

  public void setCatalyst(@Nonnull final ItemLike catalyst) {
    this.catalyst = catalyst;
  }

  @Override
  public void tick(@Nonnull final AbstractBarrelEntity barrelTile) {
    @Nonnull final Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();
    currentProgress++;
    spawnParticle(barrelTile);
    if (currentProgress >= Config.getSecondsToFluidTransform() * 20) {
      currentProgress = 0;
      @Nonnull
      final Fluid newFluid =
          ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getResult(fluidInTank, catalyst);
      barrelTile
          .getTank()
          .setFluid(new FluidStack(newFluid, AbstractBarrelEntity.MAX_FLUID_AMOUNT));
      barrelTile.setMode(ExNihiloConstants.BarrelModes.FLUID);
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
    return InteractionResult.PASS;
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
    return false;
  }

  @Override
  public void read(@Nonnull final CompoundTag nbt) {
    currentProgress = nbt.getInt("currentProgress");
    catalyst = ItemStack.of(nbt).getItem();
  }

  @Override
  @Nonnull
  public CompoundTag write() {
    @Nonnull final CompoundTag nbt = new CompoundTag();
    nbt.putInt("currentProgress", currentProgress);
    new ItemStack(catalyst).save(nbt);
    return nbt;
  }

  @Override
  protected void spawnParticle(@Nonnull final AbstractBarrelEntity barrelTile)
      throws NullPointerException {
    @Nullable final ServerLevel level = (ServerLevel) barrelTile.getLevel();
    Preconditions.checkNotNull(level, "Level is null.");
    level.sendParticles(
        ParticleTypes.EFFECT,
        barrelTile.getBlockPos().getX() + barrelTile.getLevel().random.nextDouble(),
        barrelTile.getBlockPos().getY() + barrelTile.getLevel().random.nextDouble(),
        barrelTile.getBlockPos().getZ() + barrelTile.getLevel().random.nextDouble(),
        1,
        0,
        0,
        0,
        0.05);
  }

  @Nonnull
  @Override
  public List<Component> getWailaInfo(@Nonnull final AbstractBarrelEntity barrelTile) {
    @Nullable final List<Component> info = new ArrayList<>();

    info.add(
        Component.translatable(
            "waila.progress",
            StringUtils.formatPercent(
                (float) currentProgress / (Config.getSecondsToFluidTransform() * 20))));

    return info;
  }

  @Nonnull
  @Override
  public ItemStack handleInsert(
      @Nonnull final AbstractBarrelEntity barrelTile,
      @Nonnull final ItemStack stack,
      final boolean simulate) {
    return stack;
  }
}
