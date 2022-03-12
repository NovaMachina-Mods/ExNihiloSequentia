package novamachina.exnihilosequentia.common.item.resources;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumResource implements IExtensibleEnum {
    ANCIENT_SPORES(Items.ANCIENT_SPORES),
    GRASS_SEEDS(Items.GRASS_SEEDS),
    PORCELAIN_CLAY(Items.PORCELAIN_CLAY),
    CRAFTING_DOLL(Items.CRAFTING_DOLL),
    BLUE_CORAL_SEED(Items.BLUE_CORAL_SEED),
    PINK_CORAL_SEED(Items.PINK_CORAL_SEED),
    PURPLE_CORAL_SEED(Items.PURPLE_CORAL_SEED),
    RED_CORAL_SEED(Items.RED_CORAL_SEED),
    YELLOW_CORAL_SEED(Items.YELLOW_CORAL_SEED),
    BEEHIVE_FRAME(Items.BEEHIVE_FRAME);

    @Nonnull private final String resourceName;
    @Nullable private RegistryObject<Item> registryObject;

    EnumResource(@Nonnull final String resourceName) {
        this.resourceName = resourceName;
    }

    @Nonnull
    public static EnumResource create(@Nonnull final String enumName, @Nonnull final String resourceName) {
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
    public String getResourceName() {
        return resourceName;
    }
}
