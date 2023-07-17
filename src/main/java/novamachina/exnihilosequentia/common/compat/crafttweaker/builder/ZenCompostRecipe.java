package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.CompostRecipe")
@Document("mods/ExNihiloSequentia/Composting")
public class ZenCompostRecipe {

  @Nonnull private final CompostRecipe internal;

  private ZenCompostRecipe(@Nonnull final ResourceLocation recipeId) {
    this.internal = new CompostRecipe(recipeId, Ingredient.EMPTY, 0);
  }

  /**
   * Create a recipe name for the new recipe
   *
   * @param recipeId name of recipe
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenCompostRecipe create(String recipeId) {
    // this is just for creating docs for crafttweaker
    return this;
  }

  @Nonnull
  public static ZenCompostRecipe builder(@Nonnull final ResourceLocation recipeId) {
    return new ZenCompostRecipe(recipeId);
  }

  public CompostRecipe build() {
    return internal;
  }

  /**
   * Sets the amount of compost per input.
   *
   * @param amount Sets the amount.
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenCompostRecipe setAmount(final int amount) {
    internal.setAmount(amount);
    return this;
  }

  /**
   * Sets the input. Can be a tag or an item
   *
   * @param input Sets the input tag or item.
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenCompostRecipe setInput(@Nonnull final IIngredient input) {
    internal.setInput(input.asVanillaIngredient());
    return this;
  }
}
