package novamachina.exnihilosequentia.common.item.resources;

import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum EnumResource {
    ANCIENT_SPORE(Items.ANCIENT_SPORE),
    GRASS_SEED(Items.GRASS_SEED),
    SILKWORM(Items.SILKWORM),
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

    public String getResourceName() {
        return resourceName;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
