package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenHammerRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.HammerRecipes")
public class HammerRecipeManager implements IRecipeManager {

    @ZenCodeType.Method
    public ZenHammerRecipe create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation resourceLocation = new ResourceLocation(ModIds.CRAFT_TWEAKER, recipeId);
        ZenHammerRecipe recipe = ZenHammerRecipe.builder(resourceLocation);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public IRecipeType<HammerRecipe> getRecipeType() {
        return HammerRecipe.RECIPE_TYPE;
    }
}
