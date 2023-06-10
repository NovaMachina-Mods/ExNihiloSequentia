package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenFluidTransformRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.FluidTransformRecipes")
@SuppressWarnings("unused")
public class FluidTransformRecipeManager implements IRecipeManager {

 @ZenCodeType.Method
 public ZenFluidTransformRecipe create(@Nonnull String recipeId) {
   recipeId = fixRecipeName(recipeId);
   @Nonnull final ResourceLocation resourceLocation = new ResourceLocation(
       ExNihiloConstants.ModIds.CRAFT_TWEAKER, recipeId);
   @Nonnull final ZenFluidTransformRecipe recipe = ZenFluidTransformRecipe.builder(
       resourceLocation);
   CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
   return recipe;
 }

 @Override
 @Nonnull
 public RecipeType<FluidTransformRecipe> getRecipeType() {
   return EXNRecipeTypes.FLUID_TRANSFORM_RECIPE_TYPE;
 }
}
