package novamachina.exnihilosequentia.common.item.tools.hammer;

import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public enum EnumHammer {
    WOOD(ExNihiloConstants.Items.HAMMER_WOOD, 64, ItemTier.WOOD),
    STONE(ExNihiloConstants.Items.HAMMER_STONE, 125, ItemTier.STONE),
    IRON(ExNihiloConstants.Items.HAMMER_IRON, 512, ItemTier.IRON),
    DIAMOND(ExNihiloConstants.Items.HAMMER_DIAMOND, 4096, ItemTier.DIAMOND),
    NETHERITE(ExNihiloConstants.Items.HAMMER_NETHERITE, 8192, ItemTier.NETHERITE),
    GOLD(ExNihiloConstants.Items.HAMMER_GOLD, 64, ItemTier.GOLD);

    public final String hammerName;
    public final int defaultDurability;
    public final IItemTier tier;
    private RegistryObject<Item> registryObject;

    EnumHammer(String hammerName, int durability, IItemTier tier) {
        this.hammerName = hammerName;
        this.defaultDurability = durability;
        this.tier = tier;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
