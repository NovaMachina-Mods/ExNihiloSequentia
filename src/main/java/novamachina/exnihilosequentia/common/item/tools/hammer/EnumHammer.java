package novamachina.exnihilosequentia.common.item.tools.hammer;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumHammer implements IExtensibleEnum {
    WOOD(ExNihiloConstants.Items.HAMMER_WOOD, Config.getHammerWoodDurability(), Tiers.WOOD),
    STONE(ExNihiloConstants.Items.HAMMER_STONE, Config.getHammerStoneDurability(), Tiers.STONE),
    IRON(ExNihiloConstants.Items.HAMMER_IRON, Config.getHammerIronDurability(), Tiers.IRON),
    DIAMOND(ExNihiloConstants.Items.HAMMER_DIAMOND, Config.getHammerDiamondDurability(), Tiers.DIAMOND),
    NETHERITE(ExNihiloConstants.Items.HAMMER_NETHERITE, Config.getHammerNetheriteDurability(), Tiers.NETHERITE),
    GOLD(ExNihiloConstants.Items.HAMMER_GOLD, Config.getHammerGoldDurability(), Tiers.GOLD);

    public final int maxDamage;
    @Nonnull public final String hammerName;
    @Nonnull public final Tier tier;
    @Nullable private RegistryObject<Item> registryObject;

    EnumHammer(@Nonnull final String hammerName, final int maxDamage, @Nonnull final Tier tier) {
        this.hammerName = hammerName;
        this.maxDamage = maxDamage;
        this.tier = tier;
    }

    @Nonnull
    public static EnumHammer create(@Nonnull final String enumName, @Nonnull final String hammerName,
                                    final int maxDamage, @Nonnull final Tier tier) {
        throw new IllegalStateException("Enum not extended");
    }

    @Nullable
    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(@Nonnull final RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
