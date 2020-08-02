package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class FluidOnTopRegistry {
    private static Map<ResourceLocation, FluidOnTopRecipe> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        boolean isValid = false;
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if(recipeMap.containsKey(fluidInTankID)) {
            if(recipeMap.get(fluidInTankID).getFluidOnTop().equals(fluidOnTop.getRegistryName())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static Block getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        return ForgeRegistries.BLOCKS.getValue(recipeMap.get(fluidInTank.getRegistryName()).getResult());
    }

    public static void addRecipe(Fluid fluidInTank, Fluid fluidOnTop, Block result) {
        addRecipe(fluidInTank.getRegistryName(), fluidOnTop.getRegistryName(), result.getRegistryName());
    }

    public static void addRecipe(ResourceLocation fluidInTank, ResourceLocation fluidOnTop, ResourceLocation result) {
        recipeMap.put(fluidInTank, new FluidOnTopRecipe(fluidInTank, fluidOnTop, result));
    }

    public static void initialize() {
        addRecipe(Fluids.LAVA, Fluids.WATER, Blocks.OBSIDIAN);
        addRecipe(Fluids.WATER, Fluids.LAVA, Blocks.COBBLESTONE);
    }
}
