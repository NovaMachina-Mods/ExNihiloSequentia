package novamachina.exnihilosequentia.world.level.block.entity.mode;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.DollItem;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;
import novamachina.novacore.util.StringUtils;

public class MobSpawnBarrelMode extends AbstractBarrelMode {

  @Nonnull private static final String CURRENT_PROGRESS_TAG = "currentProgress";
  @Nonnull private static final String DOLL_TYPE_TAG = "dollType";
  private int currentProgress;
  @Nullable private DollItem doll;

  public MobSpawnBarrelMode(@Nonnull final String name) {
    super(name);
    currentProgress = 0;
    doll = null;
  }

  public void setDoll(@Nullable final DollItem doll) {
    this.doll = doll;
  }

  @Override
  public void tick(@Nonnull final BarrelBlockEntity barrelTile) {
    if (doll == null) {
      return;
    }
    currentProgress++;
    spawnParticle(barrelTile);
    if (currentProgress >= Config.getSecondsToSpawn() * 20
        && doll.spawnMob(barrelTile.getLevel(), barrelTile.getBlockPos())) {
      barrelTile.getTank().setFluid(FluidStack.EMPTY);
      barrelTile.setMode(ExNihiloConstants.BarrelModes.EMPTY);
    }
  }

  @Override
  @Nonnull
  public InteractionResult onBlockActivated(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler fluidHandler,
      @Nonnull final IItemHandler itemHandler) {
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
    if (nbt.contains(CURRENT_PROGRESS_TAG)) {
      this.currentProgress = nbt.getInt(CURRENT_PROGRESS_TAG);
    } else {
      this.currentProgress = 0;
    }
    if (nbt.contains(DOLL_TYPE_TAG)) {
      doll =
          (DollItem) BuiltInRegistries.ITEM.get(new ResourceLocation(nbt.getString(DOLL_TYPE_TAG)));
    } else {
      doll = null;
    }
  }

  @Override
  @Nonnull
  public CompoundTag write() {
    @Nonnull final CompoundTag nbt = new CompoundTag();
    nbt.putInt(CURRENT_PROGRESS_TAG, currentProgress);
    if (doll != null) {
      String name = BuiltInRegistries.ITEM.getKey(doll).toString();
      nbt.putString(DOLL_TYPE_TAG, name);
    }
    return nbt;
  }

  @Override
  protected void spawnParticle(@Nonnull final BarrelBlockEntity barrelTile) {
    @Nullable final Level world = barrelTile.getLevel();
    if (!(world instanceof ServerLevel)) {
      return;
    }
    ((ServerLevel) world)
        .sendParticles(
            ParticleTypes.LARGE_SMOKE,
            barrelTile.getBlockPos().getX() + barrelTile.getLevel().random.nextDouble(),
            barrelTile.getBlockPos().getY() + barrelTile.getLevel().random.nextDouble(),
            barrelTile.getBlockPos().getZ() + barrelTile.getLevel().random.nextDouble(),
            5,
            0.0,
            0.0,
            0.0,
            0.05);
  }

  @Override
  @Nonnull
  public List<Component> getWailaInfo(@Nonnull final BarrelBlockEntity barrelTile) {
    @Nonnull final List<Component> info = new ArrayList<>();

    info.add(
        Component.translatable(
            "waila.progress",
            StringUtils.formatPercent(
                (float) currentProgress / (Config.getSecondsToSpawn() * 20))));

    return info;
  }

  @Override
  @Nonnull
  public ItemStack handleInsert(
      @Nonnull final BarrelBlockEntity barrelTile,
      @Nonnull final ItemStack stack,
      boolean simulate) {
    return stack;
  }
}
