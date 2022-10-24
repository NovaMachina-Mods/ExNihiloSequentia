package novamachina.exnihilosequentia.common.compat.crafttweaker;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.compat.crafttweaker.builder.ZenSeiveRecipe;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.SieveRecipes")
@SuppressWarnings("unused")
public class SieveRecipeManager implements IRecipeManager {
 @ZenCodeType.Method
 @Nonnull
 public ZenSeiveRecipe create(@Nonnull String recipeId) {
   recipeId = fixRecipeName(recipeId);
   @Nonnull final ResourceLocation resourceLocation = new ResourceLocation(
       ExNihiloConstants.ModIds.CRAFT_TWEAKER,
       recipeId);
   @Nonnull final ZenSeiveRecipe recipe = ZenSeiveRecipe.builder(resourceLocation);
   CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe.build(), ""));
   return recipe;
 }
 @Override
 @Nonnull
 public RecipeType<SieveRecipe> getRecipeType() {
   return SieveRecipe.RECIPE_TYPE;
 }
}
