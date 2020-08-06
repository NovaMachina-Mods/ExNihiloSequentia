package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelMode;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.BarrelTile;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class CompostBarrelMode extends AbstractBarrelMode {
    private int currentProgress;

    public CompostBarrelMode(String name) {
        super(name);
        currentProgress = 0;
    }

    @Override
    public void tick(BarrelTile barrelTile) {
        if (barrelTile.getSolidAmount() >= BarrelTile.MAX_SOLID_AMOUNT && barrelTile.getInventory().getStackInSlot(0).isEmpty()) {
            currentProgress++;
            spawnParticle(barrelTile);
            if (currentProgress >= Config.SECONDS_TO_COMPOST.get() * 20) {
                currentProgress = 0;
                barrelTile.getInventory().setStackInSlot(0, new ItemStack(ForgeRegistries.BLOCKS.getValue(Blocks.DIRT.getRegistryName())));
                barrelTile.removeSolid(barrelTile.getSolidAmount());
                barrelTile.setMode(Constants.BarrelModes.BLOCK);
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if (ModRegistries.COMPOST.containsSolid(player.getHeldItem(handIn).getItem())) {
            if(barrelTile.addSolid(ModRegistries.COMPOST
                .getSolidAmount(player.getHeldItem(handIn).getItem()))) {
                player.getHeldItem(handIn).shrink(1);
            }
        }

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
        return ModRegistries.COMPOST.containsSolid(stack.getItem());
    }

    @Override
    public void read(CompoundNBT nbt) {
        this.currentProgress = nbt.getInt("currentProgress");
    }

    @Override
    public CompoundNBT write() {
        CompoundNBT modeInfo = new CompoundNBT();
        modeInfo.putInt("currentProgress", currentProgress);
        return modeInfo;
    }

    @Override
    protected void spawnParticle(BarrelTile barrelTile) {
        ((ServerWorld) barrelTile.getWorld())
            .spawnParticle(ParticleTypes.EFFECT,
                barrelTile.getPos().getX() + barrelTile.getWorld().rand.nextDouble(),
                barrelTile.getPos().getY() + barrelTile.getWorld().rand.nextDouble(),
                barrelTile.getPos().getZ() + barrelTile.getWorld().rand.nextDouble(),
                1,
                0.0,
                0.0,
                0.0,
                0.05);
    }
}
