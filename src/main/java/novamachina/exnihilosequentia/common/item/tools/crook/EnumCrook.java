package novamachina.exnihilosequentia.common.item.tools.crook;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fmllegacy.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public enum EnumCrook implements IExtensibleEnum {
    WOOD(ExNihiloConstants.Items.WOOD_CROOK, Config.getCrookWoodDurability(), Tiers.WOOD),

    STONE(ExNihiloConstants.Items.STONE_CROOK, Config.getCrookStoneDurability(), Tiers.STONE),
    ANDESITE(ExNihiloConstants.Items.ANDESITE_CROOK, Config.getCrookAndesiteDurability(), Tiers.STONE),
    GRANITE(ExNihiloConstants.Items.GRANITE_CROOK, Config.getCrookGraniteDurability(), Tiers.STONE),
    DIORITE(ExNihiloConstants.Items.DIORITE_CROOK, Config.getCrookDioriteDurability(), Tiers.STONE),

    GOLD(ExNihiloConstants.Items.GOLD_CROOK, Config.getCrookGoldDurability(), Tiers.IRON),
    IRON(ExNihiloConstants.Items.IRON_CROOK, Config.getCrookIronDurability(), Tiers.IRON),
    DIAMOND(ExNihiloConstants.Items.DIAMOND_CROOK, Config.getCrookDiamondDurability(), Tiers.DIAMOND),

    BONE(ExNihiloConstants.Items.BONE_CROOK, Config.getCrookBoneDurability(), Tiers.STONE);

    public final String crookName;
    public final int maxDamage;
    public final Tier tier;
    private RegistryObject<Item> registryObject;

    EnumCrook(String crookName, int maxDamage, Tier tier) {
        this.crookName = crookName;
        this.maxDamage = maxDamage;
        this.tier = tier;
    }

    public static EnumCrook create(String enumName, String crookName, int maxDamage, Tier tier) {
        throw new IllegalStateException("Enum not extended");
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
