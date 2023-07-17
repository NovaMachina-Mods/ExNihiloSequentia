package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.FluidItemRecipe")
@Document("mods/ExNihiloSequentia/Fluid_To_Item")
public class ZenFluidItemRecipe {

  @Nonnull private final FluidItemRecipe internal;

  private ZenFluidItemRecipe(@Nonnull final ResourceLocation recipeId) {
    this.internal =
        new FluidItemRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
  }

  /**
   * Create a recipe name for the new recipe
   *
   * @param recipeId name of recipe
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenFluidItemRecipe create(String recipeId) {
    // this is just for creating docs for crafttweaker
    return this;
  }

  @Nonnull
  public static ZenFluidItemRecipe builder(@Nonnull final ResourceLocation recipeId) {
    return new ZenFluidItemRecipe(recipeId);
  }

  @Nonnull
  public FluidItemRecipe build() {
    return internal;
  }

  /**
   * Sets the fluid that should be in the barrel
   *
   * @param fluidInTank fluid in Tank
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenFluidItemRecipe setFluidInTank(@Nonnull final IFluidStack fluidInTank) {
    internal.setFluid(fluidInTank.getInternal());
    return this;
  }

  /**
   * Sets the catalyst to create a new block
   *
   * @param inputItem Catalyst
   */
  @ZenCodeType.Method
  @Nonnull
  @SuppressWarnings("unused")
  public ZenFluidItemRecipe setInputItem(@Nonnull final IIngredient inputItem) {
    internal.setInput(inputItem.asVanillaIngredient());
    return this;
  }

  /**
   * Sets the result block that should be generated
   *
   * @param result block or item
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenFluidItemRecipe setResult(@Nonnull final IItemStack result) {
    internal.setOutput(result.getInternal());
    return this;
  }
}
