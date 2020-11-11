package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.api.compat.ore.IOreCompat;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class ThermalExpansion implements IOreCompat {
    @Override
    public void activateOres() {
        EnumOre.ALUMINUM.setEnabled();
        EnumOre.COPPER.setEnabled();
        EnumOre.SILVER.setEnabled();
        EnumOre.TIN.setEnabled();
        EnumOre.NICKEL.setEnabled();
        EnumOre.LEAD.setEnabled();
        EnumOre.PLATINUM.setEnabled();
    }
}
