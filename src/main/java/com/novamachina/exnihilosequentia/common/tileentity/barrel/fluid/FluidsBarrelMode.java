package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import com.novamachina.exnihilosequentia.common.item.dolls.DollItem;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelMode;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.BarrelTile;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.MobSpawnBarrelMode;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

public class FluidsBarrelMode extends AbstractBarrelMode {
    public FluidsBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelTile barrelTile) {
        if (barrelTile.getFluidAmount() >= FluidAttributes.BUCKET_VOLUME) {
            if (fluidOnTop(barrelTile)) {
                return;
            }
            if (fluidTransform(barrelTile)) {
                return;
            }
        }
    }

    private boolean doMobSpawn(BarrelTile barrelTile, PlayerEntity player, Hand handIn) {
        Item item = player.getHeldItem(handIn).getItem();

        if(item instanceof DollItem) {
            DollItem doll = (DollItem) item;
            if(barrelTile.getFluid().isEquivalentTo(doll.getSpawnFluid())) {
                barrelTile.setMode(Constants.BarrelModes.MOB);
                ((MobSpawnBarrelMode)barrelTile.getMode()).setDoll(doll);
                player.getHeldItem(handIn).shrink(1);
                return true;
            }
        }

        return false;
    }

    private boolean fluidTransform(BarrelTile barrelTile) {
        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();
        Block blockBelow = barrelTile.getWorld().getBlockState(barrelTile.getPos().add(0, -1, 0)).getBlock();

        if (FluidTransformRegistry.isValidRecipe(fluidInTank, blockBelow)) {
            barrelTile.setMode(Constants.BarrelModes.TRANSFORM);
            return true;
        }
        return false;
    }

    private boolean fluidOnTop(BarrelTile barrelTile) {
        Fluid fluidOnTop = barrelTile.getWorld().getFluidState(barrelTile.getPos().add(0, 1, 0)).getFluid();
        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

        if (FluidOnTopRegistry.isValidRecipe(fluidInTank, fluidOnTop)) {
            barrelTile.getTank().setFluid(FluidStack.EMPTY);
            barrelTile.getInventory().setStackInSlot(0, new ItemStack(FluidOnTopRegistry.getResult(fluidInTank, fluidOnTop)));
            barrelTile.setMode(Constants.BarrelModes.BLOCK);
            return true;
        }
        return false;
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        ItemStack stack = player.getHeldItem(handIn);
        if (stack.isEmpty()) {
            return ActionResultType.SUCCESS;
        }

        boolean result = FluidUtil.interactWithFluidHandler(player, handIn, fluidHandler);

        if (result) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            barrelTile.getWorld()
                .notifyBlockUpdate(barrelTile.getPos(), barrelTile.getBlockState(), barrelTile.getBlockState(), 2);
            barrelTile.markDirty();
            return ActionResultType.SUCCESS;
        }

        if(fluidBlockTransform(barrelTile, player, handIn)) {
            return ActionResultType.SUCCESS;
        }

        doMobSpawn(barrelTile, player, handIn);

        return ActionResultType.SUCCESS;
    }

    private boolean fluidBlockTransform(BarrelTile barrelTile, PlayerEntity player, Hand handIn) {
        Fluid fluid = barrelTile.getTank().getFluid().getFluid();
        Item input = player.getHeldItem(handIn).getItem();
        if (FluidBlockTransformRegistry.isValidRecipe(fluid, input)) {
            barrelTile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
            barrelTile.getInventory()
                .setStackInSlot(0, new ItemStack(FluidBlockTransformRegistry.getResult(fluid, input)));
            player.getHeldItem(handIn).shrink(1);
            barrelTile.setMode(Constants.BarrelModes.BLOCK);
            return true;
        }
        return false;
    }

    @Override
    public boolean canFillWithFluid(BarrelTile barrel) {
        return true;
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

    }

    @Override
    public CompoundNBT write() {
        return new CompoundNBT();
    }

    @Override
    protected void spawnParticle(BarrelTile barrelTile) {

    }
}
