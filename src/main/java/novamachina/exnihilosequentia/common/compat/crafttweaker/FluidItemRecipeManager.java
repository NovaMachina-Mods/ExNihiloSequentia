package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenFluidItemRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nonnull;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.FluidItemRecipes")
@SuppressWarnings("unused")
public class FluidItemRecipeManager implements IRecipeManager {

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidItemRecipe create(@Nonnull String recipeId) {
        recipeId = fixRecipeName(recipeId);
        @Nonnull final ResourceLocation resourceLocation = new ResourceLocation(ExNihiloConstants.ModIds.CRAFT_TWEAKER, recipeId);
        @Nonnull final ZenFluidItemRecipe recipe = ZenFluidItemRecipe.builder(resourceLocation);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    @Nonnull
    public RecipeType<FluidItemRecipe> getRecipeType() {
        return FluidItemRecipe.RECIPE_TYPE;
    }
}
