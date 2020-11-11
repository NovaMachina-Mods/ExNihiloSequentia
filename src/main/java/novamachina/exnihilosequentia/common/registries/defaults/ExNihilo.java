package novamachina.exnihilosequentia.common.registries.defaults;

import novamachina.exnihilosequentia.common.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public class ExNihilo implements IOreCompat {
    @Override
    public void activateOres() {
        EnumOre.IRON.setEnabled();
        EnumOre.GOLD.setEnabled();
    }
}
