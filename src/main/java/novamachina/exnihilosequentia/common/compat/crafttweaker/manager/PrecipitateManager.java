package novamachina.exnihilosequentia.common.compat.crafttweaker.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.compat.crafttweaker.EXNCrTHelper;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.PrecipitateRecipe")
@Document("mods/ExNihiloSequentia/Precipitate")
public class PrecipitateManager implements IRecipeManager<PrecipitateRecipe> {
  @ZenCodeType.Method
  public void addRecipe(String name, IFluidStack fluid, IIngredient input, IItemStack result) {
    name = fixRecipeName(name);
    PrecipitateRecipe recipe =
        new PrecipitateRecipe(
            EXNCrTHelper.resourceLocation(name),
            fluid.getInternal(),
            input.asVanillaIngredient(),
            result.getInternal());

    ActionAddRecipe<PrecipitateRecipe> actionAddRecipe = new ActionAddRecipe<>(this, recipe);
    actionAddRecipe.outputDescriber(inputRecipe -> String.format("%s", result.getCommandString()));

    CraftTweakerAPI.apply(actionAddRecipe);
  }

  @Override
  public RecipeType<PrecipitateRecipe> getRecipeType() {
    return EXNRecipeTypes.PRECIPITATE;
  }
}
