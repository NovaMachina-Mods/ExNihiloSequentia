package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumPebbleType implements IExtensibleEnum {
    STONE(ExNihiloConstants.Items.PEBBLE_STONE),
    GRANITE(ExNihiloConstants.Items.PEBBLE_GRANITE),
    DIORITE(ExNihiloConstants.Items.PEBBLE_DIORITE),
    ANDESITE(ExNihiloConstants.Items.PEBBLE_ANDESITE),
    BASALT(ExNihiloConstants.Items.PEBBLE_BASALT),
    BLACKSTONE(ExNihiloConstants.Items.PEBBLE_BLACKSTONE);

    @Nonnull private final String type;
    @Nullable private RegistryObject<Item> registryObject;

    EnumPebbleType(@Nonnull final String type) {
        this.type = type;
    }

    public static EnumPebbleType create(String enumName, String type) {
        throw new IllegalStateException("Enum not extended");
    }

    @Nullable
    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(@Nonnull final RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    @Nonnull
    public String getType() {
        return type;
    }
}
