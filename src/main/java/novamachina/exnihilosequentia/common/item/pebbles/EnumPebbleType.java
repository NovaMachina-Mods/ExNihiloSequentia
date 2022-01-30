package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumPebbleType implements IExtensibleEnum {
    STONE(ExNihiloConstants.Items.STONE_PEBBLE),
    GRANITE(ExNihiloConstants.Items.GRANITE_PEBBLE),
    DIORITE(ExNihiloConstants.Items.DIORITE_PEBBLE),
    ANDESITE(ExNihiloConstants.Items.ANDESITE_PEBBLE),
    BASALT(ExNihiloConstants.Items.BASALT_PEBBLE),
    BLACKSTONE(ExNihiloConstants.Items.BLACKSTONE_PEBBLE);

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
