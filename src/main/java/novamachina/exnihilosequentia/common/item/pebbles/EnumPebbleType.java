package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fmllegacy.RegistryObject;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;

public enum EnumPebbleType implements IExtensibleEnum {
    STONE(ExNihiloConstants.Items.STONE_PEBBLE),
    GRANITE(ExNihiloConstants.Items.GRANITE_PEBBLE),
    DIORITE(ExNihiloConstants.Items.DIORITE_PEBBLE),
    ANDESITE(ExNihiloConstants.Items.ANDESITE_PEBBLE),
    BASALT(ExNihiloConstants.Items.BASALT_PEBBLE),
    BLACKSTONE(ExNihiloConstants.Items.BLACKSTONE_PEBBLE);

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
