package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import java.util.ArrayList;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.CrookRecipe")
@Document("mods/ExNihiloSequentia/Crooking")
public class ZenCrookRecipe {

  @Nonnull private final CrookRecipe internal;

  private ZenCrookRecipe(@Nonnull final ResourceLocation recipeId) {
    this.internal = new CrookRecipe(recipeId, Ingredient.EMPTY, new ArrayList<>());
  }

  /**
   * Create a recipe name for the new recipe
   *
   * @param recipeId name of recipe
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenCrookRecipe create(String recipeId) {
    // this is just for creating docs for crafttweaker
    return this;
  }

  @Nonnull
  public static ZenCrookRecipe builder(@Nonnull final ResourceLocation recipeId) {
    return new ZenCrookRecipe(recipeId);
  }

  /**
   * Sets the drop with chance
   *
   * @param drop Set the drop. Can only be an item.
   * @param chance How often should the item drop.
   */
  @ZenCodeType.Method
  @Nonnull
  @SuppressWarnings("unused")
  public ZenCrookRecipe addDrop(@Nonnull final IItemStack drop, final float chance) {
    internal.addOutput(drop.getInternal(), chance);
    return this;
  }

  @Nonnull
  public CrookRecipe build() {
    return internal;
  }

  /**
   * Sets the block that should be crooked.
   *
   * @param input Sets the block
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenCrookRecipe setInput(@Nonnull final IIngredient input) {
    internal.setInput(input.asVanillaIngredient());
    return this;
  }
}
