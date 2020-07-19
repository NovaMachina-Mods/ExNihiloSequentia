package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BarrelTile extends TileEntity implements ITickableTileEntity {
    private BarrelItemHandler inventory;
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);
    private FluidTank tank;
    private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
    private AbstractBarrelMode mode;

    public BarrelTile() {
        super(ModTiles.BARREL.get());
        this.mode = BarrelModeRegistry.getModeFromName("empty");
        inventory      = new BarrelItemHandler();
        tank           = new FluidTank(1000);
    }

    @Override
    public void tick() {
        if (world.isRemote()) {
            return;
        }

        if (mode != null) {
            mode.tick(inventory, tank);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        compound.putString("barrelMode", mode.getModeName());
        compound.put("modeInfo", mode.write(new CompoundNBT()));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        inventory.deserializeNBT(compound.getCompound("inventory"));
        tank.readFromNBT(compound.getCompound("tank"));
        mode = BarrelModeRegistry.getModeFromName(compound.getString("barrelMode"));
        if (mode != null) {
            mode.read(compound.getCompound("modeInfo"));
        }
        super.read(compound);
    }

    public ActionResultType onBlockActivated() {
        return mode.onBlockActivated(inventory, tank);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryHolder.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return tankHolder.cast();
        }
        return super.getCapability(cap, side);
    }
}
