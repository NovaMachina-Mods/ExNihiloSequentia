package novamachina.exnihilosequentia.common.item.tools.crook;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

    @Nonnull public final String crookName;
    public final int maxDamage;
    @Nonnull public final IItemTier tier;
    @Nullable private RegistryObject<Item> registryObject;

    EnumCrook(@Nonnull final String crookName, final int maxDamage, @Nonnull final IItemTier tier) {
        this.crookName = crookName;
        this.maxDamage = maxDamage;
        this.tier = tier;
    }

    @Nonnull
    public static EnumCrook create(@Nonnull final String enumName, @Nonnull final String crookName, final int maxDamage,
                                   @Nonnull final IItemTier tier) {
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
