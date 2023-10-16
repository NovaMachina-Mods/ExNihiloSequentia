package novamachina.exnihilosequentia.common.compat.crafttweaker.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.compat.crafttweaker.EXNCrTHelper;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.HeatRecipe")
@Document("mods/ExNihiloSequentia/Heat")
public class HeatManager implements IRecipeManager<HeatRecipe> {

  @ZenCodeType.Method
  public void addRecipe(String name, int amount, Block input, StatePropertiesPredicate properties) {
    name = fixRecipeName(name);
    HeatRecipe recipe =
        new HeatRecipe(EXNCrTHelper.resourceLocation(name), input, amount, properties);

    ActionAddRecipe<HeatRecipe> actionAddRecipe = new ActionAddRecipe<>(this, recipe);
    actionAddRecipe.outputDescriber(inputRecipe -> String.format("%s heat units", amount));

    CraftTweakerAPI.apply(actionAddRecipe);
  }

  @Override
  public RecipeType<HeatRecipe> getRecipeType() {
    return EXNRecipeTypes.HEAT;
  }
}
