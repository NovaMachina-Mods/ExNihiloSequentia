package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.Level;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.item.dolls.DollItem;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

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
    public void tick(@Nonnull final AbstractBarrelTile barrelTile) {
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
    public InteractionResult onBlockActivated(@Nonnull final AbstractBarrelTile barrelTile,
                                             @Nonnull final Player player, @Nonnull final InteractionHand handIn,
                                             @Nonnull final IFluidHandler fluidHandler,
                                             @Nonnull final IItemHandler itemHandler) {
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canFillWithFluid(@Nonnull final AbstractBarrelTile barrel) {
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
            @Nullable final EnumDoll enumDoll = EnumDoll.getDollFromString(nbt.getString(DOLL_TYPE_TAG));
            if (enumDoll != null) {
                doll = (DollItem) enumDoll.getRegistryObject().get();
            } else {
                doll = null;
            }
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
            nbt.putString(DOLL_TYPE_TAG, doll.getDollType());
        }
        return nbt;
    }

    @Override
    protected void spawnParticle(@Nonnull final AbstractBarrelTile barrelTile) {
        @Nullable final Level world = barrelTile.getLevel();
        if (!(world instanceof ServerLevel))
            return;
        ((ServerLevel) world)
            .sendParticles(ParticleTypes.LARGE_SMOKE,
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
    public List<Component> getWailaInfo(@Nonnull final AbstractBarrelTile barrelTile) {
        @Nonnull final List<Component> info = new ArrayList<>();

        info.add(new TranslatableComponent("waila.progress", StringUtils
            .formatPercent((float) currentProgress / (Config.getSecondsToSpawn() * 20))));

        return info;
    }

    @Override
    @Nonnull
    public ItemStack handleInsert(@Nonnull final AbstractBarrelTile barrelTile, @Nonnull final ItemStack stack,
                                  boolean simulate) {
        return stack;
    }
}
