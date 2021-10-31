package novamachina.exnihilosequentia.common.item.resources;

import net.minecraft.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;

public enum EnumResource implements IExtensibleEnum {
    ANCIENT_SPORE(Items.ANCIENT_SPORE),
    GRASS_SEED(Items.GRASS_SEED),
    PORCELAIN_CLAY(Items.PORCELAIN_CLAY),
    CRAFTING_DOLL(Items.CRAFTING_DOLL),
    BLUE_CORAL_SEED(Items.BLUE_CORAL_SEED),
    PINK_CORAL_SEED(Items.PINK_CORAL_SEED),
    PURPLE_CORAL_SEED(Items.PURPLE_CORAL_SEED),
    RED_CORAL_SEED(Items.RED_CORAL_SEED),
    YELLOW_CORAL_SEED(Items.YELLOW_CORAL_SEED),
    BEEHIVE_FRAME(Items.BEEHIVE_FRAME);

    private final String resourceName;
    private RegistryObject<Item> registryObject;

    EnumResource(String resourceName) {
        this.resourceName = resourceName;
    }

    public static EnumResource create(String enumName, String resourceName) {
        throw new IllegalStateException("Enum not extended");
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    public String getResourceName() {
        return resourceName;
    }
}
