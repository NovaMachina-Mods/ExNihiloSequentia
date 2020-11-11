package novamachina.exnihilosequentia.common.compat;

import novamachina.exnihilosequentia.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class ImmersiveEngineering implements IOreCompat {
    @Override
    public void activateOres() {
        EnumOre.ALUMINUM.setEnabled();
        EnumOre.COPPER.setEnabled();
        EnumOre.SILVER.setEnabled();
        EnumOre.NICKEL.setEnabled();
        EnumOre.LEAD.setEnabled();
        EnumOre.URANIUM.setEnabled();
    }
}
