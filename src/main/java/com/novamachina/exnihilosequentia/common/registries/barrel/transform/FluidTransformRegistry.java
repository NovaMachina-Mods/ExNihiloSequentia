package com.novamachina.exnihilosequentia.common.registries.barrel.transform;

import com.novamachina.exnihilosequentia.common.api.crafting.fluidtransform.FluidTransformRecipe;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidTransformRegistry {
    private final List<FluidTransformRecipe> recipeList = new ArrayList<>();

    public boolean isValidRecipe(Fluid fluidInTank, Block blockBelow) {
        for(FluidTransformRecipe recipe : recipeList) {
            if(recipe.getFluidInTank().isFluidEqual(new FluidStack(fluidInTank, FluidAttributes.BUCKET_VOLUME)) && recipe.getBlockBelow().test(new ItemStack(blockBelow))) {
                return true;
            }
        }
        return false;
    }

    public Fluid getResult(Fluid fluidInTank, Block blockBelow) {
        for(FluidTransformRecipe recipe : recipeList) {
            if(recipe.getFluidInTank().isFluidEqual(new FluidStack(fluidInTank, FluidAttributes.BUCKET_VOLUME)) && recipe.getBlockBelow().test(new ItemStack(blockBelow))) {
                return recipe.getResult().getFluid();
            }
        }
        return Fluids.EMPTY;
    }

    public List<FluidTransformRecipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipes(Map<ResourceLocation, FluidTransformRecipe> recipes) {
        recipeList.addAll(recipes.values());
    }
}
