package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;

public class BarrelTile extends TileEntity implements ITickableTileEntity {
    private AbstractBarrelMode mode;

    public BarrelTile() {
        super(ModTiles.BARREL.get());
        this.mode = BarrelModeRegistry.getModeFromName("empty");
    }

    @Override
    public void tick() {
        if(mode != null) {
            mode.tick();
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("barrelMode", mode.getModeName());
        compound.put("modeInfo", mode.write());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        mode = BarrelModeRegistry.getModeFromName(compound.getString("barrelMode"));
        if(mode != null) {
            mode.read(compound);
        }
        super.read(compound);
    }

    public ActionResultType onBlockActivated() {
        return mode.onBlockActivated();
    }
}
