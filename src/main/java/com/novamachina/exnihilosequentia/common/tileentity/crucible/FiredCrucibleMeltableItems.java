package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiredCrucibleMeltableItems {

    private static final Map<ResourceLocation, Meltable> meltableMap = new HashMap<>();

    public static void addMeltable(ForgeRegistryEntry<? extends IItemProvider> entry, int amount, Fluid fluid) {
        addMeltable(entry.getRegistryName(), amount, fluid.getRegistryName());
    }

    public static void addMeltable(ResourceLocation entry, int amount, ResourceLocation fluid) {
        insertIntoMap(entry, new Meltable(amount, fluid));
    }

    private static void insertIntoMap(ResourceLocation name, Meltable meltable) {
        meltableMap.put(name, meltable);
    }

    public static boolean isMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableMap.containsKey(entry.getRegistryName());
    }

    public static Meltable getMeltable(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return meltableMap.getOrDefault(entry.getRegistryName(), Meltable.DEFAULT);
    }

    public static void initialize() {
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
    }

    public static List<CrucibleJson> toJSONReady() {
        List<CrucibleJson> jsonList = new ArrayList<>();
        for(Map.Entry<ResourceLocation, Meltable> entry : meltableMap.entrySet()) {
            jsonList.add(new CrucibleJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }
}
