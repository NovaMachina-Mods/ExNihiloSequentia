package novamachina.exnihilosequentia.common.item.tools.crook;

import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public enum EnumCrook {
    WOOD(ExNihiloConstants.Items.CROOK_WOOD, 64, ItemTier.WOOD),

    STONE(ExNihiloConstants.Items.CROOK_STONE, 128, ItemTier.STONE),
    ANDESITE(ExNihiloConstants.Items.CROOK_ANDESITE, 128, ItemTier.STONE),
    GRANITE(ExNihiloConstants.Items.CROOK_GRANITE, 128, ItemTier.STONE),
    DIORITE(ExNihiloConstants.Items.CROOK_DIORITE, 128, ItemTier.STONE),

    GOLD(ExNihiloConstants.Items.CROOK_GOLD, 32, ItemTier.IRON),
    IRON(ExNihiloConstants.Items.CROOK_IRON, 256, ItemTier.IRON),
    DIAMOND(ExNihiloConstants.Items.CROOK_DIAMOND, 2048, ItemTier.DIAMOND),

    BONE(ExNihiloConstants.Items.CROOK_BONE, 256, ItemTier.STONE);

    public final String crookName;
    public final int defaultDurability;
    public final IItemTier tier;
    private RegistryObject<Item> registryObject;

    EnumCrook(String crookName, int durability, IItemTier tier) {
        this.crookName = crookName;
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
