package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import com.novamachina.exnihilosequentia.common.init.ModTiles;
import com.novamachina.exnihilosequentia.common.utility.Config;
import net.minecraftforge.fluids.FluidStack;

public class WoodBarrelTile extends AbstractBarrelTile {
    public WoodBarrelTile() {
        super(ModTiles.WOOD_BARREL.get());
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack resource) {
        return resource.getFluid().getAttributes().getTemperature() <= Config.WOOD_BARREL_MAX_TEMP.get();
    }
}
