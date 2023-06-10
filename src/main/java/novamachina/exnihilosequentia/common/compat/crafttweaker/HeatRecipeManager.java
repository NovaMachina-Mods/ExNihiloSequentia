package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenHeatRecipe;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.HeatRecipes")
@SuppressWarnings("unused")
public class HeatRecipeManager implements IRecipeManager {

 @ZenCodeType.Method
 @Nonnull
 public ZenHeatRecipe create(@Nonnull String recipeId) {
   recipeId = fixRecipeName(recipeId);
   @Nonnull final ResourceLocation resourceLocation = new ResourceLocation(
       ExNihiloConstants.ModIds.CRAFT_TWEAKER,
       recipeId);
   @Nonnull final ZenHeatRecipe recipe = ZenHeatRecipe.builder(resourceLocation);
   CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
   return recipe;
 }

 @Override
 @Nonnull
 public RecipeType<HeatRecipe> getRecipeType() {
   return EXNRecipeTypes.HEAT_RECIPE_TYPE;
 }
}
