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
    WOOD(ExNihiloConstants.Items.WOODEN_CROOK, Config.getCrookWoodDurability(), Tiers.WOOD),

    STONE(ExNihiloConstants.Items.STONE_CROOK, Config.getCrookStoneDurability(), Tiers.STONE),
    ANDESITE(ExNihiloConstants.Items.ANDESITE_CROOK, Config.getCrookAndesiteDurability(), Tiers.STONE),
    GRANITE(ExNihiloConstants.Items.GRANITE_CROOK, Config.getCrookGraniteDurability(), Tiers.STONE),
    DIORITE(ExNihiloConstants.Items.DIORITE_CROOK, Config.getCrookDioriteDurability(), Tiers.STONE),

    GOLD(ExNihiloConstants.Items.GOLD_CROOK, Config.getCrookGoldDurability(), Tiers.IRON),
    IRON(ExNihiloConstants.Items.IRON_CROOK, Config.getCrookIronDurability(), Tiers.IRON),
    DIAMOND(ExNihiloConstants.Items.DIAMOND_CROOK, Config.getCrookDiamondDurability(), Tiers.DIAMOND),

    BONE(ExNihiloConstants.Items.BONE_CROOK, Config.getCrookBoneDurability(), Tiers.STONE);

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
