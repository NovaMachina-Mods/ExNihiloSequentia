package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenFluidTransformRecipie;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenHammerRecipie;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.FluidTransformRecipes")
public class FluidTransformRecipeManager implements IRecipeManager {

    @ZenCodeType.Method
    public ZenFluidTransformRecipie create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation resourceLocation = new ResourceLocation(ExNihiloConstants.ModIds.CRAFT_TWEAKER, recipeId);
        ZenFluidTransformRecipie recipe = ZenFluidTransformRecipie.builder(resourceLocation);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public IRecipeType<FluidTransformRecipe> getRecipeType() {
        return FluidTransformRecipe.RECIPE_TYPE;
    }
}
