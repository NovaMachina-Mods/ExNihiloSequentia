package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenFluidTransformRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.FluidTransformRecipes")
public class FluidTransformRecipeManager implements IRecipeManager {

    @ZenCodeType.Method
    public ZenFluidTransformRecipe create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation resourceLocation = new ResourceLocation(ModIds.CRAFT_TWEAKER, recipeId);
        ZenFluidTransformRecipe recipe = ZenFluidTransformRecipe.builder(resourceLocation);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public IRecipeType<FluidTransformRecipe> getRecipeType() {
        return FluidTransformRecipe.RECIPE_TYPE;
    }
}
