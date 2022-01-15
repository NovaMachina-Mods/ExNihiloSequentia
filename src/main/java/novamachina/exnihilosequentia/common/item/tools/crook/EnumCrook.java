package novamachina.exnihilosequentia.common.item.tools.crook;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumCrook implements IExtensibleEnum {
    WOOD(ExNihiloConstants.Items.CROOK_WOOD, Config.getCrookWoodDurability(), Tiers.WOOD),

    STONE(ExNihiloConstants.Items.CROOK_STONE, Config.getCrookStoneDurability(), Tiers.STONE),
    ANDESITE(ExNihiloConstants.Items.CROOK_ANDESITE, Config.getCrookAndesiteDurability(), Tiers.STONE),
    GRANITE(ExNihiloConstants.Items.CROOK_GRANITE, Config.getCrookGraniteDurability(), Tiers.STONE),
    DIORITE(ExNihiloConstants.Items.CROOK_DIORITE, Config.getCrookDioriteDurability(), Tiers.STONE),

    GOLD(ExNihiloConstants.Items.CROOK_GOLD, Config.getCrookGoldDurability(), Tiers.IRON),
    IRON(ExNihiloConstants.Items.CROOK_IRON, Config.getCrookIronDurability(), Tiers.IRON),
    DIAMOND(ExNihiloConstants.Items.CROOK_DIAMOND, Config.getCrookDiamondDurability(), Tiers.DIAMOND),

    BONE(ExNihiloConstants.Items.CROOK_BONE, Config.getCrookBoneDurability(), Tiers.STONE);

    @Nonnull public final String crookName;
    public final int maxDamage;
    @Nonnull public final Tier tier;
    @Nullable private RegistryObject<Item> registryObject;

    EnumCrook(@Nonnull final String crookName, final int maxDamage, @Nonnull final Tier tier) {
        this.crookName = crookName;
        this.maxDamage = maxDamage;
        this.tier = tier;
    }

    @Nonnull
    public static EnumCrook create(@Nonnull final String enumName, @Nonnull final String crookName, final int maxDamage,
                                   @Nonnull final Tier tier) {
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
