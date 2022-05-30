package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCompostRecipe")
public class ZenCompostRecipe {

  @Nonnull
  private final CompostRecipe internal;

  private ZenCompostRecipe(@Nonnull final ResourceLocation recipeId) {
    this.internal = new CompostRecipe(recipeId, Ingredient.EMPTY, 0);
  }

  @ZenCodeType.Method
  @Nonnull
  public static ZenCompostRecipe builder(@Nonnull final ResourceLocation recipeId) {
    return new ZenCompostRecipe(recipeId);
  }

  public CompostRecipe build() {
    return internal;
  }

  @ZenCodeType.Method
  @Nonnull
  public ZenCompostRecipe setAmount(final int amount) {
    internal.setAmount(amount);
    return this;
  }

  @ZenCodeType.Method
  @Nonnull
  public ZenCompostRecipe setInput(@Nonnull final IIngredient input) {
    internal.setInput(input.asVanillaIngredient());
    return this;
  }
}
