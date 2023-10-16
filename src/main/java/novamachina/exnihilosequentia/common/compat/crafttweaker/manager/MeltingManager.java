package novamachina.exnihilosequentia.common.compat.crafttweaker.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.compat.crafttweaker.EXNCrTHelper;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.MeltingRecipe")
@Document("mods/ExNihiloSequentia/Melting")
public class MeltingManager implements IRecipeManager<MeltingRecipe> {

  @ZenCodeType.Method
  public void addRecipe(
      String name,
      IIngredient input,
      IFluidStack fluidStack,
      CrucibleBlockEntity.CrucibleType type) {
    name = fixRecipeName(name);
    MeltingRecipe recipe =
        new MeltingRecipe(
            EXNCrTHelper.resourceLocation(name),
            input.asVanillaIngredient(),
            fluidStack.getInternal(),
            type);

    ActionAddRecipe<MeltingRecipe> actionAddRecipe = new ActionAddRecipe<>(this, recipe);
    actionAddRecipe.outputDescriber(
        inputRecipe ->
            String.format(
                "%s in crucible type: %s", fluidStack.getCommandString(), type.getName()));

    CraftTweakerAPI.apply(actionAddRecipe);
  }

  @Override
  public RecipeType<MeltingRecipe> getRecipeType() {
    return EXNRecipeTypes.MELTING;
  }
}
