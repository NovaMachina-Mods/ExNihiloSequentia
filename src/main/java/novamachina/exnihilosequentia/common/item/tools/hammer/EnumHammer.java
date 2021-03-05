package novamachina.exnihilosequentia.common.item.tools.hammer;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public enum EnumHammer implements IExtensibleEnum {
    WOOD(ExNihiloConstants.Items.HAMMER_WOOD, Config.getHammerWoodDurability(), ItemTier.WOOD),
    STONE(ExNihiloConstants.Items.HAMMER_STONE, Config.getHammerStoneDurability(), ItemTier.STONE),
    IRON(ExNihiloConstants.Items.HAMMER_IRON, Config.getHammerIronDurability(), ItemTier.IRON),
    DIAMOND(ExNihiloConstants.Items.HAMMER_DIAMOND, Config.getHammerDiamondDurability(), ItemTier.DIAMOND),
    NETHERITE(ExNihiloConstants.Items.HAMMER_NETHERITE, Config.getHammerNetheriteDurability(), ItemTier.NETHERITE),
    GOLD(ExNihiloConstants.Items.HAMMER_GOLD, Config.getHammerGoldDurability(), ItemTier.GOLD);

    public int maxDamage;
    public final String hammerName;
    public final IItemTier tier;
    private RegistryObject<Item> registryObject;

    EnumHammer(String hammerName, int maxDamage, IItemTier tier) {
        this.hammerName = hammerName;
        this.maxDamage = maxDamage;
        this.tier = tier;
    }

    public static EnumHammer create(String enumName, String hammerName, int maxDamage, IItemTier tier) {
        throw new IllegalStateException("Enum not extended");
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
