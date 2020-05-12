package com.novamachina.exnihilosequentia.common.item.seeds;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.PlantType;

public enum EnumSeed {

    OAK_SEED(Constants.Items.OAK_SEED, Blocks.OAK_SAPLING.getDefaultState(), PlantType.Plains),
    SPRUCE_SEED(Constants.Items.SPRUCE_SEED, Blocks.SPRUCE_SAPLING.getDefaultState(),
        PlantType.Plains),
    BIRCH_SEED(Constants.Items.BIRCH_SEED, Blocks.BIRCH_SAPLING.getDefaultState(),
        PlantType.Plains),
    JUNGLE_SEED(Constants.Items.JUNGLE_SEED, Blocks.JUNGLE_SAPLING.getDefaultState(),
        PlantType.Plains),
    ACACIA_SEED(Constants.Items.ACACIA_SEED, Blocks.ACACIA_SAPLING.getDefaultState(),
        PlantType.Plains),
    DARK_OAK_SEED(Constants.Items.DARK_OAK_SEED, Blocks.DARK_OAK_SAPLING.getDefaultState(),
        PlantType.Plains),
    CACTUS_SEED(Constants.Items.CACTUS_SEED, Blocks.CACTUS.getDefaultState(), PlantType.Desert),
    SUGARCANE_SEED(Constants.Items.SUGARCANE_SEED, Blocks.SUGAR_CANE.getDefaultState(),
        PlantType.Beach),
    CARROT_SEED(Constants.Items.CARROT_SEED, Blocks.CARROTS.getDefaultState(), PlantType.Crop),
    POTATO_SEED(Constants.Items.POTATO_SEED, Blocks.POTATOES.getDefaultState(), PlantType.Crop),
    SWEET_BERRY_SEED(Constants.Items.SWEET_BERRY_SEED, Blocks.SWEET_BERRY_BUSH.getDefaultState(),
        PlantType.Plains);

    private final String     seedName;
    private final BlockState defaultState;
    private final PlantType  plantType;

    EnumSeed(String seedName, BlockState defaultState, PlantType plantType) {
        this.seedName     = seedName;
        this.defaultState = defaultState;
        this.plantType    = plantType;
    }

    public BlockState getDefaultState() {
        return defaultState;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public String getSeedName() {
        return "item_" + seedName + "_seed";
    }
}
