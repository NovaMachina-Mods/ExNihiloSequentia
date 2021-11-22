package novamachina.exnihilosequentia.common.registries;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.registry.ICrookRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class CrookRegistry implements ICrookRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private List<CrookRecipe> recipeList = new ArrayList<>();

    @Override
    public boolean isCrookable(IItemProvider block) {
        for (CrookRecipe recipe : recipeList) {
            if (recipe.getInput().test(new ItemStack(block))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CrookRecipe> getDrops(IItemProvider block) {
        List<CrookRecipe> returnList = new ArrayList<>();
        for (CrookRecipe recipe : recipeList) {
            if (recipe.getInput().test(new ItemStack(block))) {
                returnList.add(recipe);
            }
        }
        return returnList;
    }

    @Override
    public void setRecipes(List<CrookRecipe> recipes) {
        logger.debug("Crook Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public List<CrookRecipe> getRecipeList() {
        List<CrookRecipe> recipes = new ArrayList<>();
        for(CrookRecipe recipe : recipeList) {
            if(recipe.getOutput().size() > 21) {
                List<List<ItemStackWithChance>> partitions = Lists.partition(recipe.getOutput(), 21);
                for(int i = 0; i < partitions.size(); i++) {
                    ResourceLocation newId = new ResourceLocation(recipe.getId().getNamespace(), recipe.getId().getPath() + i);
                    recipes.add(new CrookRecipe(newId, recipe.getInput(), partitions.get(i)));
                }
            }
            else {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}