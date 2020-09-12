package com.novamachina.exnihilosequentia.common.item.seeds;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.RegistryObject;

public enum EnumSeed {

    SEED_OAK(Constants.Items.SEED_OAK, Blocks.OAK_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_SPRUCE(Constants.Items.SEED_SPRUCE, Blocks.SPRUCE_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_BIRCH(Constants.Items.SEED_BIRCH, Blocks.BIRCH_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_JUNGLE(Constants.Items.SEED_JUNGLE, Blocks.JUNGLE_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_ACACIA(Constants.Items.SEED_ACACIA, Blocks.ACACIA_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_DARK_OAK(Constants.Items.SEED_DARK_OAK, Blocks.DARK_OAK_SAPLING.getDefaultState(), PlantType.PLAINS),
    SEED_CACTUS(Constants.Items.SEED_CACTUS, Blocks.CACTUS.getDefaultState(), PlantType.DESERT),
    SEED_SUGARCANE(Constants.Items.SEED_SUGARCANE, Blocks.SUGAR_CANE.getDefaultState(), PlantType.BEACH),
    SEED_CARROT(Constants.Items.SEED_CARROT, Blocks.CARROTS.getDefaultState(), PlantType.CROP),
    SEED_POTATO(Constants.Items.SEED_POTATO, Blocks.POTATOES.getDefaultState(), PlantType.CROP),
    SEED_SWEET_BERRY(Constants.Items.SEED_SWEET_BERRY, Blocks.SWEET_BERRY_BUSH.getDefaultState(), PlantType.PLAINS),
    SEED_KELP(Constants.Items.SEED_KELP, Blocks.KELP_PLANT.getDefaultState(), PlantType.WATER),
    SEED_PICKLE(Constants.Items.SEED_PICKLE, Blocks.SEA_PICKLE.getDefaultState(), PlantType.WATER),
    SEED_BAMBOO(Constants.Items.SEED_BAMBOO, Blocks.BAMBOO_SAPLING.getDefaultState(), PlantType.PLAINS);

    private final String seedName;
    private final BlockState defaultState;
    private final PlantType plantType;
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

    public String getSeedName() {
        return "seed_" + seedName;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }
}
