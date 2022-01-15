package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IIngredient;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nonnull;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidTransformRecipe")
public class ZenFluidTransformRecipe {

    @Nonnull private final FluidTransformRecipe internal;

    private ZenFluidTransformRecipe(@Nonnull final ResourceLocation recipeId) {
        this.internal = new FluidTransformRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY, FluidStack.EMPTY);
    }

    @ZenCodeType.Method
    @Nonnull
    public static ZenFluidTransformRecipe builder(@Nonnull final ResourceLocation recipeId) {
        return new ZenFluidTransformRecipe(recipeId);
    }

    @Nonnull
    public FluidTransformRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidTransformRecipe setCatalyst(@Nonnull final IIngredient catalyst) {
        internal.setCatalyst(catalyst.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidTransformRecipe setFluidInTank(@Nonnull final IFluidStack fluidInTank) {
        internal.setFluidInTank(fluidInTank.getInternal());
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidTransformRecipe setResult(@Nonnull final IFluidStack result) {
        internal.setResult(result.getInternal());
        return this;
    }
}
