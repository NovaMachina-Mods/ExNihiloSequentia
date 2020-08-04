package com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid;

import com.novamachina.exnihilosequentia.common.json.FluidBlockJson;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModFluids;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
    private static final Map<ResourceLocation, List<FluidBlockTransformRecipe>> recipeMap = new HashMap<>();

    public static boolean isValidRecipe(Fluid fluid, Item input) {
        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluid.getRegistryName());

        if(possibleRecipes == null) {
            return false;
        }

        for(FluidBlockTransformRecipe recipe : possibleRecipes) {
            if(recipe.getInput().equals(input.getRegistryName())) {
                return true;
            }
        }
        return false;
    }

    public static IItemProvider getResult(Fluid fluid, Item input) {
        List<FluidBlockTransformRecipe> possibleRecipes = recipeMap.get(fluid.getRegistryName());

        for(FluidBlockTransformRecipe recipe : possibleRecipes) {
            if(recipe.getInput().equals(input.getRegistryName())) {
                return ForgeRegistries.ITEMS.getValue(recipe.getResult());
            }
        }
        return null;
    }

    public static void initialize() {
        addRecipe(Fluids.WATER, ModBlocks.DUST.get(), Blocks.CLAY);
        addRecipe(Fluids.LAVA, Items.REDSTONE, Blocks.NETHERRACK);
        addRecipe(Fluids.LAVA, Items.GLOWSTONE_DUST, Blocks.END_STONE);
        addRecipe(ModFluids.WITCH_WATER_STILL.get(), Blocks.SAND, Blocks.SOUL_SAND);
        addRecipe(ModFluids.WITCH_WATER_STILL.get(), Blocks.RED_MUSHROOM, Blocks.SLIME_BLOCK);
        addRecipe(ModFluids.WITCH_WATER_STILL.get(), Blocks.BROWN_MUSHROOM, Blocks.SLIME_BLOCK);
    }

    public static void addRecipe(ResourceLocation fluid, ResourceLocation input, ResourceLocation result) {
        List<FluidBlockTransformRecipe> list = recipeMap.get(fluid);

        if(list == null) {
            list = new ArrayList<>();
            recipeMap.put(fluid, list);
        }
        for(FluidBlockTransformRecipe recipe : list) {
            if(recipe.getInput().equals(input)) {
                LogUtil.warn(String.format("Duplicate recipe: %s(Fluid) + %s(Input). Replacing result with most recent: %s", fluid, input, result));
                list.remove(recipe);
            }
        }
        list.add(new FluidBlockTransformRecipe(fluid, input, result));
    }

    public static void addRecipe(Fluid fluid, IItemProvider input, Block result) {
        addRecipe(fluid.getRegistryName(), input.asItem().getRegistryName(), result.getRegistryName());
    }

    public static List<FluidBlockJson> toJSONReady() {
        List<FluidBlockJson> gsonList = new ArrayList<>();

        for (List<FluidBlockTransformRecipe> recipeList : recipeMap.values()) {
            for (FluidBlockTransformRecipe recipe : recipeList) {
                gsonList.add(new FluidBlockJson(recipe));
            }
        }

        return gsonList;
    }
}
