package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.FluidTransformRecipe")
@Document("mods/ExNihiloSequentia/Fluid_Transform")
public class ZenFluidTransformRecipe {

  @Nonnull private final FluidTransformRecipe internal;

  private ZenFluidTransformRecipe(@Nonnull final ResourceLocation recipeId) {
    this.internal =
        new FluidTransformRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY, FluidStack.EMPTY);
  }

  /**
   * Create a recipe name for the new recipe
   *
   * @param recipeId name of recipe
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenFluidTransformRecipe create(String recipeId) {
    // this is just for creating docs for crafttweaker
    return this;
  }

  @Nonnull
  public static ZenFluidTransformRecipe builder(@Nonnull final ResourceLocation recipeId) {
    return new ZenFluidTransformRecipe(recipeId);
  }

  @Nonnull
  public FluidTransformRecipe build() {
    return internal;
  }

  /**
   * Sets the block that should be underneath the barrel
   *
   * @param catalyst block under barrel
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenFluidTransformRecipe setCatalyst(@Nonnull final IIngredient catalyst) {
    internal.setCatalyst(catalyst.asVanillaIngredient());
    return this;
  }

  /**
   * Sets the fluid that should be in the barrel
   *
   * @param fluidInTank fluid in Tank
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenFluidTransformRecipe setFluidInTank(@Nonnull final IFluidStack fluidInTank) {
    internal.setFluidInTank(fluidInTank.getInternal());
    return this;
  }

  /**
   * Sets the result block that should be generated
   *
   * @param result block or item
   */
  @ZenCodeType.Method
  @Nonnull
  public ZenFluidTransformRecipe setResult(@Nonnull final IFluidStack result) {
    internal.setResult(result.getInternal());
    return this;
  }
}
