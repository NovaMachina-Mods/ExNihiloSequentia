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

public class WoodCrucibleMeltableItems {

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
