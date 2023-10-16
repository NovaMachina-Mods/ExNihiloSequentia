package novamachina.exnihilosequentia.common.compat.crafttweaker.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import java.util.StringJoiner;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.compat.crafttweaker.EXNCrTHelper;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.SiftingRecipe")
@Document("mods/ExNihiloSequentia/Sifting")
public class SiftingManager implements IRecipeManager<SiftingRecipe> {
  @ZenCodeType.Method
  public void addRecipe(
      String name,
      IIngredient input,
      IItemStack drop,
      boolean isWaterlogged,
      MeshWithChance[] rolls) {
    name = fixRecipeName(name);
    SiftingRecipe recipe =
        new SiftingRecipe(
            EXNCrTHelper.resourceLocation(name),
            input.asVanillaIngredient(),
            drop.getInternal(),
            isWaterlogged,
            rolls);
    ActionAddRecipe<SiftingRecipe> actionAddRecipe = new ActionAddRecipe<>(this, recipe);
    actionAddRecipe.outputDescriber(
        inputRecipe -> {
          StringJoiner dropJoiner = new StringJoiner(", ");
          for (MeshWithChance roll : inputRecipe.getRolls()) {
            dropJoiner.add(
                String.format(
                    "%dx %s at %f chance with %s mesh",
                    drop.getInternal().getCount(),
                    ForgeRegistries.ITEMS.getKey(drop.getInternal().getItem()),
                    roll.getChance(),
                    roll.getMesh().getMeshName()));
          }
          return String.format("[%s]", dropJoiner);
        });

    CraftTweakerAPI.apply(actionAddRecipe);
  }

  @Override
  public RecipeType<SiftingRecipe> getRecipeType() {
    return EXNRecipeTypes.SIFTING;
  }
}
