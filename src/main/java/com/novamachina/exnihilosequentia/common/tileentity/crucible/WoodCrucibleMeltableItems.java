package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.HashMap;
import java.util.Map;

// TODO: Add Tag support
public class WoodCrucibleMeltableItems{

    private static final Map<ResourceLocation, Meltable> meltableList = new HashMap<>();

    public static void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount, Fluid fluid) {
        addMeltable(entry.getRegistryName(), amount, fluid.getRegistryName());
    }

    public static void addMeltable(ResourceLocation entry, int amount, ResourceLocation fluid) {
        insertIntoMap(entry, new Meltable(amount, fluid));
    }

    private static void insertIntoMap(ResourceLocation name, Meltable meltable) {
        meltableList.put(name, meltable);
    }

    public static boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableList.containsKey(entry.getRegistryName());
    }

    public static Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableList.getOrDefault(entry.getRegistryName(), Meltable.DEFAULT);
    }

    public static void initialize() {
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
