package novamachina.exnihilosequentia.common.registries.defaults;

import novamachina.exnihilosequentia.common.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class Mekanism implements IOreCompat {
    @Override
    public void activateOres() {
        EnumOre.TIN.setEnabled();
        EnumOre.COPPER.setEnabled();
        EnumOre.OSMIUM.setEnabled();
    }
}
