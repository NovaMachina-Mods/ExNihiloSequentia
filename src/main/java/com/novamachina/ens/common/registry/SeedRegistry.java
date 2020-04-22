package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.registryitem.SeedRegistryItem;
import com.novamachina.ens.common.utility.Constants;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.PlantType;

public class SeedRegistry extends IRegistry<SeedRegistryItem> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        register(Constants.Items.OAK_SEED,
            new SeedRegistryItem(Blocks.OAK_SAPLING.getDefaultState(), PlantType.Plains));
        register(Constants.Items.SPRUCE_SEED,
            new SeedRegistryItem(Blocks.SPRUCE_SAPLING.getDefaultState(), PlantType.Plains));
        register(Constants.Items.BIRCH_SEED,
            new SeedRegistryItem(Blocks.BIRCH_SAPLING.getDefaultState(), PlantType.Plains));
        register(Constants.Items.JUNGLE_SEED,
            new SeedRegistryItem(Blocks.JUNGLE_SAPLING.getDefaultState(), PlantType.Plains));
        register(Constants.Items.ACACIA_SEED,
            new SeedRegistryItem(Blocks.ACACIA_SAPLING.getDefaultState(), PlantType.Plains));
        register(Constants.Items.DARK_OAK_SEED,
            new SeedRegistryItem(Blocks.DARK_OAK_SAPLING.getDefaultState(), PlantType.Plains));
        register(Constants.Items.CACTUS_SEED,
            new SeedRegistryItem(Blocks.CACTUS.getDefaultState(), PlantType.Desert));
        register(Constants.Items.SUGARCANE_SEED,
            new SeedRegistryItem(Blocks.SUGAR_CANE.getDefaultState(), PlantType.Beach));
        register(Constants.Items.CARROT_SEED,
            new SeedRegistryItem(Blocks.CARROTS.getDefaultState(), PlantType.Crop));
        register(Constants.Items.POTATO_SEED,
            new SeedRegistryItem(Blocks.POTATOES.getDefaultState(), PlantType.Crop));
        register(Constants.Items.SWEET_BERRY_SEED,
            new SeedRegistryItem(Blocks.SWEET_BERRY_BUSH.getDefaultState(), PlantType.Plains));
    }
}
