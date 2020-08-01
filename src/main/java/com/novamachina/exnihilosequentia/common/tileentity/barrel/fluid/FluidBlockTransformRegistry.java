package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import com.novamachina.exnihilosequentia.common.block.BaseFallingBlock;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidBlockTransformRegistry {
    private static Map<String, List<FluidBlockTransformRecipe>> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluid, Item input) {
        String fluidString = fluid.getRegistryName().toString();
        String itemString = input.getRegistryName().toString();

        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluidString);

        if(possibleRecipes == null) {
            return false;
        }

        for(FluidBlockTransformRecipe recipe : possibleRecipes) {
            if(recipe.getInput().equals(itemString)) {
                return true;
            }
        }
        return false;
    }

    public static IItemProvider getResult(Fluid fluid, Item input) {
        String fluidString = fluid.getRegistryName().toString();
        String itemString = input.getRegistryName().toString();

        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluidString);

        for(FluidBlockTransformRecipe recipe : possibleRecipes) {
            if(recipe.getInput().equals(itemString)) {
                return ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.getResult()));
            }
        }
        return null;
    }

    public static void initialize() {
        addRecipe(Fluids.WATER, ModBlocks.DUST.get(), Blocks.CLAY);
        addRecipe(Fluids.LAVA, Items.REDSTONE, Blocks.NETHERRACK);
        addRecipe(Fluids.LAVA, Items.GLOWSTONE_DUST, Blocks.END_STONE);
        addRecipe(ModFluids.WITCH_WATER_STILL.get(), Blocks.SAND, Blocks.SOUL_SAND);
    }

    private static void addRecipe(Fluid fluid, IItemProvider input, Block result) {
        String fluidString = fluid.getRegistryName().toString();
        String itemString = input.asItem().getRegistryName().toString();
        String resultString = result.getRegistryName().toString();

        List<FluidBlockTransformRecipe> recipeList = recipeMap.get(fluidString);

        if(recipeList == null) {
            recipeList = new ArrayList<>();
            recipeMap.put(fluidString, recipeList);
        }
        recipeList.add(new FluidBlockTransformRecipe(fluidString, itemString, resultString));
    }
}
