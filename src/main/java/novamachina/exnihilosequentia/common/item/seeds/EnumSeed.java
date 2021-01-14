package novamachina.exnihilosequentia.common.item.seeds;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public enum EnumSeed {

    SEED_OAK(ExNihiloConstants.Items.SEED_OAK, Blocks.OAK_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_SPRUCE(ExNihiloConstants.Items.SEED_SPRUCE, Blocks.SPRUCE_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_BIRCH(ExNihiloConstants.Items.SEED_BIRCH, Blocks.BIRCH_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_JUNGLE(ExNihiloConstants.Items.SEED_JUNGLE, Blocks.JUNGLE_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_ACACIA(ExNihiloConstants.Items.SEED_ACACIA, Blocks.ACACIA_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_DARK_OAK(ExNihiloConstants.Items.SEED_DARK_OAK, Blocks.DARK_OAK_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_CACTUS(ExNihiloConstants.Items.SEED_CACTUS, Blocks.CACTUS.getDefaultState(), PlantType.DESERT),
    SEED_SUGARCANE(ExNihiloConstants.Items.SEED_SUGARCANE, Blocks.SUGAR_CANE.getDefaultState(), PlantType.BEACH),
    SEED_CARROT(ExNihiloConstants.Items.SEED_CARROT, Blocks.CARROTS.getDefaultState(), PlantType.CROP),
    SEED_POTATO(ExNihiloConstants.Items.SEED_POTATO, Blocks.POTATOES.getDefaultState(), PlantType.CROP),
    SEED_SWEET_BERRY(ExNihiloConstants.Items.SEED_SWEET_BERRY, Blocks.SWEET_BERRY_BUSH.getDefaultState(), PlantType.PLAINS),
    SEED_KELP(ExNihiloConstants.Items.SEED_KELP, Blocks.KELP_PLANT.getDefaultState(), PlantType.WATER),
    SEED_PICKLE(ExNihiloConstants.Items.SEED_PICKLE, Blocks.SEA_PICKLE.getDefaultState(), PlantType.WATER),
    SEED_BAMBOO(ExNihiloConstants.Items.SEED_BAMBOO, Blocks.BAMBOO_SAPLING.getDefaultState(), PlantType.PLAINS);

    private final BlockState defaultState;
    private final PlantType plantType;
    private final String seedName;
    private RegistryObject<Item> registryObject;

    EnumSeed(String seedName, BlockState defaultState, PlantType plantType) {
        this.seedName = seedName;
        this.defaultState = defaultState;
        this.plantType = plantType;
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
        return "seed_" + seedName;
    }
}
