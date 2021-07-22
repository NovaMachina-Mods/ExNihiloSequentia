package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TranslatableComponent;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.item.dolls.DollItem;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

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
            if (currentProgress >= Config.getSecondsToSpawn() * 20 && doll.spawnMob(barrelTile.level, barrelTile.blockPosition())) {
                barrelTile.getTank().setFluid(FluidStack.EMPTY);
                barrelTile.setMode(ExNihiloConstants.BarrelModes.EMPTY);
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return ActionResultType.SUCCESS;
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
            setDoll((DollItem) EnumDoll.getDollFromString(nbt.getString(DOLL_TYPE_TAG)).getRegistryObject().get());
        } else {
            this.setDoll(null);
        }
    }

    @Override
    public Tag write() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt(CURRENT_PROGRESS_TAG, currentProgress);
        nbt.putString(DOLL_TYPE_TAG, doll.getDollType());
        return nbt;
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        ((ServerWorld) barrelTile.getLevel())
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
    public List<TranslatableComponent> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<ITextComponent> info = new ArrayList<>();

        info.add(new TranslationTextComponent("waila.progress", StringUtils
            .formatPercent((float) currentProgress / (Config.getSecondsToSpawn() * 20))));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate) {
        return stack;
    }
}
