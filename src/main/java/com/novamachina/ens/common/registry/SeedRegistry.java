package com.novamachina.ens.common.registry;

import com.novamachina.ens.ExNihiloSequentia;
import com.novamachina.ens.common.registry.registryitem.SeedRegistryItem;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.PlantType;

public class SeedRegistry extends IRegistry<SeedRegistryItem> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        register("oak", new SeedRegistryItem(Blocks.OAK_SAPLING.getDefaultState(), null));
        register("spruce", new SeedRegistryItem(Blocks.SPRUCE_SAPLING.getDefaultState(), null));
        register("birch", new SeedRegistryItem(Blocks.BIRCH_SAPLING.getDefaultState(), null));
        register("jungle", new SeedRegistryItem(Blocks.JUNGLE_SAPLING.getDefaultState(), null));
        register("acacia", new SeedRegistryItem(Blocks.ACACIA_SAPLING.getDefaultState(), null));
        register("darkoak", new SeedRegistryItem(Blocks.DARK_OAK_SAPLING.getDefaultState(), null));
        register("cactus", new SeedRegistryItem(Blocks.CACTUS.getDefaultState(), PlantType.Desert));
        register("sugarcane",
            new SeedRegistryItem(Blocks.SUGAR_CANE.getDefaultState(), PlantType.Beach));
        register("carrot", new SeedRegistryItem(Blocks.CARROTS.getDefaultState(), PlantType.Crop));
        register("potato", new SeedRegistryItem(Blocks.POTATOES.getDefaultState(), PlantType.Crop));
    }
}
