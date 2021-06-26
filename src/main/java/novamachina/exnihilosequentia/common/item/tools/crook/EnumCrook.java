package novamachina.exnihilosequentia.common.item.tools.crook;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public enum EnumCrook implements IExtensibleEnum {
    WOOD(ExNihiloConstants.Items.WOOD_CROOK, Config.getCrookWoodDurability(), ItemTier.WOOD),

    STONE(ExNihiloConstants.Items.STONE_CROOK, Config.getCrookStoneDurability(), ItemTier.STONE),
    ANDESITE(ExNihiloConstants.Items.ANDESITE_CROOK, Config.getCrookAndesiteDurability(), ItemTier.STONE),
    GRANITE(ExNihiloConstants.Items.GRANITE_CROOK, Config.getCrookGraniteDurability(), ItemTier.STONE),
    DIORITE(ExNihiloConstants.Items.DIORITE_CROOK, Config.getCrookDioriteDurability(), ItemTier.STONE),

    GOLD(ExNihiloConstants.Items.GOLD_CROOK, Config.getCrookGoldDurability(), ItemTier.IRON),
    IRON(ExNihiloConstants.Items.IRON_CROOK, Config.getCrookIronDurability(), ItemTier.IRON),
    DIAMOND(ExNihiloConstants.Items.DIAMOND_CROOK, Config.getCrookDiamondDurability(), ItemTier.DIAMOND),

    BONE(ExNihiloConstants.Items.BONE_CROOK, Config.getCrookBoneDurability(), ItemTier.STONE);

    public final String crookName;
    public int maxDamage;
    public final IItemTier tier;
    private RegistryObject<Item> registryObject;

    EnumCrook(String crookName, int maxDamage, IItemTier tier) {
        this.crookName = crookName;
        this.maxDamage = maxDamage;
        this.tier = tier;
    }

    public static EnumCrook create(String enumName, String crookName, int maxDamage, IItemTier tier) {
        throw new IllegalStateException("Enum not extended");
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
