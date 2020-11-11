package novamachina.exnihilosequentia.common.crafting;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidItem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeReloadListener implements IResourceManagerReloadListener {
    private final DataPackRegistries dataPackRegistries;

    public RecipeReloadListener(DataPackRegistries dataPackRegistries) {
        this.dataPackRegistries = dataPackRegistries;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        if (dataPackRegistries != null) {
            RecipeManager recipeManager = dataPackRegistries.getRecipeManager();
            buildRecipeLists(recipeManager);
        }
    }

    private void buildRecipeLists(RecipeManager recipeManager) {
        Collection<IRecipe<?>> recipes = recipeManager.getRecipes();
        if(recipes.size() == 0) {
            return;
        }

        ExNihiloRegistries.HAMMER_REGISTRY.setRecipes(filterRecipes(recipes, HammerRecipe.class, HammerRecipe.TYPE));
        ExNihiloRegistries.CROOK_REGISTRY.setRecipes(filterRecipes(recipes, CrookRecipe.class, CrookRecipe.TYPE));
        ExNihiloRegistries.COMPOST_REGISTRY.setRecipes(filterRecipes(recipes, CompostRecipe.class, CompostRecipe.TYPE));
        ExNihiloRegistries.FLUID_BLOCK_REGISTRY.setRecipes(filterRecipes(recipes, FluidItemRecipe.class, FluidItemRecipe.TYPE));
        ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.setRecipes(filterRecipes(recipes, FluidOnTopRecipe.class, FluidOnTopRecipe.TYPE));
        ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.setRecipes(filterRecipes(recipes, FluidTransformRecipe.class, FluidTransformRecipe.TYPE));
        ExNihiloRegistries.CRUCIBLE_REGISTRY.setRecipes(filterRecipes(recipes, CrucibleRecipe.class, CrucibleRecipe.TYPE));
        ExNihiloRegistries.HEAT_REGISTRY.setRecipes(filterRecipes(recipes, HeatRecipe.class, HeatRecipe.TYPE));
        ExNihiloRegistries.SIEVE_REGISTRY.setRecipes(filterRecipes(recipes, SieveRecipe.class, SieveRecipe.TYPE));
    }

    private static <R extends IRecipe<?>> List<R> filterRecipes(Collection<IRecipe<?>> recipes, Class<R> recipeClass, IRecipeType<R> recipeType)
    {
        return recipes.stream()
            .filter(iRecipe -> iRecipe.getType() == recipeType)
            .flatMap(Stream::of)
            .map(recipeClass::cast)
            .collect(Collectors.toList());
    }
}
