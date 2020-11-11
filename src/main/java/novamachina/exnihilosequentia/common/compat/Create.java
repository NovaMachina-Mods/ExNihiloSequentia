package novamachina.exnihilosequentia.common.compat;

import novamachina.exnihilosequentia.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class Create implements IOreCompat {
    @Override
    public void activateOres() {
        EnumOre.COPPER.setEnabled();
        EnumOre.ZINC.setEnabled();
    }
}
