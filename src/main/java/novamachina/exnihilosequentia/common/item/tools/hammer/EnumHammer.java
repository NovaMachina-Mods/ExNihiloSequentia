package novamachina.exnihilosequentia.common.item.tools.hammer;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fmllegacy.RegistryObject;
import novamachina.exnihilosequentia.api.utility.Config;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;

public enum EnumHammer implements IExtensibleEnum {
    WOOD(ExNihiloConstants.Items.WOOD_HAMMER, Config.getHammerWoodDurability(), Tiers.WOOD),
    STONE(ExNihiloConstants.Items.STONE_HAMMER, Config.getHammerStoneDurability(), Tiers.STONE),
    IRON(ExNihiloConstants.Items.IRON_HAMMER, Config.getHammerIronDurability(), Tiers.IRON),
    DIAMOND(ExNihiloConstants.Items.DIAMOND_HAMMER, Config.getHammerDiamondDurability(), Tiers.DIAMOND),
    NETHERITE(ExNihiloConstants.Items.NETHERITE_HAMMER, Config.getHammerNetheriteDurability(), Tiers.NETHERITE),
    GOLD(ExNihiloConstants.Items.GOLD_HAMMER, Config.getHammerGoldDurability(), Tiers.GOLD);

    public final int maxDamage;
    public final String hammerName;
    public final Tier tier;
    private RegistryObject<Item> registryObject;

    EnumHammer(String hammerName, int maxDamage, Tier tier) {
        this.hammerName = hammerName;
        this.maxDamage = maxDamage;
        this.tier = tier;
    }

    public static EnumHammer create(String enumName, String hammerName, int maxDamage, Tier tier) {
        throw new IllegalStateException("Enum not extended");
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
