package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenCrookRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.CrookRecipes")
public class CrookRecipeManager implements IRecipeManager {

    @ZenCodeType.Method
    public ZenCrookRecipe create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation resourceLocation = new ResourceLocation(ExNihiloConstants.ModIds.CRAFT_TWEAKER, recipeId);
        ZenCrookRecipe recipe = ZenCrookRecipe.builder(resourceLocation);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public RecipeType<CrookRecipe> getRecipeType() {
        return CrookRecipe.RECIPE_TYPE;
    }

}
