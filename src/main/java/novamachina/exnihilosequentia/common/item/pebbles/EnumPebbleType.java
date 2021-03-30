package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;

public enum EnumPebbleType implements IExtensibleEnum {
    STONE(Items.PEBBLE_STONE),
    GRANITE(Items.PEBBLE_GRANITE),
    DIORITE(Items.PEBBLE_DIORITE),
    ANDESITE(Items.PEBBLE_ANDESITE),
    BLACKSTONE(Items.PEBBLE_BLACKSTONE),
    BASALT(Items.PEBBLE_BASALT);

    private final String type;
    private RegistryObject<Item> registryObject;

    EnumPebbleType(String type) {
        this.type = type;
    }

    public static EnumPebbleType create(String enumName, String type) {
        throw new IllegalStateException("Enum not extended");
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    public String getType() {
        return type;
    }
}
