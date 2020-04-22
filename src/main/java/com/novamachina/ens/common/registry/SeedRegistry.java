package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.registryitem.SeedRegistryItem;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.PlantType;

public class SeedRegistry extends IRegistry<SeedRegistryItem> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        register("oak",
            new SeedRegistryItem(Blocks.OAK_SAPLING.getDefaultState(), PlantType.Plains));
        register("spruce",
            new SeedRegistryItem(Blocks.SPRUCE_SAPLING.getDefaultState(), PlantType.Plains));
        register("birch",
            new SeedRegistryItem(Blocks.BIRCH_SAPLING.getDefaultState(), PlantType.Plains));
        register("jungle",
            new SeedRegistryItem(Blocks.JUNGLE_SAPLING.getDefaultState(), PlantType.Plains));
        register("acacia",
            new SeedRegistryItem(Blocks.ACACIA_SAPLING.getDefaultState(), PlantType.Plains));
        register("darkoak",
            new SeedRegistryItem(Blocks.DARK_OAK_SAPLING.getDefaultState(), PlantType.Plains));
        register("cactus", new SeedRegistryItem(Blocks.CACTUS.getDefaultState(), PlantType.Desert));
        register("sugarcane",
            new SeedRegistryItem(Blocks.SUGAR_CANE.getDefaultState(), PlantType.Beach));
        register("carrot", new SeedRegistryItem(Blocks.CARROTS.getDefaultState(), PlantType.Crop));
        register("potato", new SeedRegistryItem(Blocks.POTATOES.getDefaultState(), PlantType.Crop));
        register("berry",
            new SeedRegistryItem(Blocks.SWEET_BERRY_BUSH.getDefaultState(), PlantType.Plains));
    }
}
