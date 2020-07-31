package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class FluidOnTopRegistry {
    private static Map<String, FluidOnTopRecipe> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        boolean isValid = false;
        String fluidInTankString = fluidInTank.getRegistryName().toString();
        if(recipeMap.containsKey(fluidInTankString)) {
            if(recipeMap.get(fluidInTankString).getFluidOnTop().equals(fluidOnTop.getRegistryName().toString())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static Block getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        String resultString = recipeMap.get(fluidInTank.getRegistryName().toString()).getResult();
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(resultString));
    }

    public static void addRecipe(Fluid fluidInTank, Fluid fluidOnTop, Block result) {
        String fluidInTankString = fluidInTank.getRegistryName().toString();
        String fluidOnTopString = fluidOnTop.getRegistryName().toString();
        String resultString = result.getRegistryName().toString();

        recipeMap.put(fluidInTankString, new FluidOnTopRecipe(fluidInTankString, fluidOnTopString, resultString));
    }


    public static void initialize() {
        addRecipe(Fluids.LAVA, Fluids.WATER, Blocks.OBSIDIAN);
        addRecipe(Fluids.WATER, Fluids.LAVA, Blocks.COBBLESTONE);
    }
}
