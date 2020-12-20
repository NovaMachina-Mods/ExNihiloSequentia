package novamachina.exnihilosequentia.common.item.pebbles;

import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum EnumPebbleType {
    STONE(ExNihiloConstants.Items.PEBBLE_STONE),
    GRANITE(ExNihiloConstants.Items.PEBBLE_GRANITE),
    DIORITE(ExNihiloConstants.Items.PEBBLE_DIORITE),
    ANDESITE(ExNihiloConstants.Items.PEBBLE_ANDESITE);

    private final String type;
    private RegistryObject<Item> registryObject;

    EnumPebbleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
