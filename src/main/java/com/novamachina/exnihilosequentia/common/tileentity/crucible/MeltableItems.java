package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class MeltableItems {

    private static final Map<String, Meltable> meltableList = new HashMap<>();
    private static       boolean               initialized  = false;

    public static void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount,
        Fluid fluid) {
        insertIntoMap(entry.getRegistryName().toString(), new Meltable(amount, fluid));
    }

    private static void insertIntoMap(String name, Meltable meltable) {
        meltableList.put(name, meltable);
    }

    public static boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        String blockName = entry.getRegistryName().toString();
        if (!initialized) {
            useDefaults();
            initialized = true;
        }
        return meltableList.containsKey(blockName);
    }

    public static Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        if (!initialized) {
            useDefaults();
            initialized = true;
        }

        String blockName = entry.getRegistryName().toString();
        return meltableList.getOrDefault(blockName, Meltable.DEFAULT);
    }

    private static void useDefaults() {
        // Lava Meltables
        addMeltable(Blocks.COBBLESTONE, 250, Fluids.LAVA);
        addMeltable(Blocks.DIORITE, 250, Fluids.LAVA);
        addMeltable(Blocks.ANDESITE, 250, Fluids.LAVA);
        addMeltable(Blocks.GRANITE, 250, Fluids.LAVA);
        addMeltable(Blocks.STONE, 250, Fluids.LAVA);
        addMeltable(Blocks.GRAVEL, 200, Fluids.LAVA);
        addMeltable(Blocks.SAND, 100, Fluids.LAVA);
        addMeltable(ModBlocks.DUST.get(), 50, Fluids.LAVA);
        addMeltable(Blocks.NETHERRACK, 1000, Fluids.LAVA);
        addMeltable(Blocks.OBSIDIAN, 1000, Fluids.LAVA);

        // Water Meltables
        addMeltable(Items.ACACIA_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.BIRCH_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.DARK_OAK_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.JUNGLE_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.SPRUCE_SAPLING, 250, Fluids.WATER);
        addMeltable(Items.OAK_SAPLING, 250, Fluids.WATER);

        addMeltable(Items.ACACIA_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.BIRCH_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.DARK_OAK_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.JUNGLE_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.SPRUCE_LEAVES, 250, Fluids.WATER);
        addMeltable(Items.OAK_LEAVES, 250, Fluids.WATER);
    }
}
