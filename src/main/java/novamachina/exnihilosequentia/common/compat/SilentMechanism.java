package novamachina.exnihilosequentia.common.compat;

import novamachina.exnihilosequentia.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class SilentMechanism implements IOreCompat {
    @Override
    public void activateOres() {
        EnumOre.URANIUM.setEnabled();
        EnumOre.ZINC.setEnabled();
        EnumOre.TIN.setEnabled();
        EnumOre.NICKEL.setEnabled();
        EnumOre.COPPER.setEnabled();
        EnumOre.ALUMINUM.setEnabled();
        EnumOre.PLATINUM.setEnabled();
        EnumOre.LEAD.setEnabled();
        EnumOre.SILVER.setEnabled();
//        EnumOre.BISMUTH.setEnabled();
    }
}
