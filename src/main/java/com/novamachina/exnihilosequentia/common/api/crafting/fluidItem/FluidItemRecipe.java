package com.novamachina.exnihilosequentia.common.api.crafting.fluidItem;

import com.novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import com.novamachina.exnihilosequentia.common.api.crafting.SerializableRecipe;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class FluidItemRecipe extends SerializableRecipe {
    public static IRecipeType<FluidItemRecipe> TYPE = IRecipeType.register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_item");
    public static RegistryObject<RecipeSerializer<FluidItemRecipe>> SERIALIZER;
    private final FluidStack fluid;
    private final Ingredient input;
    private final ItemStack output;

    public FluidItemRecipe(ResourceLocation id, FluidStack fluid, Ingredient input, ItemStack output) {
        super(output, TYPE, id);
        this.fluid = fluid;
        this.input = input;
        this.output = output;
    }

    @Override
    protected RecipeSerializer getIESerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    public FluidStack getFluidInBarrel() {
        return fluid;
    }

    public Ingredient getInput() {
        return input;
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getMatchingStacks());
    }

    public ItemStack getResult() {
        return output;
    }

    public boolean validInputs(Fluid fluid, Item input) {
        return this.fluid.getFluid().isEquivalentTo(fluid) && this.input.test(new ItemStack(input));
    }
}
