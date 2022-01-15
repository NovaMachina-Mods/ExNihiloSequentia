package novamachina.exnihilosequentia.common.item.seeds;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumSeed implements IExtensibleEnum {

    SEED_OAK(ExNihiloConstants.Items.SEED_OAK, Blocks.OAK_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_SPRUCE(ExNihiloConstants.Items.SEED_SPRUCE, Blocks.SPRUCE_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_BIRCH(ExNihiloConstants.Items.SEED_BIRCH, Blocks.BIRCH_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_JUNGLE(ExNihiloConstants.Items.SEED_JUNGLE, Blocks.JUNGLE_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_ACACIA(ExNihiloConstants.Items.SEED_ACACIA, Blocks.ACACIA_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_DARK_OAK(ExNihiloConstants.Items.SEED_DARK_OAK, Blocks.DARK_OAK_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_CACTUS(ExNihiloConstants.Items.SEED_CACTUS, Blocks.CACTUS.defaultBlockState(), PlantType.DESERT),
    SEED_SUGARCANE(ExNihiloConstants.Items.SEED_SUGARCANE, Blocks.SUGAR_CANE.defaultBlockState(), PlantType.BEACH),
    SEED_CARROT(ExNihiloConstants.Items.SEED_CARROT, Blocks.CARROTS.defaultBlockState(), PlantType.CROP),
    SEED_POTATO(ExNihiloConstants.Items.SEED_POTATO, Blocks.POTATOES.defaultBlockState(), PlantType.CROP),
    SEED_SWEET_BERRY(ExNihiloConstants.Items.SEED_SWEET_BERRY, Blocks.SWEET_BERRY_BUSH.defaultBlockState(), PlantType.PLAINS),
    SEED_KELP(ExNihiloConstants.Items.SEED_KELP, Blocks.KELP_PLANT.defaultBlockState(), PlantType.WATER),
    SEED_PICKLE(ExNihiloConstants.Items.SEED_PICKLE, Blocks.SEA_PICKLE.defaultBlockState(), PlantType.WATER),
    SEED_BAMBOO(ExNihiloConstants.Items.SEED_BAMBOO, Blocks.BAMBOO_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_FERN(ExNihiloConstants.Items.SEED_FERN, Blocks.FERN.defaultBlockState(), PlantType.PLAINS),
    SEED_LARGE_FERN(ExNihiloConstants.Items.SEED_LARGE_FERN, Blocks.LARGE_FERN.defaultBlockState(), PlantType.PLAINS);

    @Nonnull private final BlockState defaultState;
    @Nonnull private final PlantType plantType;
    @Nonnull private final String seedName;
    @Nullable private RegistryObject<Item> registryObject;

    EnumSeed(@Nonnull final String seedName, @Nonnull final BlockState defaultState,
             @Nonnull final PlantType plantType) {
        this.seedName = seedName;
        this.defaultState = defaultState;
        this.plantType = plantType;
    }

    @Nonnull
    public static EnumSeed create(@Nonnull final String enumName, @Nonnull final String seedName,
                                  @Nonnull final BlockState defaultState, @Nonnull final PlantType plantType) {
        throw new IllegalStateException("Enum not extended");
    }

    @Nonnull
    public BlockState getDefaultState() {
        return defaultState;
    }

    @Nonnull
    public PlantType getPlantType() {
        return plantType;
    }

    @Nullable
    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(@Nonnull final RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    @Nonnull
    public String getSeedName() {
        return "seed_" + seedName;
    }
}
