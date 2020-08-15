package com.novamachina.exnihilosequentia.common.jei;

import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Constants.ModIds.JEI, Constants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new CrookRecipeCategory(guiHelper));
        registration.addRecipeCategories(new SieveRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registerCrook(registration);
        registerSieve(registration);
    }

    private void registerSieve(IRecipeRegistration registration) {
        List<SieveRecipe> sieveRecipes = ModRegistries.SIEVE.getDryRecipeList();
        registration.addRecipes(sieveRecipes, SieveRecipeCategory.UID);
        LogUtil.info("JEI: Sieve Recipes Loaded: " + sieveRecipes.size());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        for(EnumCrook crook : EnumCrook.values()) {
            registration.addRecipeCatalyst(new ItemStack(ModItems.crookMap.get(crook.name).get()), CrookRecipeCategory.UID);
        }
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIEVE.get()), SieveRecipeCategory.UID);
    }

    private void registerCrook(IRecipeRegistration registration) {
        List<CrookRecipe> crookRecipes = ModRegistries.CROOK.getRecipeList();
        registration.addRecipes(crookRecipes, CrookRecipeCategory.UID);
        LogUtil.info("JEI: Crook Recipes Loaded: " + crookRecipes.size());
    }
}
