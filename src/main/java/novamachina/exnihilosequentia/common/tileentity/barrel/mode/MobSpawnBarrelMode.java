package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.item.dolls.DollItem;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MobSpawnBarrelMode extends AbstractBarrelMode {
    private static final String CURRENT_PROGRESS_TAG = "currentProgress";
    private static final String DOLL_TYPE_TAG = "dollType";
    private int currentProgress;
    private DollItem doll;

    public MobSpawnBarrelMode(String name) {
        super(name);
        currentProgress = 0;
        doll = null;
    }

    public void setDoll(DollItem doll) {
        this.doll = doll;
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        if (doll != null) {
            currentProgress++;
            spawnParticle(barrelTile);
            if (currentProgress >= Config.getSecondsToSpawn() * 20 && doll.spawnMob(barrelTile.getLevel(), barrelTile.getBlockPos())) {
                barrelTile.getTank().setFluid(FluidStack.EMPTY);
                barrelTile.setMode(ExNihiloConstants.BarrelModes.EMPTY);
            }
        }
    }

    @Override
    public InteractionResult onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canFillWithFluid(AbstractBarrelTile barrel) {
        return false;
    }

    @Override
    public boolean isEmptyMode() {
        return false;
    }

    @Override
    protected boolean isTriggerItem(ItemStack stack) {
        return false;
    }

    @Override
    public void read(CompoundTag nbt) {
        if (nbt.contains(CURRENT_PROGRESS_TAG)) {
            this.currentProgress = nbt.getInt(CURRENT_PROGRESS_TAG);
        } else {
            this.currentProgress = 0;
        }
        if (nbt.contains(DOLL_TYPE_TAG)) {
            setDoll((DollItem) Objects.requireNonNull(EnumDoll.getDollFromString(nbt.getString(DOLL_TYPE_TAG))).getRegistryObject().get());
        } else {
            this.setDoll(null);
        }
    }

    @Override
    public CompoundTag write() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt(CURRENT_PROGRESS_TAG, currentProgress);
        nbt.putString(DOLL_TYPE_TAG, doll.getDollType());
        return nbt;
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        ((ServerLevel) Objects.requireNonNull(barrelTile.getLevel()))
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
    public List<Component> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<Component> info = new ArrayList<>();

        info.add(new TranslatableComponent("waila.progress", StringUtils
            .formatPercent((float) currentProgress / (Config.getSecondsToSpawn() * 20))));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate) {
        return stack;
    }
}
