package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import com.novamachina.exnihilosequentia.common.utility.LogUtil;
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

public class FluidOnTopRegistry {
    private static Map<ResourceLocation, List<FluidOnTopRecipe>> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluidInTank, Fluid fluidOnTop) {
        boolean isValid = false;
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if(recipeMap.containsKey(fluidInTankID)) {
            List<FluidOnTopRecipe> recipeList = recipeMap.get(fluidInTankID);
            for(FluidOnTopRecipe recipe : recipeList) {
                if(recipe.getFluidOnTop().equals(fluidOnTop.getRegistryName())) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public static Block getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        ResourceLocation fluidInTankID = fluidInTank.getRegistryName();
        if(recipeMap.containsKey(fluidInTankID)) {
            List<FluidOnTopRecipe> recipeList = recipeMap.get(fluidInTankID);
            for (FluidOnTopRecipe recipe : recipeList) {
                if (recipe.getFluidOnTop().equals(fluidOnTop.getRegistryName())) {
                    return ForgeRegistries.BLOCKS.getValue(recipe.getResult());
                }
            }
        }
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:air"));
    }

    public static void addRecipe(Fluid fluidInTank, Fluid fluidOnTop, Block result) {
        addRecipe(fluidInTank.getRegistryName(), fluidOnTop.getRegistryName(), result.getRegistryName());
    }

    public static void addRecipe(ResourceLocation fluidInTank, ResourceLocation fluidOnTop, ResourceLocation result) {
        List<FluidOnTopRecipe> list = recipeMap.get(fluidInTank);

        if(list == null) {
            list = new ArrayList<>();
            recipeMap.put(fluidInTank, list);
        }
        for(FluidOnTopRecipe recipe : list) {
            if(recipe.getFluidOnTop().equals(fluidOnTop)) {
                LogUtil.warn(String.format("Duplicate recipe: %s(In Barrel) + %s(On Top). Replacing result with most recent: %s", fluidInTank, fluidOnTop, result));
                list.remove(recipe);
            }
        }
        list.add(new FluidOnTopRecipe(fluidInTank, fluidOnTop, result));
    }

    public static void initialize() {
        addRecipe(Fluids.LAVA, Fluids.WATER, Blocks.OBSIDIAN);
        addRecipe(Fluids.WATER, Fluids.LAVA, Blocks.COBBLESTONE);
    }
}
