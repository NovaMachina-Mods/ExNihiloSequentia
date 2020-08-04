package com.novamachina.exnihilosequentia.common.tileentity.barrel.transform;

import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidTransformRegistry {
    private static Map<ResourceLocation, FluidTransformRecipe> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluidInTank, Block blockBelow) {
        boolean isValid = false;
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if(recipeMap.containsKey(fluidInTankID)) {
            if(recipeMap.get(fluidInTankID).getBlockBelow().equals(blockBelow.getRegistryName())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static Block getResult(Fluid fluidInTank) {
        return ForgeRegistries.BLOCKS.getValue(recipeMap.get(fluidInTank.getRegistryName()).getResult());
    }

    public static void addRecipe(Fluid fluidInTank, Block blockBelow, Fluid result) {
        addRecipe(fluidInTank.getRegistryName(), blockBelow.getRegistryName(), result.getRegistryName());
    }

    public static void addRecipe(ResourceLocation fluidInTank, ResourceLocation blockBelow, ResourceLocation result) {
        insertIntoMap(fluidInTank, new FluidTransformRecipe(fluidInTank, blockBelow, result));
    }

    private static void insertIntoMap(ResourceLocation fluidInTank, FluidTransformRecipe recipe) {
        recipeMap.put(fluidInTank, recipe);
    }

    public static void initialize() {
        addRecipe(Fluids.WATER, Blocks.MYCELIUM, ModFluids.WITCH_WATER_STILL.get());
    }

    public static List<FluidTransformJSON> toJSONReady() {
        List<FluidTransformJSON> gsonList = new ArrayList<>();

        for (FluidTransformRecipe recipe : recipeMap.values()) {
            gsonList.add(new FluidTransformJSON(recipe));
        }

        return gsonList;
    }
}
