package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenCrucibleRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nonnull;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.CrucibleRecipes")
@SuppressWarnings("unused")
public class CrucibleRecipeManager implements IRecipeManager {

    @ZenCodeType.Method
    @Nonnull
    public ZenCrucibleRecipe create(@Nonnull String recipeId) {
        recipeId = fixRecipeName(recipeId);
        @Nonnull final ResourceLocation resourceLocation = new ResourceLocation(ExNihiloConstants.ModIds.CRAFT_TWEAKER,
                recipeId);
        @Nonnull final ZenCrucibleRecipe recipe = ZenCrucibleRecipe.builder(resourceLocation);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    @Nonnull
    public IRecipeType<CrucibleRecipe> getRecipeType() {
        return CrucibleRecipe.RECIPE_TYPE;
    }
}
