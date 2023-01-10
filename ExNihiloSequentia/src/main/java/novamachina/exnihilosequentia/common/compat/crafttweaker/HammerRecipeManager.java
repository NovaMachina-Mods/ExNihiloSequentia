package novamachina.exnihilosequentia.common.compat.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenHammerRecipe;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.HammerRecipes")
@SuppressWarnings("unused")
public class HammerRecipeManager implements IRecipeManager {

 @ZenCodeType.Method
 @Nonnull
 public ZenHammerRecipe create(@Nonnull String recipeId) {
   recipeId = fixRecipeName(recipeId);
   @Nonnull final ResourceLocation resourceLocation = new ResourceLocation(
       ExNihiloConstants.ModIds.CRAFT_TWEAKER, recipeId);
   @Nonnull final ZenHammerRecipe recipe = ZenHammerRecipe.builder(resourceLocation);
   CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
   return recipe;
 }

 @Override
 @Nonnull
 public RecipeType<HammerRecipe> getRecipeType() {
   return ExNihiloRecipeTypes.HAMMER_RECIPE_TYPE.get();
 }
}
