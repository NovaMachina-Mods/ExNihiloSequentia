package com.novamachina.ens.common.registry.registryitem;

import net.minecraft.block.BlockState;
import net.minecraftforge.common.PlantType;

public class SeedRegistryItem {

    private final BlockState blockState;
    private final PlantType  plantType;

    public SeedRegistryItem(BlockState blockState, PlantType plantType) {
        this.blockState = blockState;
        this.plantType  = plantType;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public PlantType getPlantType() {
        return plantType;
    }
}
