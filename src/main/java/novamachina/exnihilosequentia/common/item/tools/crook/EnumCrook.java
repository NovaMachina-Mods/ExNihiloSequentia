package novamachina.exnihilosequentia.common.item.tools.crook;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public enum EnumCrook implements IExtensibleEnum {
    WOOD(ExNihiloConstants.Items.CROOK_WOOD, Config.getCrookWoodDurability(), ItemTier.WOOD),

    STONE(ExNihiloConstants.Items.CROOK_STONE, Config.getCrookStoneDurability(), ItemTier.STONE),
    ANDESITE(ExNihiloConstants.Items.CROOK_ANDESITE, Config.getCrookAndesiteDurability(), ItemTier.STONE),
    GRANITE(ExNihiloConstants.Items.CROOK_GRANITE, Config.getCrookGraniteDurability(), ItemTier.STONE),
    DIORITE(ExNihiloConstants.Items.CROOK_DIORITE, Config.getCrookDioriteDurability(), ItemTier.STONE),

    GOLD(ExNihiloConstants.Items.CROOK_GOLD, Config.getCrookGoldDurability(), ItemTier.IRON),
    IRON(ExNihiloConstants.Items.CROOK_IRON, Config.getCrookIronDurability(), ItemTier.IRON),
    DIAMOND(ExNihiloConstants.Items.CROOK_DIAMOND, Config.getCrookDiamondDurability(), ItemTier.DIAMOND),

    BONE(ExNihiloConstants.Items.CROOK_BONE, Config.getCrookBoneDurability(), ItemTier.STONE);

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
