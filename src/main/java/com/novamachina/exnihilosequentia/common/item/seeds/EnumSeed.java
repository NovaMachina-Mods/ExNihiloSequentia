package com.novamachina.exnihilosequentia.common.item.seeds;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.PlantType;

public enum EnumSeed {

    SEED_OAK(Constants.Items.SEED_OAK, Blocks.OAK_SAPLING.getDefaultState(), PlantType.Plains),
    SEED_SPRUCE(Constants.Items.SEED_SPRUCE, Blocks.SPRUCE_SAPLING.getDefaultState(), PlantType.Plains),
    SEED_BIRCH(Constants.Items.SEED_BIRCH, Blocks.BIRCH_SAPLING.getDefaultState(), PlantType.Plains),
    SEED_JUNGLE(Constants.Items.SEED_JUNGLE, Blocks.JUNGLE_SAPLING.getDefaultState(), PlantType.Plains),
    SEED_ACACIA(Constants.Items.SEED_ACACIA, Blocks.ACACIA_SAPLING.getDefaultState(), PlantType.Plains),
    SEED_DARK_OAK(Constants.Items.SEED_DARK_OAK, Blocks.DARK_OAK_SAPLING.getDefaultState(), PlantType.Plains),
    SEED_CACTUS(Constants.Items.SEED_CACTUS, Blocks.CACTUS.getDefaultState(), PlantType.Desert),
    SEED_SUGARCANE(Constants.Items.SEED_SUGARCANE, Blocks.SUGAR_CANE.getDefaultState(), PlantType.Beach),
    SEED_CARROT(Constants.Items.SEED_CARROT, Blocks.CARROTS.getDefaultState(), PlantType.Crop),
    SEED_POTATO(Constants.Items.SEED_POTATO, Blocks.POTATOES.getDefaultState(), PlantType.Crop),
    SEED_SWEET_BERRY(Constants.Items.SEED_SWEET_BERRY, Blocks.SWEET_BERRY_BUSH.getDefaultState(), PlantType.Plains),
    SEED_KELP(Constants.Items.SEED_KELP, Blocks.KELP_PLANT.getDefaultState(), PlantType.Water),
    SEED_PICKLE(Constants.Items.SEED_PICKLE, Blocks.SEA_PICKLE.getDefaultState(), PlantType.Water),
    SEED_BAMBOO(Constants.Items.SEED_BAMBOO, Blocks.BAMBOO_SAPLING.getDefaultState(), PlantType.Plains);

    private final String seedName;
    private final BlockState defaultState;
    private final PlantType plantType;

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

    public String getSeedName() {
        return "seed_" + seedName;
    }
}
