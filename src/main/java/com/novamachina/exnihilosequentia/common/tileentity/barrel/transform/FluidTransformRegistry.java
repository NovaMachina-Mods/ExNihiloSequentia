package com.novamachina.exnihilosequentia.common.tileentity.barrel.transform;

import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class FluidTransformRegistry {
    private static Map<String, FluidTransformRecipe> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluidInTank, Block blockBelow) {
        boolean isValid = false;
        String fluidInTankString = fluidInTank.getRegistryName().toString();
        if(recipeMap.containsKey(fluidInTankString)) {
            if(recipeMap.get(fluidInTankString).getBlockBelow().equals(blockBelow.getRegistryName().toString())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static Block getResult(Fluid fluidInTank, Fluid fluidOnTop) {
        String resultString = recipeMap.get(fluidInTank.getRegistryName().toString()).getResult();
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(resultString));
    }

    public static void addRecipe(Fluid fluidInTank, Block blockBelow, Fluid result) {
        String fluidInTankString = fluidInTank.getRegistryName().toString();
        String blockBelowString = blockBelow.getRegistryName().toString();
        String resultString = result.getRegistryName().toString();

        recipeMap.put(fluidInTankString, new FluidTransformRecipe(fluidInTankString, blockBelowString, resultString));
    }


    public static void initialize() {
        addRecipe(Fluids.WATER, Blocks.MYCELIUM, ModFluids.WITCH_WATER_STILL.get());
    }
}
