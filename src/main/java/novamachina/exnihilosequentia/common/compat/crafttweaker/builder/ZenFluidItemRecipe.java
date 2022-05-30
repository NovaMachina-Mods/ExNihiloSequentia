package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidItemRecipe")
public class ZenFluidItemRecipe {

  @Nonnull
  private final FluidItemRecipe internal;

  private ZenFluidItemRecipe(@Nonnull final ResourceLocation recipeId) {
    this.internal = new FluidItemRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY,
        ItemStack.EMPTY);
  }

  @ZenCodeType.Method
  @Nonnull
  public static ZenFluidItemRecipe builder(@Nonnull final ResourceLocation recipeId) {
    return new ZenFluidItemRecipe(recipeId);
  }

  @Nonnull
  public FluidItemRecipe build() {
    return internal;
  }

  @ZenCodeType.Method
  @Nonnull
  public ZenFluidItemRecipe setFluidInTank(@Nonnull final IFluidStack fluidInTank) {
    internal.setFluid(fluidInTank.getInternal());
    return this;
  }

  @ZenCodeType.Method
  @Nonnull
  @SuppressWarnings("unused")
  public ZenFluidItemRecipe setInputItem(@Nonnull final IIngredient inputItem) {
    internal.setInput(inputItem.asVanillaIngredient());
    return this;
  }

  @ZenCodeType.Method
  @Nonnull
  public ZenFluidItemRecipe setResult(@Nonnull final IItemStack result) {
    internal.setOutput(result.getInternal());
    return this;
  }
}
