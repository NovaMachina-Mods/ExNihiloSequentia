package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCrucibleRecipe")
public class ZenCrucibleRecipe {

  @Nonnull
  private final CrucibleRecipe internal;

  private ZenCrucibleRecipe(@Nonnull final ResourceLocation recipeId) {
    this.internal = new CrucibleRecipe(recipeId, Ingredient.EMPTY, 0, FluidStack.EMPTY,
        CrucibleTypeEnum.WOOD);
  }

  @ZenCodeType.Method
  @Nonnull
  public static ZenCrucibleRecipe builder(@Nonnull final ResourceLocation recipeId) {
    return new ZenCrucibleRecipe(recipeId);
  }

  @Nonnull
  public CrucibleRecipe build() {
    return internal;
  }

  @ZenCodeType.Method
  @Nonnull
  public ZenCrucibleRecipe setAmount(final int amount) {
    internal.setAmount(amount);
    return this;
  }

  @ZenCodeType.Method
  @Nonnull
  public ZenCrucibleRecipe setCrucibleType(@Nonnull final String crucibleType) {
    internal.setCrucibleType(crucibleType);
    return this;
  }

  @ZenCodeType.Method
  @Nonnull
  public ZenCrucibleRecipe setInput(@Nonnull final IIngredient input) {
    internal.setInput(input.asVanillaIngredient());
    return this;
  }

  @ZenCodeType.Method
  @Nonnull
  @SuppressWarnings("unused")
  public ZenCrucibleRecipe setResultFluid(@Nonnull final IFluidStack fluid) {
    internal.setResultFluid(fluid.getInternal());
    return this;
  }
}
