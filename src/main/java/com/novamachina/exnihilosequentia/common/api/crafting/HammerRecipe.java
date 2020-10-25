package com.novamachina.exnihilosequentia.common.api.crafting;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.Collections;
import java.util.Map;

public class HammerRecipe extends SerializableRecipe {
    public static HammerRecipe EMPTY = new HammerRecipe(new ResourceLocation("empty"), ItemStack.EMPTY, ItemStack.EMPTY);

    public static IRecipeType<HammerRecipe> TYPE = IRecipeType
        .register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":hammer");
    public static RegistryObject<RecipeSerializer<HammerRecipe>> SERIALIZER;
    private final ItemStack input;
    private final ItemStack output;

    public HammerRecipe(ResourceLocation id, ItemStack input, ItemStack output) {
        super(output, TYPE, id);
        this.input = input;
        this.output = output;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    protected RecipeSerializer getIESerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }
}
