package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import com.novamachina.exnihilosequentia.common.item.dolls.DollItem;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.StringUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class MobSpawnBarrelMode extends AbstractBarrelMode {
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
    public void tick(BarrelTile barrelTile) {
        if (doll != null) {
            currentProgress++;
            spawnParticle(barrelTile);
            if (currentProgress >= Config.SECONDS_TO_SPAWN.get() * 20) {
                if (doll.spawnMob(barrelTile.getWorld(), barrelTile.getPos())) {
                    barrelTile.getTank().setFluid(FluidStack.EMPTY);
                    barrelTile.setMode(Constants.BarrelModes.EMPTY);
                }
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canFillWithFluid(BarrelTile barrel) {
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
    public void read(CompoundNBT nbt) {
        if (nbt.contains("currentProgress")) {
            this.currentProgress = nbt.getInt("currentProgress");
        } else {
            this.currentProgress = 0;
        }
        if (nbt.contains("dollType")) {
            setDoll((DollItem) ModItems.dollMap.get(nbt.getString("dollType")).get());
        } else {
            this.setDoll(null);
        }
    }

    @Override
    public CompoundNBT write() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("currentProgress", currentProgress);
        nbt.putString("dollType", doll.getDollType());
        return nbt;
    }

    @Override
    protected void spawnParticle(BarrelTile barrelTile) {
        ((ServerWorld) barrelTile.getWorld())
            .spawnParticle(ParticleTypes.LARGE_SMOKE,
                barrelTile.getPos().getX() + barrelTile.getWorld().rand.nextDouble(),
                barrelTile.getPos().getY() + barrelTile.getWorld().rand.nextDouble(),
                barrelTile.getPos().getZ() + barrelTile.getWorld().rand.nextDouble(),
                5,
                0.0,
                0.0,
                0.0,
                0.05);
    }

    @Override
    public List<ITextComponent> getWailaInfo(BarrelTile barrelTile) {
        List<ITextComponent> info = new ArrayList<>();

        info.add(new TranslationTextComponent("waila.progress", StringUtils.formatPercent((float)currentProgress / (Config.SECONDS_TO_SPAWN.get() * 20))));

        return info;
    }
}
