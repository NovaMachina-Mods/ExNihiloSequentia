package com.novamachina.exnihilosequentia.common.tileentity.barrel.transform;

import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelMode;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.BarrelTile;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

public class FluidTransformBarrelMode extends AbstractBarrelMode {
    private int currentProgress;

    public FluidTransformBarrelMode(String name) {
        super(name);
        currentProgress = 0;
    }

    @Override
    public void tick(BarrelTile barrelTile) {

        if (FluidTransformRegistry.isValidRecipe(barrelTile.getTank().getFluid().getFluid(), barrelTile.getWorld()
            .getBlockState(barrelTile.getPos().add(0, -1, 0)).getBlock())) {
            currentProgress++;

            if(currentProgress >= 200) {
                currentProgress = 0;
                barrelTile.getTank().setFluid(new FluidStack(ModFluids.WITCH_WATER_STILL.get(), FluidAttributes.BUCKET_VOLUME));
                barrelTile.setMode(Constants.BarrelModes.FLUID);
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return null;
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
        currentProgress = nbt.getInt("currentProgress");
    }

    @Override
    public CompoundNBT write() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("currentProgress", currentProgress);
        return nbt;
    }
}
