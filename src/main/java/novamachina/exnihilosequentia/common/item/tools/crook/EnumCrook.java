package novamachina.exnihilosequentia.common.item.tools.crook;

import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public enum EnumCrook {
    WOOD(Constants.Items.CROOK_WOOD, 64, ItemTier.WOOD),

    STONE(Constants.Items.CROOK_STONE, 128, ItemTier.STONE),
    ANDESITE(Constants.Items.CROOK_ANDESITE, 128, ItemTier.STONE),
    GRANITE(Constants.Items.CROOK_GRANITE, 128, ItemTier.STONE),
    DIORITE(Constants.Items.CROOK_DIORITE, 128, ItemTier.STONE),

    GOLD(Constants.Items.CROOK_GOLD, 32, ItemTier.IRON),
    IRON(Constants.Items.CROOK_IRON, 256, ItemTier.IRON),
    DIAMOND(Constants.Items.CROOK_DIAMOND, 2048, ItemTier.DIAMOND),

    BONE(Constants.Items.CROOK_BONE, 256, ItemTier.STONE);

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
