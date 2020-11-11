package novamachina.exnihilosequentia.common.registries.defaults;

import novamachina.exnihilosequentia.common.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class Create implements IOreCompat {
    @Override
    public void activateOres() {
        EnumOre.COPPER.setEnabled();
        EnumOre.ZINC.setEnabled();
    }
}
