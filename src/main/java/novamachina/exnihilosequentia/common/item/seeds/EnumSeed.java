package novamachina.exnihilosequentia.common.item.seeds;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public enum EnumSeed implements IExtensibleEnum {

    SEED_OAK(ExNihiloConstants.Items.OAK_SEED, Blocks.OAK_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_SPRUCE(ExNihiloConstants.Items.SPRUCE_SEED, Blocks.SPRUCE_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_BIRCH(ExNihiloConstants.Items.BIRCH_SEED, Blocks.BIRCH_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_JUNGLE(ExNihiloConstants.Items.JUNGLE_SEED, Blocks.JUNGLE_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_ACACIA(ExNihiloConstants.Items.ACACIA_SEED, Blocks.ACACIA_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_DARK_OAK(ExNihiloConstants.Items.DARK_OAK_SEED, Blocks.DARK_OAK_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_CACTUS(ExNihiloConstants.Items.CACTUS_SEED, Blocks.CACTUS.defaultBlockState(), PlantType.DESERT),
    SEED_SUGARCANE(ExNihiloConstants.Items.SUGARCANE_SEED, Blocks.SUGAR_CANE.defaultBlockState(), PlantType.BEACH),
    SEED_CARROT(ExNihiloConstants.Items.CARROT_SEED, Blocks.CARROTS.defaultBlockState(), PlantType.CROP),
    SEED_POTATO(ExNihiloConstants.Items.POTATO_SEED, Blocks.POTATOES.defaultBlockState(), PlantType.CROP),
    SEED_SWEET_BERRY(ExNihiloConstants.Items.SWEET_BERRY_SEED, Blocks.SWEET_BERRY_BUSH.defaultBlockState(), PlantType.PLAINS),
    SEED_KELP(ExNihiloConstants.Items.KELP_SEED, Blocks.KELP_PLANT.defaultBlockState(), PlantType.WATER),
    SEED_PICKLE(ExNihiloConstants.Items.PICKLE_SEED, Blocks.SEA_PICKLE.defaultBlockState(), PlantType.WATER),
    SEED_BAMBOO(ExNihiloConstants.Items.BAMBOO_SEED, Blocks.BAMBOO_SAPLING.defaultBlockState(), PlantType.PLAINS),
    SEED_FERN(ExNihiloConstants.Items.FERN_SEED, Blocks.FERN.defaultBlockState(), PlantType.PLAINS),
    SEED_LARGE_FERN(ExNihiloConstants.Items.LARGE_FERN_SEED, Blocks.LARGE_FERN.defaultBlockState(), PlantType.PLAINS);

    private final BlockState defaultState;
    private final PlantType plantType;
    private final String seedName;
    private RegistryObject<Item> registryObject;

    EnumSeed(String seedName, BlockState defaultState, PlantType plantType) {
        this.seedName = seedName;
        this.defaultState = defaultState;
        this.plantType = plantType;
    }

    public static EnumSeed create(String enumName, String seedName, BlockState defaultState, PlantType plantType) {
        throw new IllegalStateException("Enum not extended");
    }

    public BlockState getDefaultState() {
        return defaultState;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    public String getSeedName() {
        return seedName + "_seed";
    }
}
