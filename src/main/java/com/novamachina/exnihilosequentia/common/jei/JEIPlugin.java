package com.novamachina.exnihilosequentia.common.jei;

import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import com.novamachina.exnihilosequentia.common.jei.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.jei.crook.CrookRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.hammer.HammerRecipe;
import com.novamachina.exnihilosequentia.common.jei.hammer.HammerRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.jei.sieve.dry.DrySieveRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.sieve.wet.WetSieveRecipeCategory;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import sun.rmi.runtime.Log;

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
        registration.addRecipeCategories(new DrySieveRecipeCategory(guiHelper));
        registration.addRecipeCategories(new WetSieveRecipeCategory(guiHelper));
        registration.addRecipeCategories(new HammerRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registerCrook(registration);
        registerSieve(registration);
        registerHammer(registration);
    }

    private void registerHammer(IRecipeRegistration registration) {
        List<HammerRecipe> recipes = ModRegistries.HAMMER.getRecipeList();
        registration.addRecipes(recipes, HammerRecipeCategory.UID);
        LogUtil.info("JEI: Hammer Recipes Loaded: " + recipes.size());
    }

    private void registerSieve(IRecipeRegistration registration) {
        List<SieveRecipe> drySieveRecipes = ModRegistries.SIEVE.getDryRecipeList();
        List<SieveRecipe> wetSieveRecipes = ModRegistries.SIEVE.getWetRecipeList();
        registration.addRecipes(drySieveRecipes, DrySieveRecipeCategory.UID);
        registration.addRecipes(wetSieveRecipes, WetSieveRecipeCategory.UID);
        LogUtil.info("JEI: Sieve Recipes Loaded: " + (drySieveRecipes.size() + wetSieveRecipes.size()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        for(EnumCrook crook : EnumCrook.values()) {
            registration.addRecipeCatalyst(new ItemStack(ModItems.crookMap.get(crook.name).get()), CrookRecipeCategory.UID);
        }
        for(EnumHammer hammer : EnumHammer.values()) {
            registration.addRecipeCatalyst(new ItemStack(ModItems.hammerMap.get(hammer.name).get()), HammerRecipeCategory.UID);
        }

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIEVE.get()), DrySieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIEVE.get()), WetSieveRecipeCategory.UID);
    }

    private void registerCrook(IRecipeRegistration registration) {
        List<CrookRecipe> crookRecipes = ModRegistries.CROOK.getRecipeList();
        registration.addRecipes(crookRecipes, CrookRecipeCategory.UID);
        LogUtil.info("JEI: Crook Recipes Loaded: " + crookRecipes.size());
    }
}
